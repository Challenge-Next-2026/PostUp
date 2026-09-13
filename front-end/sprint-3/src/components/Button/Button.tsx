import { Link } from "react-router-dom";

type TipoProps = {
    navegacao: string;
    texto: string;

}

export default function Button({ navegacao, texto }: TipoProps) {
    return (
        <Link 
            to={navegacao} 
            className="inline-block border-2 border-[var(--primary)] text-[var(--text-title)] font-[var(--fonte-texto)] px-8 py-2.5 rounded-full bg-[var(--text-main)]/40 backdrop-blur-md text-center no-underline transition-all duration-300 hover:bg-[var(--primary)] hover:border-[var(--primary-dark)] hover:text-[var(--text-main)] hover:shadow-[0_0_15px_var(--primary)]"
            >
            {texto}
        </Link>
    );
    }