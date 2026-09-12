import { Link } from "react-router-dom";
import logoBranca from "../../img/logo_branca.png";

    export default function Header() {
    return (
    <header className="sticky top-0 w-full h-auto p-[2rem] bg-[var(--primary-dark)] text-[var(--text-main)] font-[var(--fonte-texto)] text-[length:var(--sub-titulo)] border-b-[5px] border-[var(--secondary-dark)] shadow-[inset_2px_2px_5px_rgba(0,0,0,1.5)]">
        <nav className="w-full h-full">
            <ul className="flex w-full h-full items-center justify-around m-0 p-0 list-none">
            
            {/* Logo */}
            <li className="w-[30%] min-[426px]:w-[10%] flex justify-start">
                <Link to="/">
                <img src={logoBranca} alt="Logo Post Up Branca" className="w-full h-auto" />
                </Link>
            </li>

            {/* Links do Menu */}
            <li className="m-0 p-[1rem] text-[var(--bg-dark)] font-normal text-[1.6rem] min-[426px]:text-[length:var(--sub-titulo)]">
                <Link to="/sobre">Sobre</Link>
            </li>
            <li className="m-0 p-[1rem] text-[var(--bg-dark)] font-normal text-[1.6rem] min-[426px]:text-[length:var(--sub-titulo)]">
                <Link to="/quem-somos">Quem Somos</Link>
            </li>
            <li className="m-0 p-[1rem] text-[var(--bg-dark)] font-normal text-[1.6rem] min-[426px]:text-[length:var(--sub-titulo)]">
                <Link to="/sistema">Sistema</Link>
            </li>
            <li className="m-0 p-[1rem] text-[var(--bg-dark)] font-normal text-[1.6rem] min-[426px]:text-[length:var(--sub-titulo)]">
                <Link to="/faq">FAQ</Link>
            </li>

            {/* Contato */}
            <li className="p-[1rem] flex justify-end text-[1.4rem] min-[426px]:text-[2rem]">
                <Link to="/contato">Contato</Link>
            </li>

            </ul>
        </nav>
    </header>
    );
    }