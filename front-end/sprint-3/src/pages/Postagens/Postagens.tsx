import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

interface Usuario {
    idUsuario?: number;
    nome?: string;
    email?: string;
    senha?: string;
    dataCadastro?: string;
}

interface Postagem {
    idPostagem?: number;
    titulo?: string;
    descricao?: string;
    dataPostagem?: string;
}

interface Arquivo {
    idArquivo?: number;
    nomeArquivo?: string;
    tipo?: string;
    tamanho?: string;
    url?: string;
    dataUpload?: string;
    idPostagem?: number;
}

interface Avaliacao {
    idAvaliacao?: number;
    criterio?: string;
    notaImpacto?: number;
    notaFrequencia?: number;
    notaDificuldade?: number;
    notaConfiabilidade?: number;
    dataAvaliacao?: string;
    idPostagem?: number;
}

interface Pontuacao {
    idPontuacao?: number;
    valor?: number;
    dataPontuacao?: string;
    [key: string]: any;
}

type MessageState = { type: 'success' | 'error'; text: string } | null;

// Função auxiliar para buscar o valor de um cookie pelo nome
const getCookie = (name: string): string | undefined => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop()?.split(';').shift();
};

export function Postagens() {
    const navigate = useNavigate();

    // Estados de Listagem
    const [usuarios, setUsuarios] = useState<Usuario[]>([]);
    const [postagens, setPostagens] = useState<Postagem[]>([]);
    const [arquivos, setArquivos] = useState<Arquivo[]>([]);
    const [avaliacoes, setAvaliacoes] = useState<Avaliacao[]>([]);
    const [pontuacoes, setPontuacoes] = useState<Pontuacao[]>([]);

    // Estados de Mensagem por Seção
    const [msgUsuario, setMsgUsuario] = useState<MessageState>(null);
    const [msgPostagem, setMsgPostagem] = useState<MessageState>(null);
    const [msgArquivo, setMsgArquivo] = useState<MessageState>(null);
    const [msgAvaliacao, setMsgAvaliacao] = useState<MessageState>(null);
    const [msgPontuacao, setMsgPontuacao] = useState<MessageState>(null);

    // Estados de Consulta Individual por ID
    const [buscaUsuarioId, setBuscaUsuarioId] = useState('');
    const [usuarioFiltrado, setUsuarioFiltrado] = useState<Usuario | null>(null);

    const [buscaPostId, setBuscaPostId] = useState('');
    const [postFiltrada, setPostFiltrada] = useState<Postagem | null>(null);

    const [buscaArqId, setBuscaArqId] = useState('');
    const [arqFiltrado, setArqFiltrado] = useState<Arquivo | null>(null);

    const [buscaAvalId, setBuscaAvalId] = useState('');
    const [avalFiltrada, setAvalFiltrada] = useState<Avaliacao | null>(null);

    const [buscaPontId, setBuscaPontId] = useState('');
    const [pontFiltrada, setPontFiltrada] = useState<Pontuacao | null>(null);

    // Estados de Edição
    const [editandoUsuarioId, setEditandoUsuarioId] = useState<number | null>(null);
    const [editUsuarioNome, setEditUsuarioNome] = useState('');
    const [editUsuarioEmail, setEditUsuarioEmail] = useState('');
    const [editUsuarioSenha, setEditUsuarioSenha] = useState('');

    const [editandoPostId, setEditandoPostId] = useState<number | null>(null);
    const [editPostTitulo, setEditPostTitulo] = useState('');
    const [editPostDescricao, setEditPostDescricao] = useState('');

    const [editandoArqId, setEditandoArqId] = useState<number | null>(null);
    const [editArqNome, setEditArqNome] = useState('');
    const [editArqTamanho, setEditArqTamanho] = useState('');
    const [editArqTipo, setEditArqTipo] = useState('');
    const [editArqUrl, setEditArqUrl] = useState('');

    const [editandoAvalId, setEditandoAvalId] = useState<number | null>(null);
    const [editAvalCriterio, setEditAvalCriterio] = useState('');

    // Estados dos Formulários de Cadastro
    const [postTitulo, setPostTitulo] = useState('');
    const [postDescricao, setPostDescricao] = useState('');

    const [arqNome, setArqNome] = useState('');
    const [arqTipo, setArqTipo] = useState('');
    const [arqTamanho, setArqTamanho] = useState('');
    const [arqUrl, setArqUrl] = useState('');
    const [arqPostId, setArqPostId] = useState('');

    const [avalPostId, setAvalPostId] = useState('');

    const getHeaders = () => {
        return {
            'Content-Type': 'application/json'
        };
    };

    const apiFetch = (url: string, options: RequestInit = {}) => {
        return fetch(url, {
            ...options,
            credentials: 'include'
        });
    };

    // Função para traduzir o status HTTP em mensagem
    const tratarRespostaApi = async (res: Response, sucessoPadrao: string): Promise<MessageState> => {
        if (res.ok || res.status === 201 || res.status === 204) {
            return { type: 'success', text: sucessoPadrao };
        }
        
        try {
            const data = await res.json();
            if (data.message) return { type: 'error', text: `Erro (${res.status}): ${data.message}` };
        } catch {}

        switch (res.status) {
            case 400: return { type: 'error', text: 'Erro 400: Dados inválidos ou campos obrigatórios ausentes.' };
            case 401: return { type: 'error', text: 'Erro 401: Não autorizado. Faça login novamente.' };
            case 403: return { type: 'error', text: 'Erro 403: Você não tem permissão para realizar esta ação.' };
            case 404: return { type: 'error', text: 'Erro 404: Registro não encontrado no servidor.' };
            case 500: return { type: 'error', text: 'Erro 500: Erro interno no servidor.' };
            default: return { type: 'error', text: `Erro ${res.status}: Falha ao processar a requisição.` };
        }
    };

    const carregarDados = async () => {
        try {
            const headers = getHeaders();
            const [resUser, resPost, resArq, resAval, resPont] = await Promise.all([
                apiFetch('https://postup-api.onrender.com/postup/usuario', { headers }),
                apiFetch('https://postup-api.onrender.com/postup/postagem', { headers }),
                apiFetch('https://postup-api.onrender.com/postup/arquivo', { headers }),
                apiFetch('https://postup-api.onrender.com/postup/avaliacao', { headers }),
                apiFetch('https://postup-api.onrender.com/postup/pontuacao', { headers }),
            ]);

            if (resUser.ok) setUsuarios(await resUser.json());
            if (resPost.ok) setPostagens(await resPost.json());
            if (resArq.ok) setArquivos(await resArq.json());
            if (resAval.ok) setAvaliacoes(await resAval.json());
            if (resPont.ok) setPontuacoes(await resPont.json());
        } catch (error) {
            console.error('Erro ao carregar dados:', error);
        }
    };

    useEffect(() => {
        const token = getCookie('authToken') || getCookie('token') || getCookie('access_token');
        if (!token) {
            navigate('/login');
            return;
        }
        carregarDados();
    }, [navigate]);

    // Componente reutilizável para renderizar alertas nas seções com tamanho ampliado
    const AlertMessage = ({ message }: { message: MessageState }) => {
        if (!message) return null;
        return (
            <div className={`p-5 rounded-2xl text-base font-semibold mb-5 transition-all ${
                message.type === 'error' 
                    ? 'bg-rose-50 text-rose-600 border border-rose-200' 
                    : 'bg-emerald-50 text-emerald-600 border border-emerald-200'
            }`}>
                {message.text}
            </div>
        );
    };

    // ================= USUÁRIO =================
    const handleLogout = async () => {
        try {
            await apiFetch('https://postup-api.onrender.com/postup/usuario/logout', {
                method: 'POST',
                headers: getHeaders()
            });
        } catch (e) {
            console.error(e);
        } finally {
            sessionStorage.removeItem('isLoggedIn');
            navigate('/login');
        }
    };

    const handleBuscarUsuarioPorId = async () => {
        if (!buscaUsuarioId) return;
        setMsgUsuario(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/usuario/${buscaUsuarioId}`, { headers: getHeaders() });
            if (res.ok) {
                setUsuarioFiltrado(await res.json());
            } else {
                setUsuarioFiltrado(null);
                setMsgUsuario(await tratarRespostaApi(res, ''));
            }
        } catch (error) {
            setMsgUsuario({ type: 'error', text: 'Erro ao conectar ao servidor.' });
        }
    };

    const handleAtualizarUsuario = async (id: number) => {
        setMsgUsuario(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/usuario/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ nome: editUsuarioNome, email: editUsuarioEmail, senha: editUsuarioSenha })
            });
            const msg = await tratarRespostaApi(res, 'Usuário atualizado com sucesso!');
            setMsgUsuario(msg);
            if (msg?.type === 'success') {
                setEditandoUsuarioId(null);
                carregarDados();
            }
        } catch (error) {
            setMsgUsuario({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirUsuario = async (id?: number) => {
        if (!id || !confirm('Deseja excluir este usuário?')) return;
        setMsgUsuario(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/usuario/${id}`, { method: 'DELETE', headers: getHeaders() });
            const msg = await tratarRespostaApi(res, 'Usuário excluído com sucesso!');
            setMsgUsuario(msg);
            if (msg?.type === 'success') {
                setUsuarioFiltrado(null);
                carregarDados();
            }
        } catch {
            setMsgUsuario({ type: 'error', text: 'Erro ao conectar ao servidor.' });
        }
    };

    // ================= POSTAGEM =================
    const handleCriarPostagem = async (e: React.FormEvent) => {
        e.preventDefault();
        setMsgPostagem(null);
        try {
            const payload = { titulo: postTitulo, descricao: postDescricao };
            const response = await apiFetch('https://postup-api.onrender.com/postup/postagem', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(payload)
            });
            const msg = await tratarRespostaApi(response, 'Postagem cadastrada com sucesso!');
            setMsgPostagem(msg);
            if (msg?.type === 'success') {
                setPostTitulo('');
                setPostDescricao('');
                carregarDados();
            }
        } catch (error) {
            setMsgPostagem({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleBuscarPostPorId = async () => {
        if (!buscaPostId) return;
        setMsgPostagem(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/postagem/${buscaPostId}`, { headers: getHeaders() });
            if (res.ok) {
                setPostFiltrada(await res.json());
            } else {
                setPostFiltrada(null);
                setMsgPostagem(await tratarRespostaApi(res, ''));
            }
        } catch (error) {
            setMsgPostagem({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleAtualizarPostagem = async (id: number) => {
        setMsgPostagem(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/postagem/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ titulo: editPostTitulo, descricao: editPostDescricao })
            });
            const msg = await tratarRespostaApi(res, 'Postagem atualizada com sucesso!');
            setMsgPostagem(msg);
            if (msg?.type === 'success') {
                setEditandoPostId(null);
                carregarDados();
            }
        } catch (error) {
            setMsgPostagem({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirPost = async (id?: number) => {
        if (!id || !confirm('Deseja excluir esta postagem?')) return;
        setMsgPostagem(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/postagem/${id}`, { method: 'DELETE', headers: getHeaders() });
            const msg = await tratarRespostaApi(res, 'Postagem excluída com sucesso!');
            setMsgPostagem(msg);
            if (msg?.type === 'success') {
                setPostFiltrada(null);
                carregarDados();
            }
        } catch {
            setMsgPostagem({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    // ================= ARQUIVO =================
    const handleCriarArquivo = async (e: React.FormEvent) => {
        e.preventDefault();
        setMsgArquivo(null);
        try {
            const payload = {
                nomeArquivo: arqNome,
                tipo: arqTipo,
                tamanho: arqTamanho,
                url: arqUrl,
                idPostagem: Number(arqPostId)
            };
            const res = await apiFetch('https://postup-api.onrender.com/postup/arquivo', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(payload)
            });
            const msg = await tratarRespostaApi(res, 'Arquivo cadastrado com sucesso!');
            setMsgArquivo(msg);
            if (msg?.type === 'success') {
                setArqNome(''); setArqTipo(''); setArqTamanho(''); setArqUrl(''); setArqPostId('');
                carregarDados();
            }
        } catch (error) {
            setMsgArquivo({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleBuscarArquivoPorId = async () => {
        if (!buscaArqId) return;
        setMsgArquivo(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/arquivo/${buscaArqId}`, { headers: getHeaders() });
            if (res.ok) {
                setArqFiltrado(await res.json());
            } else {
                setArqFiltrado(null);
                setMsgArquivo(await tratarRespostaApi(res, ''));
            }
        } catch (error) {
            setMsgArquivo({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleAtualizarArquivo = async (id: number) => {
        setMsgArquivo(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/arquivo/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ nomeArquivo: editArqNome, tipo: editArqTipo, tamanho: editArqTamanho, url: editArqUrl })
            });
            const msg = await tratarRespostaApi(res, 'Arquivo atualizado com sucesso!');
            setMsgArquivo(msg);
            if (msg?.type === 'success') {
                setEditandoArqId(null);
                carregarDados();
            }
        } catch (error) {
            setMsgArquivo({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirArquivo = async (id?: number) => {
        if (!id || !confirm('Deseja excluir este arquivo?')) return;
        setMsgArquivo(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/arquivo/${id}`, { method: 'DELETE', headers: getHeaders() });
            const msg = await tratarRespostaApi(res, 'Arquivo excluído com sucesso!');
            setMsgArquivo(msg);
            if (msg?.type === 'success') {
                setArqFiltrado(null);
                carregarDados();
            }
        } catch {
            setMsgArquivo({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    // ================= AVALIAÇÃO =================
    const handleCriarAvaliacao = async (e: React.FormEvent) => {
        e.preventDefault();
        setMsgAvaliacao(null);
        try {
            const payload = { idPostagem: Number(avalPostId) };
            const res = await apiFetch('https://postup-api.onrender.com/postup/avaliacao/postagem', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(payload)
            });
            const msg = await tratarRespostaApi(res, 'Avaliação cadastrada com sucesso!');
            setMsgAvaliacao(msg);
            if (msg?.type === 'success') {
                setAvalPostId('');
                carregarDados();
            }
        } catch (error) {
            setMsgAvaliacao({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleBuscarAvaliacaoPorId = async () => {
        if (!buscaAvalId) return;
        setMsgAvaliacao(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/avaliacao/${buscaAvalId}`, { headers: getHeaders() });
            if (res.ok) {
                setAvalFiltrada(await res.json());
            } else {
                setAvalFiltrada(null);
                setMsgAvaliacao(await tratarRespostaApi(res, ''));
            }
        } catch (error) {
            setMsgAvaliacao({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleAtualizarAvaliacao = async (id: number) => {
        setMsgAvaliacao(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/avaliacao/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ criterio: editAvalCriterio })
            });
            const msg = await tratarRespostaApi(res, 'Avaliação atualizada com sucesso!');
            setMsgAvaliacao(msg);
            if (msg?.type === 'success') {
                setEditandoAvalId(null);
                carregarDados();
            }
        } catch (error) {
            setMsgAvaliacao({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirAvaliacao = async (id?: number) => {
        if (!id || !confirm('Deseja excluir esta avaliação?')) return;
        setMsgAvaliacao(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/avaliacao/${id}`, { method: 'DELETE', headers: getHeaders() });
            const msg = await tratarRespostaApi(res, 'Avaliação excluída com sucesso!');
            setMsgAvaliacao(msg);
            if (msg?.type === 'success') {
                setAvalFiltrada(null);
                carregarDados();
            }
        } catch {
            setMsgAvaliacao({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    // ================= PONTUAÇÃO =================
    const handleBuscarPontuacaoPorId = async () => {
        if (!buscaPontId) return;
        setMsgPontuacao(null);
        try {
            const res = await apiFetch(`https://postup-api.onrender.com/postup/avaliacao/${buscaPontId}`, { headers: getHeaders() });
            if (res.ok) {
                setPontFiltrada(await res.json());
            } else {
                setPontFiltrada(null);
                setMsgPontuacao(await tratarRespostaApi(res, ''));
            }
        } catch (error) {
            setMsgPontuacao({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    return (
        <div className="min-h-screen bg-slate-100 font-sans text-slate-800 p-8 sm:p-12">
            <div className="max-w-5xl mx-auto space-y-12">

                {/* Cabeçalho */}
                <header className="flex justify-between items-center bg-white p-8 rounded-3xl shadow-sm border border-slate-200">
                    <div>
                        <h1 className="text-3xl sm:text-4xl font-black text-slate-900">
                            Post <span className="text-[#44c0c5]">Up</span> - Painel de Controle
                        </h1>
                        <p className="text-sm font-semibold text-slate-400 uppercase tracking-wider mt-1">Gerenciamento completo do sistema</p>
                    </div>
                    <button onClick={handleLogout} className="px-5 py-3 bg-rose-50 text-rose-600 rounded-2xl font-bold text-base hover:bg-rose-100 cursor-pointer transition">
                        Sair
                    </button>
                </header>

                {/* Seção Usuários */}
                <section className="bg-white p-10 rounded-3xl shadow-sm border border-slate-200 space-y-8">
                    <div className="flex justify-between items-center border-b pb-6">
                        <div>
                            <h2 className="text-2xl sm:text-3xl font-bold text-slate-900">Usuários</h2>
                            <p className="text-sm text-slate-500 mt-1">Gerencie os dados de usuários do sistema.</p>
                        </div>
                        <button onClick={carregarDados} className="px-5 py-3 bg-slate-100 hover:bg-slate-200 text-slate-700 text-sm font-bold rounded-2xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <AlertMessage message={msgUsuario} />

                    <div className="flex gap-3">
                        <input type="number" placeholder="Buscar usuário por ID" value={buscaUsuarioId} onChange={e => setBuscaUsuarioId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-2xl px-5 py-4 text-base outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarUsuarioPorId} className="px-6 py-4 bg-slate-900 hover:bg-slate-800 text-white font-bold text-base rounded-2xl cursor-pointer transition">Buscar por ID</button>
                        <button onClick={() => { setUsuarioFiltrado(null); setMsgUsuario(null); }} className="px-5 py-4 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-base rounded-2xl cursor-pointer transition">Limpar busca</button>
                    </div>

                    {usuarioFiltrado ? (
                        <div className="border border-slate-200 rounded-2xl p-6 space-y-4 bg-slate-50/50">
                            <span className="text-sm font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-3.5 py-1.5 rounded-xl">ID: {usuarioFiltrado.idUsuario}</span>
                            <p className="text-base"><strong>Nome:</strong> {usuarioFiltrado.nome}</p>
                            <p className="text-base"><strong>E-mail:</strong> {usuarioFiltrado.email}</p>
                            <button onClick={() => handleExcluirUsuario(usuarioFiltrado.idUsuario)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-4">
                            {usuarios.map(u => (
                                <div key={u.idUsuario} className="border border-slate-200 rounded-2xl p-6 bg-white shadow-xs flex justify-between items-center">
                                    {editandoUsuarioId === u.idUsuario ? (
                                        <div className="flex-1 space-y-3 mr-4">
                                            <input type="text" value={editUsuarioNome} onChange={e => setEditUsuarioNome(e.target.value)} placeholder="Novo Nome" className="w-full border p-3 rounded-xl text-base" />
                                            <input type="email" value={editUsuarioEmail} onChange={e => setEditUsuarioEmail(e.target.value)} placeholder="Novo E-mail" className="w-full border p-3 rounded-xl text-base" />
                                            <input type="password" value={editUsuarioSenha} onChange={e => setEditUsuarioSenha(e.target.value)} placeholder="Nova Senha" className="w-full border p-3 rounded-xl text-base" />
                                            <div className="flex gap-3">
                                                <button onClick={() => handleAtualizarUsuario(u.idUsuario!)} className="px-4 py-2 bg-emerald-500 text-white text-sm font-bold rounded-xl">Salvar</button>
                                                <button onClick={() => setEditandoUsuarioId(null)} className="px-4 py-2 bg-slate-300 text-slate-700 text-sm font-bold rounded-xl">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-sm font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-3 py-1 rounded-lg">ID: {u.idUsuario}</span>
                                            <h4 className="font-bold text-lg text-slate-900 mt-2">{u.nome}</h4>
                                            <p className="text-sm text-slate-500 mt-1">{u.email}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-3">
                                        {editandoUsuarioId !== u.idUsuario && (
                                            <button onClick={() => { setEditandoUsuarioId(u.idUsuario!); setEditUsuarioNome(u.nome || ''); setEditUsuarioEmail(u.email || ''); }} className="px-4 py-2 bg-sky-50 text-sky-600 text-sm font-bold rounded-xl cursor-pointer transition">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirUsuario(u.idUsuario)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Postagens */}
                <section className="bg-white p-10 rounded-3xl shadow-sm border border-slate-200 space-y-8">
                    <div className="flex justify-between items-center border-b pb-6">
                        <div>
                            <h2 className="text-2xl sm:text-3xl font-bold text-slate-900">Postagens</h2>
                            <p className="text-sm text-slate-500 mt-1">Gerencie os dados de postagens.</p>
                        </div>
                        <button onClick={carregarDados} className="px-5 py-3 bg-slate-100 hover:bg-slate-200 text-slate-700 text-sm font-bold rounded-2xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <AlertMessage message={msgPostagem} />

                    <div className="space-y-4 bg-slate-50/50 p-8 rounded-2xl border border-slate-100">
                        <h3 className="text-base font-extrabold text-slate-900 uppercase tracking-wider">Cadastrar registro</h3>
                        <form onSubmit={handleCriarPostagem} className="space-y-5">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">Título</label>
                                    <input type="text" value={postTitulo} onChange={e => setPostTitulo(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">Descrição</label>
                                    <textarea value={postDescricao} onChange={e => setPostDescricao(e.target.value)} required rows={3} className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5] resize-none" />
                                </div>
                            </div>
                            <button type="submit" className="px-7 py-3.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-base rounded-2xl shadow-md cursor-pointer transition">
                                Cadastrar
                            </button>
                        </form>
                    </div>

                    <div className="flex gap-3">
                        <input type="number" placeholder="Buscar postagens por ID" value={buscaPostId} onChange={e => setBuscaPostId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-2xl px-5 py-4 text-base outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarPostPorId} className="px-6 py-4 bg-slate-900 hover:bg-slate-800 text-white font-bold text-base rounded-2xl cursor-pointer transition">Buscar por ID</button>
                        <button onClick={() => { setPostFiltrada(null); setMsgPostagem(null); }} className="px-5 py-4 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-base rounded-2xl cursor-pointer transition">Limpar busca</button>
                    </div>

                    {postFiltrada ? (
                        <div className="border border-slate-200 rounded-2xl p-6 space-y-4 bg-slate-50/50">
                            <span className="text-sm font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-3.5 py-1.5 rounded-xl">ID: {postFiltrada.idPostagem}</span>
                            <p className="text-base"><strong>Título:</strong> {postFiltrada.titulo}</p>
                            <p className="text-base"><strong>Descrição:</strong> {postFiltrada.descricao}</p>
                            <button onClick={() => handleExcluirPost(postFiltrada.idPostagem)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-4">
                            {postagens.map(p => (
                                <div key={p.idPostagem} className="border border-slate-200 rounded-2xl p-6 space-y-3 bg-white shadow-xs flex justify-between items-start">
                                    {editandoPostId === p.idPostagem ? (
                                        <div className="flex-1 space-y-3 mr-4">
                                            <input type="text" value={editPostTitulo} onChange={e => setEditPostTitulo(e.target.value)} placeholder="Novo Título" className="w-full border p-3 rounded-xl text-base" />
                                            <textarea value={editPostDescricao} onChange={e => setEditPostDescricao(e.target.value)} placeholder="Nova Descrição" rows={3} className="w-full border p-3 rounded-xl text-base" />
                                            <div className="flex gap-3">
                                                <button onClick={() => handleAtualizarPostagem(p.idPostagem!)} className="px-4 py-2 bg-emerald-500 text-white text-sm font-bold rounded-xl">Salvar</button>
                                                <button onClick={() => setEditandoPostId(null)} className="px-4 py-2 bg-slate-300 text-slate-700 text-sm font-bold rounded-xl">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-sm font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-3 py-1 rounded-lg">ID: {p.idPostagem}</span>
                                            <h4 className="font-bold text-lg text-slate-900 mt-2">{p.titulo}</h4>
                                            <p className="text-sm text-slate-500 mt-1">{p.descricao}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-3">
                                        {editandoPostId !== p.idPostagem && (
                                            <button onClick={() => { setEditandoPostId(p.idPostagem!); setEditPostTitulo(p.titulo || ''); setEditPostDescricao(p.descricao || ''); }} className="px-4 py-2 bg-sky-50 text-sky-600 text-sm font-bold rounded-xl cursor-pointer transition">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirPost(p.idPostagem)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Arquivos */}
                <section className="bg-white p-10 rounded-3xl shadow-sm border border-slate-200 space-y-8">
                    <div className="flex justify-between items-center border-b pb-6">
                        <div>
                            <h2 className="text-2xl sm:text-3xl font-bold text-slate-900">Arquivos</h2>
                            <p className="text-sm text-slate-500 mt-1">Gerencie os dados de arquivos.</p>
                        </div>
                        <button onClick={carregarDados} className="px-5 py-3 bg-slate-100 hover:bg-slate-200 text-slate-700 text-sm font-bold rounded-2xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <AlertMessage message={msgArquivo} />

                    <div className="space-y-4 bg-slate-50/50 p-8 rounded-2xl border border-slate-100">
                        <h3 className="text-base font-extrabold text-slate-900 uppercase tracking-wider">Cadastrar registro</h3>
                        <form onSubmit={handleCriarArquivo} className="space-y-5">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">Nome do Arquivo</label>
                                    <input type="text" value={arqNome} onChange={e => setArqNome(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">Tipo</label>
                                    <input type="text" value={arqTipo} onChange={e => setArqTipo(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <div className="grid grid-cols-1 md:grid-cols-3 gap-5">
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">Tamanho</label>
                                    <input type="text" value={arqTamanho} onChange={e => setArqTamanho(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">URL</label>
                                    <input type="text" value={arqUrl} onChange={e => setArqUrl(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">Postagem Id</label>
                                    <input type="number" value={arqPostId} onChange={e => setArqPostId(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <button type="submit" className="px-7 py-3.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-base rounded-2xl shadow-md cursor-pointer transition">
                                Cadastrar
                            </button>
                        </form>
                    </div>

                    <div className="flex gap-3">
                        <input type="number" placeholder="Buscar arquivo por ID" value={buscaArqId} onChange={e => setBuscaArqId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-2xl px-5 py-4 text-base outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarArquivoPorId} className="px-6 py-4 bg-slate-900 hover:bg-slate-800 text-white font-bold text-base rounded-2xl cursor-pointer transition">Buscar por ID</button>
                        <button onClick={() => { setArqFiltrado(null); setMsgArquivo(null); }} className="px-5 py-4 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-base rounded-2xl cursor-pointer transition">Limpar busca</button>
                    </div>

                    {arqFiltrado ? (
                        <div className="border border-slate-200 rounded-2xl p-6 space-y-4 bg-slate-50/50">
                            <span className="text-sm font-bold bg-indigo-50 text-indigo-600 px-3.5 py-1.5 rounded-xl">ID: {arqFiltrado.idArquivo}</span>
                            <p className="text-base"><strong>Nome:</strong> {arqFiltrado.nomeArquivo}</p>
                            <p className="text-base"><strong>Tipo:</strong> {arqFiltrado.tipo} | Tamanho: {arqFiltrado.tamanho}</p>
                            <button onClick={() => handleExcluirArquivo(arqFiltrado.idArquivo)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-4">
                            {arquivos.map(a => (
                                <div key={a.idArquivo} className="border border-slate-200 rounded-2xl p-6 bg-white shadow-xs flex justify-between items-center">
                                    {editandoArqId === a.idArquivo ? (
                                        <div className="flex-1 space-y-3 mr-4">
                                            <input type="text" value={editArqNome} onChange={e => setEditArqNome(e.target.value)} placeholder="Novo Nome" className="w-full border p-3 rounded-xl text-base" />
                                            <input type="text" value={editArqTipo} onChange={e => setEditArqTipo(e.target.value)} placeholder="Novo Tipo" className="w-full border p-3 rounded-xl text-base" />
                                            <input type="text" value={editArqTamanho} onChange={e => setEditArqTamanho(e.target.value)} placeholder="Novo Tamanho" className="w-full border p-3 rounded-xl text-base" />
                                            <input type="text" value={editArqUrl} onChange={e => setEditArqUrl(e.target.value)} placeholder="Nova URL" className="w-full border p-3 rounded-xl text-base" />
                                            <div className="flex gap-3">
                                                <button onClick={() => handleAtualizarArquivo(a.idArquivo!)} className="px-4 py-2 bg-emerald-500 text-white text-sm font-bold rounded-xl">Salvar</button>
                                                <button onClick={() => setEditandoArqId(null)} className="px-4 py-2 bg-slate-300 text-slate-700 text-sm font-bold rounded-xl">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-sm font-bold bg-indigo-50 text-indigo-600 px-3 py-1 rounded-lg">ID: {a.idArquivo}</span>
                                            <h4 className="font-bold text-lg text-slate-900 mt-2">{a.nomeArquivo}</h4>
                                            <p className="text-sm text-slate-500 mt-1">Tipo: {a.tipo} | Tamanho: {a.tamanho}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-3">
                                        {editandoArqId !== a.idArquivo && (
                                            <button onClick={() => { setEditandoArqId(a.idArquivo!); setEditArqNome(a.nomeArquivo || ''); setEditArqTipo(a.tipo || ''); }} className="px-4 py-2 bg-sky-50 text-sky-600 text-sm font-bold rounded-xl cursor-pointer transition">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirArquivo(a.idArquivo)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Avaliações */}
                <section className="bg-white p-10 rounded-3xl shadow-sm border border-slate-200 space-y-8">
                    <div className="flex justify-between items-center border-b pb-6">
                        <div>
                            <h2 className="text-2xl sm:text-3xl font-bold text-slate-900">Avaliações</h2>
                            <p className="text-sm text-slate-500 mt-1">Gerencie os dados de avaliações e critérios.</p>
                        </div>
                        <button onClick={carregarDados} className="px-5 py-3 bg-slate-100 hover:bg-slate-200 text-slate-700 text-sm font-bold rounded-2xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <AlertMessage message={msgAvaliacao} />

                    <div className="space-y-4 bg-slate-50/50 p-8 rounded-2xl border border-slate-100">
                        <h3 className="text-base font-extrabold text-slate-900 uppercase tracking-wider">Cadastrar registro</h3>
                        <form onSubmit={handleCriarAvaliacao} className="space-y-5">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                                <div className="space-y-2">
                                    <label className="text-sm font-bold text-slate-600">Postagem Id</label>
                                    <input type="number" value={avalPostId} onChange={e => setAvalPostId(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-2xl p-4 text-base outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <button type="submit" className="px-7 py-3.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-base rounded-2xl shadow-md cursor-pointer transition">
                                Cadastrar Avaliação
                            </button>
                        </form>
                    </div>

                    <div className="flex gap-3">
                        <input type="number" placeholder="Buscar avaliação por ID" value={buscaAvalId} onChange={e => setBuscaAvalId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-2xl px-5 py-4 text-base outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarAvaliacaoPorId} className="px-6 py-4 bg-slate-900 hover:bg-slate-800 text-white font-bold text-base rounded-2xl cursor-pointer transition">Buscar por ID</button>
                        <button onClick={() => { setAvalFiltrada(null); setMsgAvaliacao(null); }} className="px-5 py-4 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-base rounded-2xl cursor-pointer transition">Limpar busca</button>
                    </div>

                    {avalFiltrada ? (
                        <div className="border border-slate-200 rounded-2xl p-6 space-y-4 bg-slate-50/50">
                            <span className="text-sm font-bold bg-amber-50 text-amber-600 px-3.5 py-1.5 rounded-xl">ID: {avalFiltrada.idAvaliacao}</span>
                            <p className="text-base"><strong>Critério:</strong> {avalFiltrada.criterio}</p>
                            <p className="text-sm text-slate-500">Impacto: {avalFiltrada.notaImpacto} | Frequência: {avalFiltrada.notaFrequencia} | Dificuldade: {avalFiltrada.notaDificuldade} | Confiabilidade: {avalFiltrada.notaConfiabilidade}</p>
                            <button onClick={() => handleExcluirAvaliacao(avalFiltrada.idAvaliacao)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-4">
                            {avaliacoes.map(av => (
                                <div key={av.idAvaliacao} className="border border-slate-200 rounded-2xl p-6 bg-white shadow-xs flex justify-between items-center">
                                    {editandoAvalId === av.idAvaliacao ? (
                                        <div className="flex-1 space-y-3 mr-4">
                                            <input type="text" value={editAvalCriterio} onChange={e => setEditAvalCriterio(e.target.value)} placeholder="Novo Critério" className="w-full border p-3 rounded-xl text-base" />
                                            <div className="flex gap-3">
                                                <button onClick={() => handleAtualizarAvaliacao(av.idAvaliacao!)} className="px-4 py-2 bg-emerald-500 text-white text-sm font-bold rounded-xl">Salvar</button>
                                                <button onClick={() => setEditandoAvalId(null)} className="px-4 py-2 bg-slate-300 text-slate-700 text-sm font-bold rounded-xl">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-sm font-bold bg-amber-50 text-amber-600 px-3 py-1 rounded-lg">ID: {av.idAvaliacao}</span>
                                            <p className="text-base mt-2"><strong>Critério:</strong> {av.criterio}</p>
                                            <p className="text-sm text-slate-500 mt-1">Impacto: {av.notaImpacto} | Frequência: {av.notaFrequencia} | Dificuldade: {av.notaDificuldade}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-3">
                                        {editandoAvalId !== av.idAvaliacao && (
                                            <button onClick={() => { setEditandoAvalId(av.idAvaliacao!); setEditAvalCriterio(av.criterio || ''); }} className="px-4 py-2 bg-sky-50 text-sky-600 text-sm font-bold rounded-xl cursor-pointer transition">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirAvaliacao(av.idAvaliacao)} className="px-4 py-2 bg-rose-50 text-rose-600 text-sm font-bold rounded-xl cursor-pointer transition">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Pontuações */}
                <section className="bg-white p-10 rounded-3xl shadow-sm border border-slate-200 space-y-8">
                    <div className="flex justify-between items-center border-b pb-6">
                        <div>
                            <h2 className="text-2xl sm:text-3xl font-bold text-slate-900">Pontuações</h2>
                            <p className="text-sm text-slate-500 mt-1">Média gerada automaticamente a partir das avaliações.</p>
                        </div>
                        <button onClick={carregarDados} className="px-5 py-3 bg-slate-100 hover:bg-slate-200 text-slate-700 text-sm font-bold rounded-2xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <AlertMessage message={msgPontuacao} />

                    <div className="flex gap-3">
                        <input type="number" placeholder="Consultar pontuação por ID (/postup/avaliacao/{id})" value={buscaPontId} onChange={e => setBuscaPontId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-2xl px-5 py-4 text-base outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarPontuacaoPorId} className="px-6 py-4 bg-slate-900 hover:bg-slate-800 text-white font-bold text-base rounded-2xl cursor-pointer transition">Consultar por ID</button>
                        <button onClick={() => { setPontFiltrada(null); setMsgPontuacao(null); }} className="px-5 py-4 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-base rounded-2xl cursor-pointer transition">Limpar busca</button>
                    </div>

                    {pontFiltrada ? (
                        <div className="border border-slate-200 rounded-2xl p-6 space-y-4 bg-slate-50/50">
                            <span className="text-sm font-bold bg-emerald-50 text-emerald-600 px-3.5 py-1.5 rounded-xl">ID: {pontFiltrada.idPontuacao || pontFiltrada.id}</span>
                            <p className="text-base"><strong>Dados da Pontuação:</strong> {JSON.stringify(pontFiltrada)}</p>
                        </div>
                    ) : (
                        <div className="space-y-4">
                            {pontuacoes.map(pt => (
                                <div key={pt.idPontuacao || Math.random()} className="border border-slate-200 rounded-2xl p-6 bg-white shadow-xs">
                                    <span className="text-sm font-bold bg-emerald-50 text-emerald-600 px-3 py-1 rounded-lg">ID: {pt.idPontuacao || 'N/A'}</span>
                                    <p className="text-base mt-2"><strong>Valor da Pontuação:</strong> {pt.valor}</p>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

            </div>
        </div>
    );
}

export default Postagens;