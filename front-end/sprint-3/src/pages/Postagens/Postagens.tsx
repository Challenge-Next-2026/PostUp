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
    postagemId?: number;
}

interface Avaliacao {
    idAvaliacao?: number;
    criterio?: string;
    notaImpacto?: number;
    notaFrequencia?: number;
    notaDificuldade?: number;
    notaConfiabilidade?: number;
    dataAvaliacao?: string;
    postagemId?: number;
}

interface Pontuacao {
    idPontuacao?: number;
    valor?: number;
    dataPontuacao?: string;
    [key: string]: any;
}

export function Postagens() {
    const navigate = useNavigate();

    // Estados de Listagem
    const [usuarios, setUsuarios] = useState<Usuario[]>([]);
    const [postagens, setPostagens] = useState<Postagem[]>([]);
    const [arquivos, setArquivos] = useState<Arquivo[]>([]);
    const [avaliacoes, setAvaliacoes] = useState<Avaliacao[]>([]);
    const [pontuacoes, setPontuacoes] = useState<Pontuacao[]>([]);

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

    const [editandoPostId, setEditandoPostId] = useState<number | null>(null);
    const [editPostTitulo, setEditPostTitulo] = useState('');
    const [editPostDescricao, setEditPostDescricao] = useState('');

    const [editandoArqId, setEditandoArqId] = useState<number | null>(null);
    const [editArqNome, setEditArqNome] = useState('');
    const [editArqTipo, setEditArqTipo] = useState('');

    const [editandoAvalId, setEditandoAvalId] = useState<number | null>(null);
    const [editAvalCriterio, setEditAvalCriterio] = useState('');

    // Estados dos Formulários de Cadastro (mapeados com base nas classes DTO)
    const [postTitulo, setPostTitulo] = useState('');
    const [postDescricao, setPostDescricao] = useState('');

    const [arqNome, setArqNome] = useState('');
    const [arqTipo, setArqTipo] = useState('');
    const [arqTamanho, setArqTamanho] = useState('');
    const [arqUrl, setArqUrl] = useState('');
    const [arqPostId, setArqPostId] = useState('');

    const [avalPostId, setAvalPostId] = useState('');
    const [avalCriterio, setAvalCriterio] = useState('');
    const [avalImpacto, setAvalImpacto] = useState('');
    const [avalDificuldade, setAvalDificuldade] = useState('');
    const [avalConfiabilidade, setAvalConfiabilidade] = useState('');
    const [avalFrequencia, setAvalFrequencia] = useState('');

    const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

    const getHeaders = () => {
        const token = 
            localStorage.getItem('authToken') || 
            localStorage.getItem('token') || 
            localStorage.getItem('access_token');

        return {
            'Content-Type': 'application/json',
            ...(token ? { 'Authorization': `Bearer ${token}` } : {})
        };
    };

    const carregarDados = async () => {
        try {
            const headers = getHeaders();
            const [resUser, resPost, resArq, resAval, resPont] = await Promise.all([
                fetch('https://postup-api.onrender.com/postup/usuario', { headers }),
                fetch('https://postup-api.onrender.com/postup/postagem', { headers }),
                fetch('https://postup-api.onrender.com/postup/arquivo', { headers }),
                fetch('https://postup-api.onrender.com/postup/avaliacao', { headers }),
                fetch('https://postup-api.onrender.com/postup/pontuacao', { headers }),
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
        const token = 
            localStorage.getItem('authToken') || 
            localStorage.getItem('token') || 
            localStorage.getItem('access_token');

        if (!token) {
            navigate('/login');
            return;
        }
        carregarDados();
    }, [navigate]);

    // ================= USUÁRIO =================
    const handleLogout = async () => {
        try {
            await fetch('https://postup-api.onrender.com/postup/usuario/logout', {
                method: 'POST',
                headers: getHeaders()
            });
        } catch (e) {
            console.error(e);
        } finally {
            localStorage.clear();
            navigate('/login');
        }
    };

    const handleBuscarUsuarioPorId = async () => {
        if (!buscaUsuarioId) return;
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/usuario/${buscaUsuarioId}`, { headers: getHeaders() });
            if (res.ok) {
                setUsuarioFiltrado(await res.json());
            } else {
                setUsuarioFiltrado(null);
                setMessage({ type: 'error', text: 'Usuário não encontrado.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro ao buscar usuário.' });
        }
    };

    const handleAtualizarUsuario = async (id: number) => {
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/usuario/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ nome: editUsuarioNome, email: editUsuarioEmail })
            });
            if (res.ok || res.status === 204) {
                setMessage({ type: 'success', text: 'Usuário atualizado com sucesso!' });
                setEditandoUsuarioId(null);
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao atualizar usuário.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirUsuario = async (id?: number) => {
        if (!id || !confirm('Deseja excluir este usuário?')) return;
        const res = await fetch(`https://postup-api.onrender.com/postup/usuario/${id}`, { method: 'DELETE', headers: getHeaders() });
        if (res.ok || res.status === 204) {
            setMessage({ type: 'success', text: 'Usuário excluído com sucesso!' });
            setUsuarioFiltrado(null);
            carregarDados();
        } else {
            setMessage({ type: 'error', text: 'Erro ao excluir usuário.' });
        }
    };

    // ================= POSTAGEM =================
    const handleCriarPostagem = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(null);
        try {
            // Baseado nos atributos da classe DTO Postagem: titulo, descricao
            const payload = { titulo: postTitulo, descricao: postDescricao };
            const response = await fetch('https://postup-api.onrender.com/postup/postagem', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(payload)
            });
            if (response.ok || response.status === 201 || response.status === 204) {
                setMessage({ type: 'success', text: 'Postagem cadastrada com sucesso!' });
                setPostTitulo('');
                setPostDescricao('');
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao cadastrar postagem.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleBuscarPostPorId = async () => {
        if (!buscaPostId) return;
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/postagem/${buscaPostId}`, { headers: getHeaders() });
            if (res.ok) {
                setPostFiltrada(await res.json());
            } else {
                setPostFiltrada(null);
                setMessage({ type: 'error', text: 'Postagem não encontrada.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro ao buscar postagem.' });
        }
    };

    const handleAtualizarPostagem = async (id: number) => {
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/postagem/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ titulo: editPostTitulo, descricao: editPostDescricao })
            });
            if (res.ok || res.status === 204) {
                setMessage({ type: 'success', text: 'Postagem atualizada com sucesso!' });
                setEditandoPostId(null);
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao atualizar postagem.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirPost = async (id?: number) => {
        if (!id || !confirm('Deseja excluir esta postagem?')) return;
        const res = await fetch(`https://postup-api.onrender.com/postup/postagem/${id}`, { method: 'DELETE', headers: getHeaders() });
        if (res.ok || res.status === 204) {
            setMessage({ type: 'success', text: 'Postagem excluída com sucesso!' });
            setPostFiltrada(null);
            carregarDados();
        } else {
            setMessage({ type: 'error', text: 'Erro ao excluir postagem.' });
        }
    };

    // ================= ARQUIVO =================
    const handleCriarArquivo = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(null);
        try {
            // Baseado nos atributos da classe DTO Arquivo: nomeArquivo, tipo, tamanho, url
            const payload = {
                nomeArquivo: arqNome,
                tipo: arqTipo,
                tamanho: arqTamanho,
                url: arqUrl,
                postagemId: Number(arqPostId)
            };
            const res = await fetch('https://postup-api.onrender.com/postup/arquivo', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(payload)
            });
            if (res.ok || res.status === 201 || res.status === 204) {
                setMessage({ type: 'success', text: 'Arquivo cadastrado com sucesso!' });
                setArqNome(''); setArqTipo(''); setArqTamanho(''); setArqUrl(''); setArqPostId('');
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao cadastrar arquivo.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleBuscarArquivoPorId = async () => {
        if (!buscaArqId) return;
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/arquivo/${buscaArqId}`, { headers: getHeaders() });
            if (res.ok) {
                setArqFiltrado(await res.json());
            } else {
                setArqFiltrado(null);
                setMessage({ type: 'error', text: 'Arquivo não encontrado.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro ao buscar arquivo.' });
        }
    };

    const handleAtualizarArquivo = async (id: number) => {
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/arquivo/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ nomeArquivo: editArqNome, tipo: editArqTipo })
            });
            if (res.ok || res.status === 204) {
                setMessage({ type: 'success', text: 'Arquivo atualizado com sucesso!' });
                setEditandoArqId(null);
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao atualizar arquivo.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirArquivo = async (id?: number) => {
        if (!id || !confirm('Deseja excluir este arquivo?')) return;
        const res = await fetch(`https://postup-api.onrender.com/postup/arquivo/${id}`, { method: 'DELETE', headers: getHeaders() });
        if (res.ok || res.status === 204) {
            setMessage({ type: 'success', text: 'Arquivo excluído com sucesso!' });
            setArqFiltrado(null);
            carregarDados();
        } else {
            setMessage({ type: 'error', text: 'Erro ao excluir arquivo.' });
        }
    };

    // ================= AVALIAÇÃO =================
    const handleCriarAvaliacao = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(null);
        try {
            // Baseado nos atributos da classe DTO Avaliacao: criterio, notaImpacto, notaFrequencia, notaDificuldade, notaConfiabilidade
            const payload = {
                postagemId: Number(avalPostId),
                criterio: avalCriterio,
                notaImpacto: Number(avalImpacto),
                notaFrequencia: Number(avalFrequencia),
                notaDificuldade: Number(avalDificuldade),
                notaConfiabilidade: Number(avalConfiabilidade)
            };
            const res = await fetch('https://postup-api.onrender.com/postup/avaliacao/postagem', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(payload)
            });
            if (res.ok || res.status === 201 || res.status === 204) {
                setMessage({ type: 'success', text: 'Avaliação cadastrada com sucesso!' });
                setAvalPostId(''); setAvalCriterio(''); setAvalImpacto(''); setAvalFrequencia('');
                setAvalDificuldade(''); setAvalConfiabilidade('');
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao cadastrar avaliação.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleBuscarAvaliacaoPorId = async () => {
        if (!buscaAvalId) return;
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/avaliacao/${buscaAvalId}`, { headers: getHeaders() });
            if (res.ok) {
                setAvalFiltrada(await res.json());
            } else {
                setAvalFiltrada(null);
                setMessage({ type: 'error', text: 'Avaliação não encontrada.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro ao buscar avaliação.' });
        }
    };

    const handleAtualizarAvaliacao = async (id: number) => {
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/avaliacao/${id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify({ criterio: editAvalCriterio })
            });
            if (res.ok || res.status === 204) {
                setMessage({ type: 'success', text: 'Avaliação atualizada com sucesso!' });
                setEditandoAvalId(null);
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao atualizar avaliação.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    const handleExcluirAvaliacao = async (id?: number) => {
        if (!id || !confirm('Deseja excluir esta avaliação?')) return;
        const res = await fetch(`https://postup-api.onrender.com/postup/avaliacao/${id}`, { method: 'DELETE', headers: getHeaders() });
        if (res.ok || res.status === 204) {
            setMessage({ type: 'success', text: 'Avaliação excluída com sucesso!' });
            setAvalFiltrada(null);
            carregarDados();
        } else {
            setMessage({ type: 'error', text: 'Erro ao excluir avaliação.' });
        }
    };

    // ================= PONTUAÇÃO =================
    const handleBuscarPontuacaoPorId = async () => {
        if (!buscaPontId) return;
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/avaliacao/${buscaPontId}`, { headers: getHeaders() });
            if (res.ok) {
                setPontFiltrada(await res.json());
            } else {
                setPontFiltrada(null);
                setMessage({ type: 'error', text: 'Pontuação/Avaliação não encontrada.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro ao buscar pontuação.' });
        }
    };

    return (
        <div className="min-h-screen bg-slate-100 font-sans text-slate-800 p-6 sm:p-10">
            <div className="max-w-4xl mx-auto space-y-10">

                <header className="flex justify-between items-center bg-white p-6 rounded-2xl shadow-sm border border-slate-200">
                    <div>
                        <h1 className="text-2xl font-black text-slate-900">
                            Post <span className="text-[#44c0c5]">Up</span> - Painel de Controle
                        </h1>
                        <p className="text-xs font-semibold text-slate-400 uppercase tracking-wider">Gerenciamento completo do sistema</p>
                    </div>
                    <button onClick={handleLogout} className="px-4 py-2 bg-rose-50 text-rose-600 rounded-xl font-bold text-sm hover:bg-rose-100 cursor-pointer">
                        Sair
                    </button>
                </header>

                {message && (
                    <div className={`p-4 rounded-2xl text-sm font-semibold ${message.type === 'error' ? 'bg-rose-50 text-rose-600 border border-rose-200' : 'bg-emerald-50 text-emerald-600 border border-emerald-200'}`}>
                        {message.text}
                    </div>
                )}

                {/* Seção Usuários */}
                <section className="bg-white p-8 rounded-3xl shadow-sm border border-slate-200 space-y-6">
                    <div className="flex justify-between items-center border-b pb-4">
                        <div>
                            <h2 className="text-xl font-bold text-slate-900">Usuários</h2>
                            <p className="text-xs text-slate-500">Gerencie os dados de usuários do sistema.</p>
                        </div>
                        <button onClick={carregarDados} className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 text-xs font-bold rounded-xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <div className="flex gap-2">
                        <input type="number" placeholder="Buscar usuário por ID" value={buscaUsuarioId} onChange={e => setBuscaUsuarioId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-sm outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarUsuarioPorId} className="px-5 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-bold text-sm rounded-xl cursor-pointer">Buscar por ID</button>
                        <button onClick={() => setUsuarioFiltrado(null)} className="px-4 py-2.5 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-sm rounded-xl cursor-pointer">Limpar busca</button>
                    </div>

                    {usuarioFiltrado ? (
                        <div className="border border-slate-200 rounded-2xl p-5 space-y-3 bg-slate-50/50">
                            <span className="text-xs font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-2.5 py-1 rounded-lg">ID: {usuarioFiltrado.idUsuario}</span>
                            <p className="text-sm"><strong>Nome:</strong> {usuarioFiltrado.nome}</p>
                            <p className="text-sm"><strong>E-mail:</strong> {usuarioFiltrado.email}</p>
                            <button onClick={() => handleExcluirUsuario(usuarioFiltrado.idUsuario)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-3">
                            {usuarios.map(u => (
                                <div key={u.idUsuario} className="border border-slate-200 rounded-2xl p-5 bg-white shadow-xs flex justify-between items-center">
                                    {editandoUsuarioId === u.idUsuario ? (
                                        <div className="flex-1 space-y-2 mr-4">
                                            <input type="text" value={editUsuarioNome} onChange={e => setEditUsuarioNome(e.target.value)} placeholder="Novo Nome" className="w-full border p-2 rounded-xl text-sm" />
                                            <input type="email" value={editUsuarioEmail} onChange={e => setEditUsuarioEmail(e.target.value)} placeholder="Novo E-mail" className="w-full border p-2 rounded-xl text-sm" />
                                            <div className="flex gap-2">
                                                <button onClick={() => handleAtualizarUsuario(u.idUsuario!)} className="px-3 py-1 bg-emerald-500 text-white text-xs font-bold rounded-lg">Salvar</button>
                                                <button onClick={() => setEditandoUsuarioId(null)} className="px-3 py-1 bg-slate-300 text-slate-700 text-xs font-bold rounded-lg">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-xs font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-2 py-0.5 rounded-md">ID: {u.idUsuario}</span>
                                            <h4 className="font-bold text-base text-slate-900 mt-2">{u.nome}</h4>
                                            <p className="text-xs text-slate-500 mt-1">{u.email}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-2">
                                        {editandoUsuarioId !== u.idUsuario && (
                                            <button onClick={() => { setEditandoUsuarioId(u.idUsuario!); setEditUsuarioNome(u.nome || ''); setEditUsuarioEmail(u.email || ''); }} className="px-3 py-1.5 bg-sky-50 text-sky-600 text-xs font-bold rounded-lg cursor-pointer">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirUsuario(u.idUsuario)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Postagens */}
                <section className="bg-white p-8 rounded-3xl shadow-sm border border-slate-200 space-y-6">
                    <div className="flex justify-between items-center border-b pb-4">
                        <div>
                            <h2 className="text-xl font-bold text-slate-900">Postagens</h2>
                            <p className="text-xs text-slate-500">Gerencie os dados de postagens.</p>
                        </div>
                        <button onClick={carregarDados} className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 text-xs font-bold rounded-xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <div className="space-y-4 bg-slate-50/50 p-6 rounded-2xl border border-slate-100">
                        <h3 className="text-sm font-extrabold text-slate-900 uppercase tracking-wider">Cadastrar registro</h3>
                        <form onSubmit={handleCriarPostagem} className="space-y-4">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Título</label>
                                    <input type="text" value={postTitulo} onChange={e => setPostTitulo(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Descrição</label>
                                    <textarea value={postDescricao} onChange={e => setPostDescricao(e.target.value)} required rows={2} className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5] resize-none" />
                                </div>
                            </div>
                            <button type="submit" className="px-6 py-2.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-sm rounded-xl shadow-md cursor-pointer">
                                Cadastrar
                            </button>
                        </form>
                    </div>

                    <div className="flex gap-2">
                        <input type="number" placeholder="Buscar postagens por ID" value={buscaPostId} onChange={e => setBuscaPostId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-sm outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarPostPorId} className="px-5 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-bold text-sm rounded-xl cursor-pointer">Buscar por ID</button>
                        <button onClick={() => setPostFiltrada(null)} className="px-4 py-2.5 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-sm rounded-xl cursor-pointer">Limpar busca</button>
                    </div>

                    {postFiltrada ? (
                        <div className="border border-slate-200 rounded-2xl p-5 space-y-3 bg-slate-50/50">
                            <span className="text-xs font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-2.5 py-1 rounded-lg">ID: {postFiltrada.idPostagem}</span>
                            <p className="text-sm"><strong>Título:</strong> {postFiltrada.titulo}</p>
                            <p className="text-sm"><strong>Descrição:</strong> {postFiltrada.descricao}</p>
                            <button onClick={() => handleExcluirPost(postFiltrada.idPostagem)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-3">
                            {postagens.map(p => (
                                <div key={p.idPostagem} className="border border-slate-200 rounded-2xl p-5 space-y-2 bg-white shadow-xs flex justify-between items-start">
                                    {editandoPostId === p.idPostagem ? (
                                        <div className="flex-1 space-y-2 mr-4">
                                            <input type="text" value={editPostTitulo} onChange={e => setEditPostTitulo(e.target.value)} placeholder="Novo Título" className="w-full border p-2 rounded-xl text-sm" />
                                            <textarea value={editPostDescricao} onChange={e => setEditPostDescricao(e.target.value)} placeholder="Nova Descrição" rows={2} className="w-full border p-2 rounded-xl text-sm" />
                                            <div className="flex gap-2">
                                                <button onClick={() => handleAtualizarPostagem(p.idPostagem!)} className="px-3 py-1 bg-emerald-500 text-white text-xs font-bold rounded-lg">Salvar</button>
                                                <button onClick={() => setEditandoPostId(null)} className="px-3 py-1 bg-slate-300 text-slate-700 text-xs font-bold rounded-lg">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-xs font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-2 py-0.5 rounded-md">ID: {p.idPostagem}</span>
                                            <h4 className="font-bold text-base text-slate-900 mt-2">{p.titulo}</h4>
                                            <p className="text-xs text-slate-500 mt-1">{p.descricao}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-2">
                                        {editandoPostId !== p.idPostagem && (
                                            <button onClick={() => { setEditandoPostId(p.idPostagem!); setEditPostTitulo(p.titulo || ''); setEditPostDescricao(p.descricao || ''); }} className="px-3 py-1.5 bg-sky-50 text-sky-600 text-xs font-bold rounded-lg cursor-pointer">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirPost(p.idPostagem)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Arquivos */}
                <section className="bg-white p-8 rounded-3xl shadow-sm border border-slate-200 space-y-6">
                    <div className="flex justify-between items-center border-b pb-4">
                        <div>
                            <h2 className="text-xl font-bold text-slate-900">Arquivos</h2>
                            <p className="text-xs text-slate-500">Gerencie os dados de arquivos.</p>
                        </div>
                        <button onClick={carregarDados} className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 text-xs font-bold rounded-xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <div className="space-y-4 bg-slate-50/50 p-6 rounded-2xl border border-slate-100">
                        <h3 className="text-sm font-extrabold text-slate-900 uppercase tracking-wider">Cadastrar registro</h3>
                        <form onSubmit={handleCriarArquivo} className="space-y-4">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Nome do Arquivo</label>
                                    <input type="text" value={arqNome} onChange={e => setArqNome(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Tipo</label>
                                    <input type="text" value={arqTipo} onChange={e => setArqTipo(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Tamanho</label>
                                    <input type="text" value={arqTamanho} onChange={e => setArqTamanho(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">URL</label>
                                    <input type="text" value={arqUrl} onChange={e => setArqUrl(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Postagem Id</label>
                                    <input type="number" value={arqPostId} onChange={e => setArqPostId(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <button type="submit" className="px-6 py-2.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-sm rounded-xl shadow-md cursor-pointer">
                                Cadastrar
                            </button>
                        </form>
                    </div>

                    <div className="flex gap-2">
                        <input type="number" placeholder="Buscar arquivo por ID" value={buscaArqId} onChange={e => setBuscaArqId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-sm outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarArquivoPorId} className="px-5 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-bold text-sm rounded-xl cursor-pointer">Buscar por ID</button>
                        <button onClick={() => setArqFiltrado(null)} className="px-4 py-2.5 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-sm rounded-xl cursor-pointer">Limpar busca</button>
                    </div>

                    {arqFiltrado ? (
                        <div className="border border-slate-200 rounded-2xl p-5 space-y-3 bg-slate-50/50">
                            <span className="text-xs font-bold bg-indigo-50 text-indigo-600 px-2.5 py-1 rounded-lg">ID: {arqFiltrado.idArquivo}</span>
                            <p className="text-sm"><strong>Nome:</strong> {arqFiltrado.nomeArquivo}</p>
                            <p className="text-sm"><strong>Tipo:</strong> {arqFiltrado.tipo} | Tamanho: {arqFiltrado.tamanho}</p>
                            <button onClick={() => handleExcluirArquivo(arqFiltrado.idArquivo)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-3">
                            {arquivos.map(a => (
                                <div key={a.idArquivo} className="border border-slate-200 rounded-2xl p-5 bg-white shadow-xs flex justify-between items-center">
                                    {editandoArqId === a.idArquivo ? (
                                        <div className="flex-1 space-y-2 mr-4">
                                            <input type="text" value={editArqNome} onChange={e => setEditArqNome(e.target.value)} placeholder="Novo Nome" className="w-full border p-2 rounded-xl text-sm" />
                                            <input type="text" value={editArqTipo} onChange={e => setEditArqTipo(e.target.value)} placeholder="Novo Tipo" className="w-full border p-2 rounded-xl text-sm" />
                                            <div className="flex gap-2">
                                                <button onClick={() => handleAtualizarArquivo(a.idArquivo!)} className="px-3 py-1 bg-emerald-500 text-white text-xs font-bold rounded-lg">Salvar</button>
                                                <button onClick={() => setEditandoArqId(null)} className="px-3 py-1 bg-slate-300 text-slate-700 text-xs font-bold rounded-lg">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-xs font-bold bg-indigo-50 text-indigo-600 px-2 py-0.5 rounded-md">ID: {a.idArquivo}</span>
                                            <h4 className="font-bold text-base text-slate-900 mt-2">{a.nomeArquivo}</h4>
                                            <p className="text-xs text-slate-500 mt-1">Tipo: {a.tipo} | Tamanho: {a.tamanho}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-2">
                                        {editandoArqId !== a.idArquivo && (
                                            <button onClick={() => { setEditandoArqId(a.idArquivo!); setEditArqNome(a.nomeArquivo || ''); setEditArqTipo(a.tipo || ''); }} className="px-3 py-1.5 bg-sky-50 text-sky-600 text-xs font-bold rounded-lg cursor-pointer">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirArquivo(a.idArquivo)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Avaliações */}
                <section className="bg-white p-8 rounded-3xl shadow-sm border border-slate-200 space-y-6">
                    <div className="flex justify-between items-center border-b pb-4">
                        <div>
                            <h2 className="text-xl font-bold text-slate-900">Avaliações</h2>
                            <p className="text-xs text-slate-500">Gerencie os dados de avaliações e critérios.</p>
                        </div>
                        <button onClick={carregarDados} className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 text-xs font-bold rounded-xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <div className="space-y-4 bg-slate-50/50 p-6 rounded-2xl border border-slate-100">
                        <h3 className="text-sm font-extrabold text-slate-900 uppercase tracking-wider">Cadastrar registro</h3>
                        <form onSubmit={handleCriarAvaliacao} className="space-y-4">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Postagem Id</label>
                                    <input type="number" value={avalPostId} onChange={e => setAvalPostId(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Critério</label>
                                    <input type="text" value={avalCriterio} onChange={e => setAvalCriterio(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Nota Impacto</label>
                                    <input type="number" value={avalImpacto} onChange={e => setAvalImpacto(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Nota Frequência</label>
                                    <input type="number" value={avalFrequencia} onChange={e => setAvalFrequencia(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Nota Dificuldade</label>
                                    <input type="number" value={avalDificuldade} onChange={e => setAvalDificuldade(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Nota Confiabilidade</label>
                                    <input type="number" value={avalConfiabilidade} onChange={e => setAvalConfiabilidade(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <button type="submit" className="px-6 py-2.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-sm rounded-xl shadow-md cursor-pointer">
                                Cadastrar Avaliação
                            </button>
                        </form>
                    </div>

                    <div className="flex gap-2">
                        <input type="number" placeholder="Buscar avaliação por ID" value={buscaAvalId} onChange={e => setBuscaAvalId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-sm outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarAvaliacaoPorId} className="px-5 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-bold text-sm rounded-xl cursor-pointer">Buscar por ID</button>
                        <button onClick={() => setAvalFiltrada(null)} className="px-4 py-2.5 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-sm rounded-xl cursor-pointer">Limpar busca</button>
                    </div>

                    {avalFiltrada ? (
                        <div className="border border-slate-200 rounded-2xl p-5 space-y-3 bg-slate-50/50">
                            <span className="text-xs font-bold bg-amber-50 text-amber-600 px-2.5 py-1 rounded-lg">ID: {avalFiltrada.idAvaliacao}</span>
                            <p className="text-sm"><strong>Critério:</strong> {avalFiltrada.criterio}</p>
                            <p className="text-xs text-slate-500">Impacto: {avalFiltrada.notaImpacto} | Frequência: {avalFiltrada.notaFrequencia} | Dificuldade: {avalFiltrada.notaDificuldade} | Confiabilidade: {avalFiltrada.notaConfiabilidade}</p>
                            <button onClick={() => handleExcluirAvaliacao(avalFiltrada.idAvaliacao)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-3">
                            {avaliacoes.map(av => (
                                <div key={av.idAvaliacao} className="border border-slate-200 rounded-2xl p-5 bg-white shadow-xs flex justify-between items-center">
                                    {editandoAvalId === av.idAvaliacao ? (
                                        <div className="flex-1 space-y-2 mr-4">
                                            <input type="text" value={editAvalCriterio} onChange={e => setEditAvalCriterio(e.target.value)} placeholder="Novo Critério" className="w-full border p-2 rounded-xl text-sm" />
                                            <div className="flex gap-2">
                                                <button onClick={() => handleAtualizarAvaliacao(av.idAvaliacao!)} className="px-3 py-1 bg-emerald-500 text-white text-xs font-bold rounded-lg">Salvar</button>
                                                <button onClick={() => setEditandoAvalId(null)} className="px-3 py-1 bg-slate-300 text-slate-700 text-xs font-bold rounded-lg">Cancelar</button>
                                            </div>
                                        </div>
                                    ) : (
                                        <div>
                                            <span className="text-xs font-bold bg-amber-50 text-amber-600 px-2 py-0.5 rounded-md">ID: {av.idAvaliacao}</span>
                                            <p className="text-sm mt-2"><strong>Critério:</strong> {av.criterio}</p>
                                            <p className="text-xs text-slate-500 mt-0.5">Impacto: {av.notaImpacto} | Frequência: {av.notaFrequencia} | Dificuldade: {av.notaDificuldade}</p>
                                        </div>
                                    )}
                                    <div className="flex gap-2">
                                        {editandoAvalId !== av.idAvaliacao && (
                                            <button onClick={() => { setEditandoAvalId(av.idAvaliacao!); setEditAvalCriterio(av.criterio || ''); }} className="px-3 py-1.5 bg-sky-50 text-sky-600 text-xs font-bold rounded-lg cursor-pointer">Editar</button>
                                        )}
                                        <button onClick={() => handleExcluirAvaliacao(av.idAvaliacao)} className="px-3 py-1.5 bg-rose-50 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}
                </section>

                {/* Seção Pontuações */}
                <section className="bg-white p-8 rounded-3xl shadow-sm border border-slate-200 space-y-6">
                    <div className="flex justify-between items-center border-b pb-4">
                        <div>
                            <h2 className="text-xl font-bold text-slate-900">Pontuações</h2>
                            <p className="text-xs text-slate-500">Média gerada automaticamente a partir das avaliações.</p>
                        </div>
                        <button onClick={carregarDados} className="px-4 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 text-xs font-bold rounded-xl transition cursor-pointer">
                            Atualizar lista
                        </button>
                    </div>

                    <div className="flex gap-2">
                        <input type="number" placeholder="Consultar pontuação por ID (/postup/avaliacao/{id})" value={buscaPontId} onChange={e => setBuscaPontId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-sm outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarPontuacaoPorId} className="px-5 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-bold text-sm rounded-xl cursor-pointer">Consultar por ID</button>
                        <button onClick={() => setPontFiltrada(null)} className="px-4 py-2.5 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-sm rounded-xl cursor-pointer">Limpar busca</button>
                    </div>

                    {pontFiltrada ? (
                        <div className="border border-slate-200 rounded-2xl p-5 space-y-3 bg-slate-50/50">
                            <span className="text-xs font-bold bg-emerald-50 text-emerald-600 px-2.5 py-1 rounded-lg">ID: {pontFiltrada.idPontuacao || pontFiltrada.id}</span>
                            <p className="text-sm"><strong>Dados da Pontuação:</strong> {JSON.stringify(pontFiltrada)}</p>
                        </div>
                    ) : (
                        <div className="space-y-3">
                            {pontuacoes.map(pt => (
                                <div key={pt.idPontuacao || Math.random()} className="border border-slate-200 rounded-2xl p-5 bg-white shadow-xs">
                                    <span className="text-xs font-bold bg-emerald-50 text-emerald-600 px-2 py-0.5 rounded-md">ID: {pt.idPontuacao || 'N/A'}</span>
                                    <p className="text-sm mt-2"><strong>Valor da Pontuação:</strong> {pt.valor}</p>
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