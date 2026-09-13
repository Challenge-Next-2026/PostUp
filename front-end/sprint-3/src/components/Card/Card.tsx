

type TipoProps = {
    titulo: string;
    sub: string;
    descricao: string;

}

export default function Card({ titulo, sub, descricao }: TipoProps) {
    return (
                <div className="flex flex-col items-center justify-center text-center gap-6 max-w-4xl mx-auto">

            <h1 
                className="text-[4.5rem] sm:text-[calc(var(--titulo-hero)*1.2)] font-[var(--fonte-principal)] font-normal leading-tight"
                style={{ color: '#ffffff' }}
            >
                {titulo}
            </h1>

            {sub && (
                <h2 
                    className="text-[2.4rem] sm:text-[calc(var(--slogan)*1.2)] font-[var(--fonte-texto)] underline underline-offset-4 decoration-[var(--secondary)]"
                    style={{ color: '#ffffff' }}
                >
                    {sub}
                </h2>
            )}

            {descricao && (
                <p 
                    className="text-[1.8rem] sm:text-[2.6rem] font-[var(--fonte-texto)] max-w-2xl"
                    style={{ color: '#ffffff' }}
                >
                    {descricao}
                </p>
            )}

        </div>
    );
    }