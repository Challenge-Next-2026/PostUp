import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

interface ApiResponse {
    token?: string;
    message?: string;
    error?: string;
}

export function Cadastro() {
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
                body: JSON.stringify({
                    nome,
                    email,
                    senha: password
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
        <div className="flex min-h-screen items-center justify-center bg-gradient-to-br from-gray-50 to-gray-100 font-sans px-4 py-8">
            <div className="w-full max-w-xl rounded-[2.5rem] border border-gray-200 bg-white p-12 sm:p-14 shadow-2xl shadow-gray-200/60">

                {/* Cabeçalho extra grande */}
                <div className="mb-12 text-center">
                    <h1 className="text-5xl font-black tracking-tight text-gray-900">
                        Post <span className="text-[#44c0c5]">Up</span>
                    </h1>
                    <p className="mt-4 text-lg font-medium text-gray-500">
                        Crie sua conta para começar
                    </p>
                </div>

                {/* Formulário com espaçamentos e fontes maiores */}
                <form onSubmit={handleSubmit} className="flex flex-col gap-7">
                    <div className="flex flex-col gap-2.5">
                        <label className="text-base font-bold text-gray-700">Nome completo</label>
                        <input
                            type="text"
                            placeholder="Seu nome"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}
                            required
                            className="rounded-2xl border border-gray-200 bg-gray-50/50 p-5 text-lg text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-4 focus:ring-[#44c0c5]/20"
                        />
                    </div>

                    <div className="flex flex-col gap-2.5">
                        <label className="text-base font-bold text-gray-700">E-mail</label>
                        <input
                            type="email"
                            placeholder="seu@email.com"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            className="rounded-2xl border border-gray-200 bg-gray-50/50 p-5 text-lg text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-4 focus:ring-[#44c0c5]/20"
                        />
                    </div>

                    <div className="flex flex-col gap-2.5">
                        <label className="text-base font-bold text-gray-700">Senha</label>
                        <input
                            type="password"
                            placeholder="••••••••"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            className="rounded-2xl border border-gray-200 bg-gray-50/50 p-5 text-lg text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-4 focus:ring-[#44c0c5]/20"
                        />
                    </div>

                    {message && (
                        <div
                            className={`rounded-2xl p-5 text-center text-lg font-bold ${
                                message.type === 'error'
                                    ? 'bg-red-50 text-red-600 border border-red-200'
                                    : 'bg-emerald-50 text-emerald-600 border border-emerald-200'
                            }`}
                        >
                            {message.text}
                        </div>
                    )}

                    <button
                        type="submit"
                        disabled={loading}
                        className={`mt-3 rounded-2xl bg-[#44c0c5] p-5 text-xl font-extrabold text-white shadow-xl shadow-[#44c0c5]/30 transition-all duration-200 hover:bg-[#3aaab0] active:scale-[0.99] ${
                            loading ? 'cursor-not-allowed opacity-70' : 'cursor-pointer'
                        }`}
                    >
                        {loading ? 'Cadastrando...' : 'Cadastrar'}
                    </button>
                </form>

                {/* Rodapé ampliado */}
                <div className="mt-10 text-center text-lg text-gray-600">
                    Já tem uma conta?{' '}
                    <Link to="/login" className="font-extrabold text-[#44c0c5] hover:underline">
                        Faça login
                    </Link>
                </div>

                <p className="mt-12 text-center text-base text-gray-400 font-medium">
                    Post Up Project &copy; {new Date().getFullYear()}
                </p>

            </div>
        </div>
    );
}

export default Cadastro;