import linkedin from "../../img/linkedin_logo.png";
import github from "../../img/github_logo.png";

export interface Integrante {
    nome: string;
    rm: string;
    turma: string;
    foto: string;
    descricao: string;
    linkedin: string;
    github: string;
    fundo: "primary" | "secondary";
}

interface IntegranteCardProps {
    integrante: Integrante;
}

export default function IntegranteCard({
    integrante
}: IntegranteCardProps) {

    const fundo =
        integrante.fundo === "primary"
            ? "bg-[var(--primary)] shadow-[0_10px_45px_rgba(68,192,197,0.45)]"
            : "bg-[var(--secondary-dark)] shadow-[0_10px_40px_rgba(121,155,0,0.4)]";

    return (
        <section
            className={`w-full flex flex-col md:flex-row items-center p-8 md:p-24 lg:p-32 gap-12 lg:gap-24 border-t-[10px] border-[var(--bg-dark)] ${fundo}`}
        >

            {/* Foto do integrante */}
            <img
                className="w-full md:w-[23vw] max-w-[320px] md:max-w-none h-auto border-[5px] border-[var(--secondary)] rounded-[1.5rem] object-cover shrink-0"
                src={integrante.foto}
                alt={`Foto de ${integrante.nome}`}
            />

            {/* Informações do integrante */}
            <div className="flex flex-col gap-10 md:gap-14 justify-center w-full">

                {/* Nome */}
                <h2 className="text-3xl md:text-5xl lg:text-[var(--titulo-hero)] font-[var(--fonte-texto)] font-bold text-[var(--bg-dark)] text-center md:text-left">
                    {integrante.nome}
                </h2>

                {/* Card de informações */}
                <div className="w-full bg-[var(--bg-primary)] border-4 border-[var(--secondary)] rounded-[1.5rem] shadow-[7px_7px_7px_rgba(255,255,255,1)] p-8 md:p-14 flex flex-col gap-8">

                    {/* Descrição */}
                    <p className="text-lg md:text-[2.2rem] font-[var(--fonte-texto)] text-center leading-relaxed md:leading-[3.5rem]">
                        {integrante.descricao}
                    </p>

                    {/* Informações acadêmicas */}
                    <div className="flex flex-col md:flex-row items-center justify-center gap-4 md:gap-8 pt-4">

                        <span className="text-xl md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold underline">
                            {integrante.rm}
                        </span>

                        <span className="text-xl md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold">
                            Turma: {integrante.turma}
                        </span>

                    </div>

                    {/* Redes sociais */}
                    <div className="flex items-center justify-center gap-8 pt-2">

                        {/* LinkedIn */}
                        <a
                            href={integrante.linkedin}
                            target="_blank"
                            rel="noopener noreferrer"
                            aria-label={`LinkedIn de ${integrante.nome}`}
                        >
                            <img
                                src={linkedin}
                                alt="LinkedIn"
                                className="w-12 h-12 object-contain"
                            />
                        </a>

                        {/* GitHub */}
                        <a
                            href={integrante.github}
                            target="_blank"
                            rel="noopener noreferrer"
                            aria-label={`GitHub de ${integrante.nome}`}
                        >
                            <img
                                src={github}
                                alt="GitHub"
                                className="w-12 h-12 object-contain"
                            />
                        </a>

                    </div>

                </div>

            </div>

        </section>
    );
}