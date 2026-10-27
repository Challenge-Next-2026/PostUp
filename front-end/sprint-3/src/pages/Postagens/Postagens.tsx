import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

interface Postagem {
    id?: number;
    titulo?: string;
    conteudo?: string;
}

export function Postagens() {
    const [postagens, setPostagens] = useState<Postagem[]>([]);
    const [titulo, setTitulo] = useState<string>('');
    const [conteudo, setConteudo] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(false);
    const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

    const navigate = useNavigate();
    const token = localStorage.getItem('authToken');

    // Função para buscar todas as postagens (GET /postup/postagem)
    const buscarPostagens = async () => {
        try {
            const response = await fetch('https://postup-api.onrender.com/postup/postagem', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                const data = await response.json();
                setPostagens(data);
            } else {
                setMessage({ type: 'error', text: 'Erro ao carregar as postagens.' });
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
        }
    };

    useEffect(() => {
        buscarPostagens();
    }, []);

    // Função para criar uma nova postagem (POST /postup/postagem)
    const handleCriarPostagem = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setMessage(null);

        try {
            const response = await fetch('https://postup-api.onrender.com/postup/postagem', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ titulo, conteudo })
            });

            if (response.ok) {
                setMessage({ type: 'success', text: 'Postagem criada com sucesso!' });
                setTitulo('');
                setConteudo('');
                buscarPostagens();
            } else {
                setMessage({ type: 'error', text: 'Erro ao criar postagem. Verifique os dados.' });
            }
        } catch (error) {
            console.error('Erro:', error);
            setMessage({ type: 'error', text: 'Erro de conexão com o servidor.' });
        } finally {
            setLoading(false);
        }
    };

    // Função para excluir uma postagem (DELETE /postup/postagem/{id})
    const handleExcluir = async (id?: number) => {
        if (!id) return;
        if (!confirm('Deseja realmente excluir esta postagem?')) return;

        try {
            const response = await fetch(`https://postup-api.onrender.com/postup/postagem/${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok || response.status === 204) {
                setMessage({ type: 'success', text: 'Postagem excluída com sucesso!' });
                buscarPostagens();
            } else {
                setMessage({ type: 'error', text: 'Erro ao excluir a postagem.' });
            }
        } catch (error) {
            console.error('Erro:', error);
        }
    };

    const handleLogout = () => {
        localStorage.removeItem('authToken');
        navigate('/login');
    };

    return (
        <div className="min-h-screen bg-gradient from-gray-50 via-gray-100 to-gray-200 font-sans p-4 sm:p-8">
            <div className="max-w-3xl mx-auto space-y-6">

                {/* Cabeçalho */}
                <header className="flex flex-col sm:flex-row justify-between items-center gap-4 bg-white/80 backdrop-blur-md p-6 rounded-3xl shadow-xl shadow-gray-200/50 border border-white">
                    <div>
                        <h1 className="text-2xl font-extrabold tracking-tight text-gray-900">
                            Post <span className="text-[#44c0c5]">Up</span>
                        </h1>
                        <p className="text-xs font-medium text-gray-500 uppercase tracking-wider mt-0.5">
                            Painel de Publicações
                        </p>
                    </div>
                    <button
                        onClick={handleLogout}
                        className="w-full sm:w-auto px-5 py-2.5 bg-red-50 text-red-600 rounded-xl font-bold text-sm hover:bg-red-100 active:scale-95 transition-all duration-200 cursor-pointer shadow-sm"
                    >
                        Sair da Conta
                    </button>
                </header>

                {/* Formulário de Criação */}
                <section className="bg-white p-6 sm:p-8 rounded-3xl shadow-xl shadow-gray-200/50 border border-gray-100">
                    <h2 className="text-lg font-bold text-gray-900 mb-4 flex items-center gap-2">
                        <span className="w-2.5 h-2.5 rounded-full bg-[#44c0c5]"></span>
                        Criar Nova Postagem
                    </h2>

                    {message && (
                        <div className={`p-4 rounded-2xl mb-5 text-sm font-semibold transition-all ${message.type === 'error'
                                ? 'bg-red-50 text-red-600 border border-red-100'
                                : 'bg-emerald-50 text-emerald-600 border border-emerald-100'
                            }`}>
                            {message.text}
                        </div>
                    )}

                    <form onSubmit={handleCriarPostagem} className="flex flex-col gap-4">
                        <div className="flex flex-col gap-1.5">
                            <label className="text-xs font-bold text-gray-600 uppercase tracking-wider">Título</label>
                            <input
                                type="text"
                                placeholder="Sobre o que você quer falar?"
                                value={titulo}
                                onChange={(e) => setTitulo(e.target.value)}
                                required
                                className="rounded-2xl border border-gray-200 bg-gray-50/50 p-4 text-base text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-4 focus:ring-[#44c0c5]/10"
                            />
                        </div>

                        <div className="flex flex-col gap-1.5">
                            <label className="text-xs font-bold text-gray-600 uppercase tracking-wider">Conteúdo</label>
                            <textarea
                                placeholder="Escreva os detalhes da sua postagem..."
                                value={conteudo}
                                onChange={(e) => setConteudo(e.target.value)}
                                required
                                rows={4}
                                className="rounded-2xl border border-gray-200 bg-gray-50/50 p-4 text-base text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-4 focus:ring-[#44c0c5]/10 resize-none"
                            />
                        </div>

                        <button
                            type="submit"
                            disabled={loading}
                            className={`mt-2 rounded-2xl bg-[#44c0c5] p-4 text-base font-bold text-white shadow-lg shadow-[#44c0c5]/25 transition-all duration-200 hover:bg-[#3aaab0] active:scale-[0.99] ${loading ? 'cursor-not-allowed opacity-70' : 'cursor-pointer'
                                }`}
                        >
                            {loading ? 'Publicando...' : 'Publicar Agora'}
                        </button>
                    </form>
                </section>

                {/* Lista de Postagens */}
                <section className="space-y-4">
                    <div className="flex justify-between items-center px-2">
                        <h2 className="text-lg font-bold text-gray-900">Postagens Recentes</h2>
                        <span className="text-xs font-bold bg-gray-200/70 text-gray-700 px-3 py-1 rounded-full">
                            {postagens.length} {postagens.length === 1 ? 'post' : 'posts'}
                        </span>
                    </div>

                    {postagens.length === 0 ? (
                        <div className="bg-white p-12 rounded-3xl border border-gray-100 text-center shadow-sm">
                            <p className="text-gray-400 text-sm font-medium">Nenhuma postagem encontrada no momento.</p>
                        </div>
                    ) : (
                        postagens.map((post) => (
                            <div
                                key={post.id}
                                className="bg-white p-6 sm:p-7 rounded-3xl shadow-sm hover:shadow-md transition-all duration-200 border border-gray-100 flex flex-col sm:flex-row justify-between items-start gap-4"
                            >
                                <div className="space-y-2 flex-1">
                                    <h3 className="text-lg font-extrabold text-gray-900 tracking-tight">{post.titulo}</h3>
                                    <p className="text-gray-600 text-sm leading-relaxed whitespace-pre-wrap">{post.conteudo}</p>
                                </div>
                                <button
                                    onClick={() => handleExcluir(post.id)}
                                    className="self-end sm:self-start px-3 py-1.5 bg-red-50 text-red-600 rounded-xl text-xs font-bold hover:bg-red-100 transition-all duration-200 cursor-pointer"
                                    title="Excluir postagem"
                                >
                                    Excluir
                                </button>
                            </div>
                        ))
                    )}
                </section>

            </div>
        </div>
    );
}

export default Postagens;