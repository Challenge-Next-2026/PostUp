interface FAQItemProps {
    pergunta: string;
    resposta: string;
    aberta: boolean;
    onToggle: () => void;
}

export default function FAQItem({
    pergunta,
    resposta,
    aberta,
    onToggle
}: FAQItemProps) {

    return (
        <section className="relative w-auto flex flex-col justify-center gap-[3rem] md:gap-[4rem] m-[1.6rem] md:m-[7rem] p-[1rem] bg-[var(--primary-dark)] rounded-[3rem] shadow-[7px_7px_7px_rgb(82,160,18)] border-3 border-[var(--secondary)]">
            <h3 className="text-[1.5rem] md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold text-center text-[var(--bg-primary)] mt-8 leading-[2.5rem] md:leading-normal">
                {pergunta}
            </h3>
            <div className="relative inline-block text-center pb-4">
                <button
                    onClick={onToggle}
                    className="bg-transparent text-[var(--bg-primary)] px-[20px] py-[12px] border-3 border-[var(--secondary-dark)] cursor-pointer rounded-[3rem] transition-all duration-300 hover:bg-[var(--bg-primary)] hover:text-[var(--secondary-dark)]"
                >
                    {aberta ? "Ocultar Resposta" : "Ver Resposta"}
                </button>
                {aberta && (
                    <div className="absolute left-0 right-0 mx-auto w-full min-w-[160px] bg-[var(--secondary-dark)] shadow-[0_5px_15px_rgba(0,0,0,0.2)] rounded-[10px] overflow-hidden z-10">
                        <p className="block p-[12px] text-[var(--bg-primary)]">
                            {resposta}
                        </p>
                    </div>
                )}
            </div>
        </section>
    );
}