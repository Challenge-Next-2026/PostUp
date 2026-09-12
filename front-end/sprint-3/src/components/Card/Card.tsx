

type TipoProps = {
    titulo: string;
    sub: string;
    descricao: string;

}

export default function Card({ titulo, sub, descricao }: TipoProps) {
    return (
        <div className="flex flex-col items-center justify-center text-center gap-6 max-w-4xl mx-auto">

        <h1 className="text-white text-[3.5rem] sm:text-[var(--titulo-hero)] font-[var(--fonte-principal)] font-normal leading-tight">
            {titulo}
        </h1>


        {sub && (
            <h2 className="text-[var(--secondary)] text-[1.8rem] sm:text-[var(--slogan)] font-[var(--fonte-texto)] underline underline-offset-4 decoration-[var(--secondary)]">
            {sub}
            </h2>
        )}

        {descricao && (
            <p className="text-[var(--bg-dark)] text-[1.4rem] sm:text-[2rem] font-[var(--fonte-texto)] max-w-2xl">
            {descricao}
            </p>
        )}
    </div>
    );
    }