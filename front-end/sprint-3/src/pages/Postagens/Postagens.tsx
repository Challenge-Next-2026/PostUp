import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

interface Postagem { id?: number; titulo?: string; conteudo?: string; }
interface Arquivo { id?: number; nome?: string; tipo?: string; postagemId?: number; }
interface Avaliacao { id?: number; comentario?: string; nota?: number; postagemId?: number; }

export function Postagens() {
    const [postagens, setPostagens] = useState<Postagem[]>([]);
    const [arquivos, setArquivos] = useState<Arquivo[]>([]);
    const [avaliacoes, setAvaliacoes] = useState<Avaliacao[]>([]);

    // Estados dos formulários de cadastro
    const [postTitulo, setPostTitulo] = useState('');
    const [postConteudo, setPostConteudo] = useState('');

    const [arqNome, setArqNome] = useState('');
    const [arqTipo, setArqTipo] = useState('');
    const [arqPostId, setArqPostId] = useState('');

    const [avalPostId, setAvalPostId] = useState('');
    const [avalNota, setAvalNota] = useState('');
    const [avalComentario, setAvalComentario] = useState('');

    // Estados de busca por ID
    const [buscaPostId, setBuscaPostId] = useState('');
    const [postFiltrada, setPostFiltrada] = useState<Postagem | null>(null);

    const [buscaArqId, setBuscaArqId] = useState('');
    const [arqFiltrado, setArqFiltrado] = useState<Arquivo | null>(null);

    const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);
    const navigate = useNavigate();

    const getHeaders = () => {
        const token = localStorage.getItem('authToken');
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        };
    };

    const carregarDados = async () => {
        try {
            const headers = getHeaders();
            const [resPost, resArq, resAval] = await Promise.all([
                fetch('https://postup-api.onrender.com/postup/postagem', { headers }),
                fetch('https://postup-api.onrender.com/postup/arquivo', { headers }),
                fetch('https://postup-api.onrender.com/postup/avaliacao', { headers }),
            ]);

            if (resPost.ok) setPostagens(await resPost.json());
            if (resArq.ok) setArquivos(await resArq.json());
            if (resAval.ok) setAvaliacoes(await resAval.json());
        } catch (error) {
            console.error('Erro ao carregar dados:', error);
        }
    };

    useEffect(() => {
        const token = localStorage.getItem('authToken');
        if (!token) {
            navigate('/postagens');
            return;
        }
        carregarDados();
    }, [navigate]);

    // Cadastrar Postagem
    const handleCriarPostagem = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(null);

        try {
            const payload = {
                titulo: postTitulo,
                conteudo: postConteudo,
                descricao: postConteudo
            };

            const response = await fetch('https://postup-api.onrender.com/postup/postagem', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(payload)
            });

            // Tenta ler a resposta como texto ou json para depuração
            const responseText = await response.text();
            let data: any = {};
            try {
                data = JSON.parse(responseText);
            } catch {
                data = { message: responseText };
            }

            console.log("Resposta detalhada do erro da API:", data);

            if (response.ok) {
                setMessage({ type: 'success', text: 'Postagem cadastrada com sucesso!' });
                setPostTitulo(''); 
                setPostConteudo('');
                carregarDados();
            } else {
                const erroServidor = data.message || data.error || responseText || 'Erro desconhecido ao cadastrar.';
                setMessage({ type: 'error', text: `Erro 400: ${erroServidor}` });
            }
        } catch (error) {
            console.error('Erro de conexão:', error);
            setMessage({ type: 'error', text: 'Erro de conexão com o servidor.' });
        }
    };

    // Cadastrar Arquivo
    const handleCriarArquivo = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(null);
        try {
            const res = await fetch('https://postup-api.onrender.com/postup/arquivo', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify({ nome: arqNome, tipo: arqTipo, postagemId: Number(arqPostId) })
            });
            if (res.ok) {
                setMessage({ type: 'success', text: 'Arquivo cadastrado com sucesso!' });
                setArqNome(''); setArqTipo(''); setArqPostId('');
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao cadastrar arquivo.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    // Cadastrar Avaliação
    const handleCriarAvaliacao = async (e: React.FormEvent) => {
        e.preventDefault();
        setMessage(null);
        try {
            const res = await fetch('https://postup-api.onrender.com/postup/avaliacao/postagem', {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify({ postagemId: Number(avalPostId), nota: Number(avalNota), comentario: avalComentario })
            });
            if (res.ok || res.status === 204) {
                setMessage({ type: 'success', text: 'Avaliação cadastrada com sucesso!' });
                setAvalPostId(''); setAvalNota(''); setAvalComentario('');
                carregarDados();
            } else {
                setMessage({ type: 'error', text: 'Erro ao cadastrar avaliação.' });
            }
        } catch (error) {
            setMessage({ type: 'error', text: 'Erro de conexão.' });
        }
    };

    // Buscar Postagem por ID
    const handleBuscarPostPorId = async () => {
        if (!buscaPostId) return;
        try {
            const res = await fetch(`https://postup-api.onrender.com/postup/postagem/${buscaPostId}`, { headers: getHeaders() });
            if (res.ok) {
                setPostFiltrada(await res.json());
            } else {
                setPostFiltrada(null);
                alert('Postagem não encontrada.');
            }
        } catch (error) {
            console.error(error);
        }
    };

    // Excluir Postagem
    const handleExcluirPost = async (id?: number) => {
        if (!id || !confirm('Deseja excluir esta postagem?')) return;
        const res = await fetch(`https://postup-api.onrender.com/postup/postagem/${id}`, { method: 'DELETE', headers: getHeaders() });
        if (res.ok || res.status === 204) {
            setPostFiltrada(null);
            carregarDados();
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('authToken');
        navigate('/login');
    };

    return (
        <div className="min-h-screen bg-slate-100 font-sans text-slate-800 p-6 sm:p-10">
            <div className="max-w-4xl mx-auto space-y-10">

                {/* Cabeçalho Superior Global */}
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

                {/* ================= SEÇÃO 1: POSTAGENS ================= */}
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

                    {/* Formulário de Cadastro */}
                    <div className="space-y-4 bg-slate-50/50 p-6 rounded-2xl border border-slate-100">
                        <h3 className="text-sm font-extrabold text-slate-900 uppercase tracking-wider">Cadastrar registro</h3>
                        <form onSubmit={handleCriarPostagem} className="space-y-4">
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Título</label>
                                    <input type="text" value={postTitulo} onChange={e => setPostTitulo(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Conteúdo</label>
                                    <textarea value={postConteudo} onChange={e => setPostConteudo(e.target.value)} required rows={2} className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5] resize-none" />
                                </div>
                            </div>
                            <button type="submit" className="px-6 py-2.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-sm rounded-xl shadow-md shadow-[#44c0c5]/20 cursor-pointer">
                                Cadastrar
                            </button>
                        </form>
                    </div>

                    {/* Barra de Busca por ID */}
                    <div className="flex gap-2">
                        <input type="number" placeholder="Buscar postagens por ID" value={buscaPostId} onChange={e => setBuscaPostId(e.target.value)} className="flex-1 bg-slate-50 border border-slate-200 rounded-xl px-4 py-2.5 text-sm outline-none focus:border-[#44c0c5]" />
                        <button onClick={handleBuscarPostPorId} className="px-5 py-2.5 bg-slate-900 hover:bg-slate-800 text-white font-bold text-sm rounded-xl cursor-pointer">Buscar por ID</button>
                        <button onClick={() => setPostFiltrada(null)} className="px-4 py-2.5 bg-slate-100 hover:bg-slate-200 text-slate-700 font-semibold text-sm rounded-xl cursor-pointer">Limpar busca</button>
                    </div>

                    {/* Exibição de Postagem Filtrada ou Lista */}
                    {postFiltrada ? (
                        <div className="border border-slate-200 rounded-2xl p-5 space-y-3 bg-slate-50/50">
                            <div className="flex justify-between items-center">
                                <span className="text-xs font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-2.5 py-1 rounded-lg">ID: {postFiltrada.id}</span>
                                <span className="text-xs text-slate-400 font-semibold">Postagem</span>
                            </div>
                            <p className="text-sm"><strong>Título:</strong> {postFiltrada.titulo}</p>
                            <p className="text-sm"><strong>Conteúdo:</strong> {postFiltrada.conteudo}</p>
                            <button onClick={() => handleExcluirPost(postFiltrada.id)} className="px-3 py-1.5 bg-rose-50 hover:bg-rose-100 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                        </div>
                    ) : (
                        <div className="space-y-3">
                            {postagens.map(p => (
                                <div key={p.id} className="border border-slate-200 rounded-2xl p-5 space-y-2 bg-white shadow-xs flex justify-between items-start">
                                    <div>
                                        <span className="text-xs font-bold bg-[#44c0c5]/10 text-[#44c0c5] px-2 py-0.5 rounded-md">ID: {p.id}</span>
                                        <h4 className="font-bold text-base text-slate-900 mt-2">{p.titulo}</h4>
                                        <p className="text-xs text-slate-500 mt-1">{p.conteudo}</p>
                                    </div>
                                    <button onClick={() => handleExcluirPost(p.id)} className="px-3 py-1.5 bg-rose-50 hover:bg-rose-100 text-rose-600 text-xs font-bold rounded-lg cursor-pointer">Excluir</button>
                                </div>
                            ))}
                        </div>
                    )}
                </section>


                {/* ================= SEÇÃO 2: ARQUIVOS ================= */}
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
                            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Nome</label>
                                    <input type="text" value={arqNome} onChange={e => setArqNome(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Tipo</label>
                                    <input type="text" value={arqTipo} onChange={e => setArqTipo(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                                <div className="space-y-1">
                                    <label className="text-xs font-bold text-slate-600">Postagem Id</label>
                                    <input type="number" value={arqPostId} onChange={e => setArqPostId(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <button type="submit" className="px-6 py-2.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-sm rounded-xl shadow-md shadow-[#44c0c5]/20 cursor-pointer">
                                Cadastrar
                            </button>
                        </form>
                    </div>

                    <div className="space-y-3">
                        {arquivos.length === 0 ? (
                            <div className="border border-dashed border-slate-300 rounded-2xl p-10 text-center space-y-1">
                                <p className="text-slate-500 font-bold text-sm">Nenhum registro encontrado.</p>
                                <p className="text-slate-400 text-xs">Verifique a API ou cadastre um novo registro.</p>
                            </div>
                        ) : (
                            arquivos.map(a => (
                                <div key={a.id} className="border border-slate-200 rounded-2xl p-5 bg-white shadow-xs">
                                    <span className="text-xs font-bold bg-indigo-50 text-indigo-600 px-2 py-0.5 rounded-md">ID: {a.id}</span>
                                    <h4 className="font-bold text-base text-slate-900 mt-2">{a.nome}</h4>
                                    <p className="text-xs text-slate-500 mt-1">Tipo: {a.tipo} | Post ID: {a.postagemId}</p>
                                </div>
                            ))
                        )}
                    </div>
                </section>


                {/* ================= SEÇÃO 3: AVALIAÇÕES ================= */}
                <section className="bg-white p-8 rounded-3xl shadow-sm border border-slate-200 space-y-6">
                    <div className="flex justify-between items-center border-b pb-4">
                        <div>
                            <h2 className="text-xl font-bold text-slate-900">Avaliações</h2>
                            <p className="text-xs text-slate-500">Gerencie os dados de avaliações.</p>
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
                                    <label className="text-xs font-bold text-slate-600">Nota (1 a 5)</label>
                                    <input type="number" min="1" max="5" value={avalNota} onChange={e => setAvalNota(e.target.value)} required className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5]" />
                                </div>
                            </div>
                            <div className="space-y-1">
                                <label className="text-xs font-bold text-slate-600">Comentário</label>
                                <textarea value={avalComentario} onChange={e => setAvalComentario(e.target.value)} rows={2} className="w-full bg-white border border-slate-200 rounded-xl p-3 text-sm outline-none focus:border-[#44c0c5] resize-none" />
                            </div>
                            <button type="submit" className="px-6 py-2.5 bg-[#44c0c5] hover:bg-[#3aaab0] text-white font-bold text-sm rounded-xl shadow-md shadow-[#44c0c5]/20 cursor-pointer">
                                Cadastrar
                            </button>
                        </form>
                    </div>

                    <div className="space-y-3">
                        {avaliacoes.map(av => (
                            <div key={av.id} className="border border-slate-200 rounded-2xl p-5 bg-white shadow-xs">
                                <span className="text-xs font-bold bg-amber-50 text-amber-600 px-2 py-0.5 rounded-md">ID: {av.id}</span>
                                <p className="text-sm mt-2"><strong>Nota:</strong> {av.nota}</p>
                                <p className="text-xs text-slate-500 mt-0.5">Post ID: {av.postagemId} | Comentário: {av.comentario || 'Nenhum'}</p>
                            </div>
                        ))}
                    </div>
                </section>

            </div>
        </div>
    );
}

export default Postagens;