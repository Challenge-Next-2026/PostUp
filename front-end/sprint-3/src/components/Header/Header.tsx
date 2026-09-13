import { Link, useNavigate} from "react-router-dom";
import logoBranca from "../../img/logo_branca.png";


export default function Header() {
    // 1. Hook para navegação programática (via código/funções)
    const navigate = useNavigate();



    const handleNavegar = (rota: string) => {
    navigate(rota);
};

    return (
            <header className="sticky top-0 z-50 w-full bg-[var(--primary-dark)] text-[var(--text-title)] font-[var(--fonte-texto)] p-[1.5rem_2rem] border-b-[5px]">
            <nav className="w-full">
                <ul className="flex w-full items-center justify-around list-none m-0 p-0">
                
                <li className="w-[12%] min-w-[40px] flex justify-start items-center">
                    <Link to="/">
                    <img
                        src={logoBranca}
                        alt="Logo Post Up Branca"
                        className="w-full max-w-[50px] h-auto object-contain block"
                    />
                    </Link>
                </li>

                <li className="text-[var(--text-title)] text-[1.6rem] sm:text-[2.2rem] hover:opacity-80 transition-opacity">
                    <Link to="/sobre">Sobre</Link>
                </li>

                <li className="text-[var(--text-title)] text-[1.6rem] sm:text-[2.2rem] hover:opacity-80 transition-opacity">
                    <Link to="/quem-somos">Quem Somos</Link>
                </li>

                <li className="text-[var(--text-title)] text-[1.6rem] sm:text-[2.2rem] hover:opacity-80 transition-opacity">
                    <Link to="/sistema">Sistema</Link>
                </li>

                <li className="text-[var(--text-title)] text-[1.6rem] sm:text-[2.2rem] hover:opacity-80 transition-opacity">
                    <button 
                    type="button" 
                    onClick={() => handleNavegar("/faq")}
                    className="bg-transparent border-none cursor-pointer text-inherit font-inherit p-0"
                    >
                    FAQ
                    </button>
                </li>

                {/* Exemplo com parâmetro dinâmico via Link ou Navegação direta */}
                <li className="text-[var(--text-title)] text-[1.6rem] sm:text-[2.2rem] hover:opacity-80 transition-opacity">
                    <Link to="/contato">Contato</Link>
                </li>

                </ul>
            </nav>
            </header>
);
}