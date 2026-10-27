import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

interface Postagem {
    id?: number;
    titulo?: string;
    conteudo?: string;
    // Adicione outros campos se a sua entidade possuir
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
                buscarPostagens(); // Atualiza a lista
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
        <div className="min-h-screen bg-gray-50 p-6 font-sans">
            <div className="max-w-4xl mx-auto">

                {/* Cabeçalho da Página */}
                <div className="flex justify-between items-center mb-8 bg-white p-6 rounded-2xl shadow-sm border border-gray-100">
                    <div>
                        <h1 className="text-2xl font-extrabold text-gray-900">
                            Post <span className="text-[#44c0c5]">Up</span> - Painel de Postagens
                        </h1>
                        <p className="text-sm text-gray-500">Gerencie suas publicações com segurança</p>
                    </div>
                    <button
                        onClick={handleLogout}
                        className="px-4 py-2 bg-red-50 text-red-600 rounded-xl font-semibold hover:bg-red-100 transition-all text-sm cursor-pointer"
                    >
                        Sair
                    </button>
                </div>

                {/* Formulário de Criação */}
                <div className="bg-white p-6 rounded-2xl shadow-sm border border-gray-100 mb-8">
                    <h2 className="text-lg font-bold text-gray-800 mb-4">Criar Nova Postagem</h2>

                    {message && (
                        <div className={`p-3 rounded-xl mb-4 text-sm font-medium ${message.type === 'error' ? 'bg-red-50 text-red-600' : 'bg-emerald-50 text-emerald-600'}`}>
                            {message.text}
                        </div>
                    )}

                    <form onSubmit={handleCriarPostagem} className="flex flex-col gap-4">
                        <input
                            type="text"
                            placeholder="Título da postagem"
                            value={titulo}
                            onChange={(e) => setTitulo(e.target.value)}
                            required
                            className="rounded-xl border border-gray-200 bg-gray-50/50 p-3 text-base text-gray-900 outline-none focus:border-[#44c0c5] focus:bg-white focus:ring-2 focus:ring-[#44c0c5]/20"
                        />
                        <textarea
                            placeholder="Escreva o conteúdo da postagem..."
                            value={conteudo}
                            onChange={(e) => setConteudo(e.target.value)}
                            required
                            rows={3}
                            className="rounded-xl border border-gray-200 bg-gray-50/50 p-3 text-base text-gray-900 outline-none focus:border-[#44c0c5] focus:bg-white focus:ring-2 focus:ring-[#44c0c5]/20 resize-none"
                        />
                        <button
                            type="submit"
                            disabled={loading}
                            className="rounded-xl bg-[#44c0c5] p-3 text-base font-bold text-white shadow-lg shadow-[#44c0c5]/25 transition-all hover:bg-[#3aaab0] cursor-pointer"
                        >
                            {loading ? 'Publicando...' : 'Publicar Postagem'}
                        </button>
                    </form>
                </div>

                {/* Lista de Postagens */}
                <div className="space-y-4">
                    <h2 className="text-xl font-bold text-gray-800">Postagens Recentes</h2>
                    {postagens.length === 0 ? (
                        <p className="text-gray-500 text-sm bg-white p-6 rounded-2xl border border-gray-100 text-center">Nenhuma postagem encontrada.</p>
                    ) : (
                        postagens.map((post) => (
                            <div key={post.id} className="bg-white p-6 rounded-2xl shadow-sm border border-gray-100 flex justify-between items-start">
                                <div>
                                    <h3 className="text-lg font-bold text-gray-900">{post.titulo}</h3>
                                    <p className="text-gray-600 mt-2 text-sm">{post.conteudo}</p>
                                </div>
                                <button
                                    onClick={() => handleExcluir(post.id)}
                                    className="text-red-500 hover:text-red-700 text-sm font-semibold p-2 cursor-pointer"
                                >
                                    Excluir
                                </button>
                            </div>
                        ))
                    )}
                </div>

            </div>
        </div>
    );
}

export default Postagens;