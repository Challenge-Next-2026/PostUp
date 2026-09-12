import Button from "../../components/Button/Button";

export default function Home(){
    return (
        <main className="conteudo">
            <section className="hero">
                <div className="hero__container">
                    <h1 className="hero__title">Post UP</h1>
                    <h2 className="hero__slogan">Eleve suas metas. Plante o futuro.</h2>
                    <p className="hero__desc">
                        Muito mais que um software, uma nova mentalidade.<br /> 
                        A cada clique um passo em direção a uma <br />
                        economia mais verde e circular.
                    </p>
                    <button className="hero__botao"><a href="./paginas/sistema.html">Conheça mais</a></button>
                </div>
            </section>
            <div className="w-full h-auto flex flex-col">
                {/* Seção do Projeto */}
                <section className="flex flex-col w-full h-auto gap-[3rem] bg-[var(--primary-dark)]">
                    
                    {/* Informações Principais */}
                    <section className="flex flex-col justify-center text-center text-[var(--bg-dark)] font-[var(--fonte-texto)] leading-[4rem]">
                    <h2 className="text-[length:var(--titulo-main)] font-semibold my-[6rem] p-[1rem] underline decoration-[var(--secondary)]">
                        Nossa ideia principal!
                    </h2>
                    <p className="text-[2rem] font-normal mx-[2.5rem] p-[1rem]">
                        Trabalhar em união direta com a empresa parceira Soul UP, <br />
                        para fazer um sistema de postagens sustentaveis que vai oferecer:
                    </p>
                    </section>

                    {/* Grid de Diferenciais */}
                    <div className="w-full h-1/2 grid grid-cols-[repeat(auto-fit,minmax(250px,1fr))] gap-4 max-[425px]:gap-4 border-b-[5px] border-[var(--secondary)]">
                    <section className="justify-center p-[1.5rem] bg-[var(--secondary-dark)] border-[3px] border-[var(--primary)] m-[7rem] mt-[6rem] leading-[2.5rem] rounded-[2rem] transition-all duration-300 ease-in-out hover:-translate-y-[6px] hover:shadow-[8px_8px_10px_rgb(255,255,255)]">
                        <h2 className="text-[2.2rem] text-center font-[var(--fonte-texto)] leading-[3rem] mb-[3rem] mt-[1rem] text-[var(--bg-dark)]">
                        ALGORITMO DE CLASSIFICAÇÃO
                        </h2>
                        <p className="text-[1.7rem] text-center mt-[2rem] font-[var(--fonte-texto)] font-normal leading-[2.5rem]">
                        Definir uma escala de importância perante as postagens em nível de sustentabilidade.
                        </p>
                    </section>

                    <section className="justify-center p-[1.5rem] bg-[var(--secondary-dark)] border-[3px] border-[var(--primary)] m-[7rem] mt-[6rem] leading-[2.5rem] rounded-[2rem] transition-all duration-300 ease-in-out hover:-translate-y-[6px] hover:shadow-[8px_8px_10px_rgb(255,255,255)]">
                        <h2 className="text-[2.2rem] text-center font-[var(--fonte-texto)] leading-[3rem] mb-[3rem] mt-[1rem] text-[var(--bg-dark)]">
                        SEGURANÇA E IA
                        </h2>
                        <p className="text-[1.7rem] text-center mt-[2rem] font-[var(--fonte-texto)] font-normal leading-[2.5rem]">
                        Camada projetada especificamente para prevenir atividades fraudulentas usando uma IA treinada.
                        </p>
                    </section>

                    <section className="justify-center p-[1.5rem] bg-[var(--secondary-dark)] border-[3px] border-[var(--primary)] m-[7rem] mt-[6rem] leading-[2.5rem] rounded-[2rem] transition-all duration-300 ease-in-out hover:-translate-y-[6px] hover:shadow-[8px_8px_10px_rgb(255,255,255)]">
                        <h2 className="text-[2.2rem] text-center font-[var(--fonte-texto)] leading-[3rem] mb-[3rem] mt-[1rem] text-[var(--bg-dark)]">
                        RANKING
                        </h2>
                        <p className="text-[1.7rem] text-center mt-[2rem] font-[var(--fonte-texto)] font-normal leading-[2.5rem]">
                        Sistema de comparação entre usuários.
                        </p>
                    </section>

                    <section className="justify-center p-[1.5rem] bg-[var(--secondary-dark)] border-[3px] border-[var(--primary)] m-[7rem] mt-[6rem] leading-[2.5rem] rounded-[2rem] transition-all duration-300 ease-in-out hover:-translate-y-[6px] hover:shadow-[8px_8px_10px_rgb(255,255,255)]">
                        <h2 className="text-[2.2rem] text-center font-[var(--fonte-texto)] leading-[3rem] mb-[3rem] mt-[1rem] text-[var(--bg-dark)]">
                        CAMADA DE INTEGRAÇÃO
                        </h2>
                        <p className="text-[1.7rem] text-center mt-[2rem] font-[var(--fonte-texto)] font-normal leading-[2.5rem]">
                        Interface amigável com proposta gamificada.
                        </p>
                    </section>
                    </div>
                </section>

                {/* Seção de Vídeo */}
                <section className="p-[3rem] w-full h-auto flex flex-col items-center bg-[var(--bg-dark)]">
                    <div className="text-center font-[var(--fonte-texto)] m-[6rem] gap-[5rem]">
                    <h2 className="text-[length:var(--titulo-main)] mb-[9rem] font-semibold underline leading-none decoration-[var(--primary)] max-[425px]:text-[3.5rem] max-[425px]:leading-[4rem]">
                        Vídeo Pitch Post UP
                    </h2>
                    <p className="text-[length:var(--sub-titulo)] font-normal max-[425px]:text-[2.5rem] max-[425px]:leading-[3rem]">
                        Entenda como transformamos propósito em ação no vídeo a seguir...
                    </p>
                    </div>

                    {/* Container Responsivo de Vídeo (Proporção 16/9) */}
                    <div className="w-full max-w-[800px] mx-auto aspect-video">
                    <iframe 
                        className="w-full h-full object-cover"
                        src="https://www.youtube.com/embed/VECOCcgvD8Q?si=o_zhMD7YNVaAJRRY" 
                        title="YouTube video player" 
                        frameBorder="0" 
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
                        referrerPolicy="strict-origin-when-cross-origin" 
                        allowFullScreen
                    ></iframe>
                    </div>
                </section>

                {/* Seção FAQ */}
                <div className="bg-[var(--primary)] w-full h-full flex justify-center items-center border-t-[5px] border-b-[5px] border-[var(--secondary)] leading-[3.3rem]">
                    <section className="w-[60%] max-[425px]:w-full h-1/2 bg-[var(--secondary-dark)] text-center p-[2rem] max-[425px]:p-[1rem] rounded-[1.5rem] m-[9rem] max-[425px]:m-[2rem] shadow-[7px_7px_7px_rgb(82,160,18)]">
                    <h2 className="text-[length:var(--sub-titulo)] font-[var(--fonte-texto)] font-semibold text-[var(--bg-dark)] m-[3rem]">
                        Ficou Com Alguma Dúvida ?
                    </h2>
                    <p className="text-[2rem] font-[var(--fonte-texto)] font-normal text-[var(--bg-dark)] mb-[4rem]">
                        Caso tenha alguma dúvida sobre a Post UP aqui está um FAQ de perguntas <br /> 
                        que recebemos frequentemente, a sua duvida pode estar lá !!
                    </p>
                    
                    <Button navegacao="/faq" texto="Tire sua duvida"/>
                    </section>
                </div>
                </div>
        </main>
)}