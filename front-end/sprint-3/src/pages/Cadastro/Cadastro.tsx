import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

interface ApiResponse {
    token?: string;
    message?: string;
    error?: string;
}

export function Cadastro() {
    // Alterado de 'name' para 'nome' caso o backend exija em português
    const [nome, setNome] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(false);
    const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setLoading(true);
        setMessage(null);

        try {
            const response = await fetch('https://postup-api.onrender.com/postup/usuario', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                // Enviando 'nome' em vez de 'name' (padrão comum em APIs Spring Boot / PT-BR)
                body: JSON.stringify({
                    nome,
                    email,
                    senha: password // Se o seu backend espera 'password', mude de volta para 'password'
                }),
            });

            const contentType = response.headers.get('content-type');
            let data: ApiResponse = {};

            if (contentType && contentType.includes('application/json')) {
                data = await response.json();
            }

            if (response.ok) {
                setMessage({ type: 'success', text: 'Conta criada com sucesso! Redirecionando...' });

                if (data.token) {
                    localStorage.setItem('authToken', data.token);
                }

                setTimeout(() => {
                    navigate('/login');
                }, 1500);

            } else {
                // Exibe a mensagem exata retornada pela API, se houver
                const errorMsg = data.message || data.error || `Erro no cadastro (Status: ${response.status})`;
                setMessage({ type: 'error', text: errorMsg });
                console.error('Detalhes do erro da API:', data);
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            setMessage({
                type: 'error',
                text: 'Erro ao conectar com o servidor. Tente novamente mais tarde.'
            });
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="flex min-h-screen items-center justify-center bg-gradient from-gray-50 to-gray-100 font-sans px-4">
            <div className="w-full max-w-md rounded-2xl border border-[#e5e7eb] bg-white p-8 shadow-xl shadow-gray-100">

                <div className="mb-8 text-center">
                    <h1 className="text-3xl font-extrabold tracking-tight text-gray-900">
                        Post <span className="text-[#44c0c5]">Up</span>
                    </h1>
                    <p className="mt-2 text-sm text-gray-500">
                        Crie sua conta para começar
                    </p>
                </div>

                <form onSubmit={handleSubmit} className="flex flex-col gap-5">
                    <div className="flex flex-col gap-1.5">
                        <label className="text-sm font-semibold text-gray-700">Nome completo</label>
                        <input
                            type="text"
                            placeholder="Seu nome"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}
                            required
                            className="rounded-xl border border-[#e5e7eb] bg-gray-50/50 p-3.5 text-base text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-2 focus:ring-[#44c0c5]/20"
                        />
                    </div>

                    <div className="flex flex-col gap-1.5">
                        <label className="text-sm font-semibold text-gray-700">E-mail</label>
                        <input
                            type="email"
                            placeholder="seu@email.com"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            className="rounded-xl border border-[#e5e7eb] bg-gray-50/50 p-3.5 text-base text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-2 focus:ring-[#44c0c5]/20"
                        />
                    </div>

                    <div className="flex flex-col gap-1.5">
                        <label className="text-sm font-semibold text-gray-700">Senha</label>
                        <input
                            type="password"
                            placeholder="••••••••"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            className="rounded-xl border border-[#e5e7eb] bg-gray-50/50 p-3.5 text-base text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-2 focus:ring-[#44c0c5]/20"
                        />
                    </div>

                    {message && (
                        <div
                            className={`rounded-xl p-3.5 text-center text-sm font-medium animate-fadeIn ${message.type === 'error'
                                    ? 'bg-red-50 text-red-600 border border-red-100'
                                    : 'bg-emerald-50 text-emerald-600 border border-emerald-100'
                                }`}
                        >
                            {message.text}
                        </div>
                    )}

                    <button
                        type="submit"
                        disabled={loading}
                        className={`mt-2 rounded-xl bg-[#44c0c5] p-3.5 text-base font-bold text-white shadow-lg shadow-[#44c0c5]/25 transition-all duration-200 hover:bg-#3aaab0] active:scale-[0.99] ${loading ? 'cursor-not-allowed opacity-70' : 'cursor-pointer'
                            }`}
                    >
                        {loading ? 'Cadastrando...' : 'Cadastrar'}
                    </button>
                </form>

                <div className="mt-6 text-center text-sm text-gray-600">
                    Já tem uma conta?{' '}
                    <Link to="/login" className="font-semibold text-[#44c0c5] hover:underline">
                        Faça login
                    </Link>
                </div>

                <p className="mt-8 text-center text-xs text-gray-400">
                    Post Up Project &copy; {new Date().getFullYear()}
                </p>

            </div>
        </div>
    );
}

export default Cadastro;