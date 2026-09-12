import { Link } from "react-router-dom";

type TipoProps = {
    navegacao: string;
    texto: string;

}

export default function Button({ navegacao, texto }: TipoProps) {
    return (
        <Link 
        to={navegacao} 
        className="inline-block border-y-2 border-[var(--primary)] text-[var(--text-main)] font-[var(--fonte-texto)] px-8 py-3 text-center no-underline transition-all duration-300 hover:bg-[var(--secondary)] hover:border-[var(--secondary-dark)] hover:text-white"
        >
        {texto}
        </Link>
    );
    }