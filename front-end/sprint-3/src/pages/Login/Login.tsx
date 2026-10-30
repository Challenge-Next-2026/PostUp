import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export function Login() {
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
            const response = await fetch('https://postup-api.onrender.com/postup/usuario/login', {
                method: 'POST',
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                body: JSON.stringify({
                    email: email.trim(),
                    senha: password
                }),
            });

            const responseText = await response.text();
            let data: any = {};
            
            try {
                data = JSON.parse(responseText);
            } catch {
                data = responseText; // Caso a API retorne o token diretamente como uma string de texto puro
            }

            console.log("Resposta da API de Login:", data);

            if (response.ok) {
                setMessage({
                    type: 'success',
                    text: 'Login realizado com sucesso! Redirecionando...'
                });

                sessionStorage.setItem('isLoggedIn', 'true');

                setTimeout(() => {
                    navigate('/postagens');
                }, 1200);
                
            } else {
                const errorMsg = (typeof data === 'object' && (data.message || data.error)) || responseText || 'E-mail ou senha inválidos.';
                setMessage({ type: 'error', text: errorMsg });
                setLoading(false);
            }
        } catch (error) {
            console.error('Erro de conexão:', error);
            setMessage({
                type: 'error',
                text: 'Erro ao conectar com o servidor. Verifique sua conexão.'
            });
            setLoading(false);
        }
    };

    return (
        <div className="flex min-h-screen items-center justify-center bg-gradient-to-br from-gray-50 to-gray-100 font-sans px-4">
            <div className="w-full max-w-md rounded-2xl border border-gray-200 bg-white p-8 shadow-xl shadow-gray-100">

                <div className="mb-8 text-center">
                    <h1 className="text-3xl font-extrabold tracking-tight text-gray-900">
                        Post <span className="text-[#44c0c5]">Up</span>
                    </h1>
                    <p className="mt-2 text-sm text-gray-500">
                        Entre com sua conta para continuar
                    </p>
                </div>

                <form onSubmit={handleSubmit} className="flex flex-col gap-5">
                    <div className="flex flex-col gap-1.5">
                        <label className="text-sm font-semibold text-gray-700">E-mail</label>
                        <input
                            type="email"
                            placeholder="seu@email.com"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            className="rounded-xl border border-gray-200 bg-gray-50/50 p-3.5 text-base text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-2 focus:ring-[#44c0c5]/20"
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
                            className="rounded-xl border border-gray-200 bg-gray-50/50 p-3.5 text-base text-gray-900 outline-none transition-all duration-200 focus:border-[#44c0c5] focus:bg-white focus:ring-2 focus:ring-[#44c0c5]/20"
                        />
                    </div>

                    {message && (
                        <div
                            className={`rounded-xl p-3.5 text-center text-sm font-medium ${
                                message.type === 'error'
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
                        className={`mt-2 rounded-xl bg-[#44c0c5] p-3.5 text-base font-bold text-white shadow-lg shadow-[#44c0c5]/25 transition-all duration-200 hover:bg-[#3aaab0] active:scale-[0.99] ${
                            loading ? 'cursor-not-allowed opacity-70' : 'cursor-pointer'
                        }`}
                    >
                        {loading ? 'Verificando...' : 'Entrar'}
                    </button>
                </form>

                <div className="mt-6 text-center text-sm text-gray-600">
                    Ainda não tem uma conta?{' '}
                    <Link to="/cadastro" className="font-semibold text-[#44c0c5] hover:underline">
                        Cadastre-se
                    </Link>
                </div>

                <p className="mt-8 text-center text-xs text-gray-400">
                    Post Up Project &copy; {new Date().getFullYear()}
                </p>

            </div>
        </div>
    );
}

export default Login;