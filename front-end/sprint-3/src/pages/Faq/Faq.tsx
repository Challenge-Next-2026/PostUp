import Card from "../../components/Card/Card";
import imgFundo from "../../img/fundo2.jpg"
import Button from "../../components/Button/Button";

export default function Faq(){
    return (
        <main className="conteudo">
            <section 
                className="relative w-full h-[calc(100vh-80px)] bg-cover bg-center bg-no-repeat border-b-[5px] border-[var(--secondary-dark)] flex items-center justify-center"
                style={{
                    backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.65), rgba(0, 0, 0, 0.65)), url(${imgFundo})`
                }}
                >
                <div className="text-center px-4">
                    <Card 
                    titulo="FAQ" 
                    sub="Algumas perguntas e respostas" 
                    descricao="O FAQ da Post UP reúne perguntas e respostas rápidas para ajudar os usuários a entenderem o funcionamento da <br />
                        plataforma, suas funcionalidades e o sistema de sustentabilidade e recompensas." 
                    />
                    <Button navegacao="/sistema" texto="Conheça mais"/>
                </div>
            </section>
            <section className="hero">
                <div className="hero__container">
                    <h1 className="hero__title">FAQ</h1>
                    <h2 className="hero__slogan">Algumas perguntas e respostas</h2>
                    <p className="hero__desc">
                        O FAQ da Post UP reúne perguntas e respostas rápidas para ajudar os usuários a entenderem o funcionamento da <br />
                        plataforma, suas funcionalidades e o sistema de sustentabilidade e recompensas.
                    </p>
                    <button className="hero__botao"><a href="./sistema.html">Conheça mais</a></button>
                </div>
            </section>
            
        <section className="w-full h-auto">
            
            {/* .perguntas__resposta__titulo */}
            <h1 className="text-2xl sm:text-3xl md:text-4xl font-[var(--fonte-texto)] font-bold text-center bg-[var(--secondary)] border-3 border-[var(--primary)] rounded-[3rem] text-[var(--bg-primary)] m-4 sm:m-8 md:m-[4.5rem] p-4 md:p-8">
                A sua dúvida pode estar aqui!!!
            </h1>

            
            <section className="group relative w-auto flex flex-col justify-center gap-[3rem] md:gap-[4rem] m-[1.6rem] md:m-[7rem] p-[1rem] bg-[var(--primary-dark)] rounded-[3rem] shadow-[7px_7px_7px_rgb(82,160,18)] border-3 border-[var(--secondary)]">
                <h3 className="text-[1.5rem] md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold text-center text-[var(--bg-primary)] mt-8 leading-[2.5rem] md:leading-normal">
                1) Como funciona o sistema de pontuação da Post UP?
                </h3>
                
                
                <div className="relative inline-block text-center pb-4">
                
                <button className="bg-transparent text-[var(--bg-primary)] px-[20px] py-[12px] border-3 border-[var(--secondary-dark)] cursor-pointer rounded-[3rem] transition-all duration-1000 hover:bg-[var(--bg-primary)] hover:text-[var(--secondary-dark)]">
                    Ver Resposta
                </button>
                
                
                <div className="hidden group-hover:block absolute left-0 right-0 mx-auto w-full min-w-[160px] bg-[var(--secondary-dark)] shadow-[0_5px_15px_rgba(0,0,0,0.2)] rounded-[10px] overflow-hidden z-10">
                    <p className="block p-[12px] text-[var(--bg-primary)] transition-colors duration-[3000ms] hover:bg-[#f1f1f1] hover:text-black">
                    A plataforma utiliza um sistema de score dinâmico baseado em impacto, frequência, dificuldade e confiabilidade das ações sustentáveis realizadas pelos usuários. Além disso, existe um ranking para incentivar a competitividade saudável entre os participantes.
                    </p>
                </div>
                </div>
            </section>

            
            <section className="group relative w-auto flex flex-col justify-center gap-[3rem] md:gap-[4rem] m-[1.6rem] md:m-[7rem] p-[1rem] bg-[var(--primary-dark)] rounded-[3rem] shadow-[7px_7px_7px_rgb(82,160,18)] border-3 border-[var(--secondary)]">
                <h3 className="text-[1.5rem] md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold text-center text-[var(--bg-primary)] mt-8 leading-[2.5rem] md:leading-normal">
                2) O que acontece quando um usuário acumula pontos?
                </h3>
                <div className="relative inline-block text-center pb-4">
                <button className="bg-transparent text-[var(--bg-primary)] px-[20px] py-[12px] border-3 border-[var(--secondary-dark)] cursor-pointer rounded-[3rem] transition-all duration-1000 hover:bg-[var(--bg-primary)] hover:text-[var(--secondary-dark)]">
                    Ver Resposta
                </button>
                <div className="hidden group-hover:block absolute left-0 right-0 mx-auto w-full min-w-[160px] bg-[var(--secondary-dark)] shadow-[0_5px_15px_rgba(0,0,0,0.2)] rounded-[10px] overflow-hidden z-10">
                    <p className="block p-[12px] text-[var(--bg-primary)] transition-colors duration-[3000ms] hover:bg-[#f1f1f1] hover:text-black">
                    Os usuários podem subir no ranking da plataforma e participar do sistema de recompensas baseado em ações sustentáveis e engajamento dentro da comunidade.
                    </p>
                </div>
                </div>
            </section>

            
            <section className="group relative w-auto flex flex-col justify-center gap-[3rem] md:gap-[4rem] m-[1.6rem] md:m-[7rem] p-[1rem] bg-[var(--primary-dark)] rounded-[3rem] shadow-[7px_7px_7px_rgb(82,160,18)] border-3 border-[var(--secondary)]">
                <h3 className="text-[1.5rem] md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold text-center text-[var(--bg-primary)] mt-8 leading-[2.5rem] md:leading-normal">
                3) Como os posts sustentáveis são classificados?
                </h3>
                <div className="relative inline-block text-center pb-4">
                <button className="bg-transparent text-[var(--bg-primary)] px-[20px] py-[12px] border-3 border-[var(--secondary-dark)] cursor-pointer rounded-[3rem] transition-all duration-1000 hover:bg-[var(--bg-primary)] hover:text-[var(--secondary-dark)]">
                    Ver Resposta
                </button>
                <div className="hidden group-hover:block absolute left-0 right-0 mx-auto w-full min-w-[160px] bg-[var(--secondary-dark)] shadow-[0_5px_15px_rgba(0,0,0,0.2)] rounded-[10px] overflow-hidden z-10">
                    <p className="block p-[12px] text-[var(--bg-primary)] transition-colors duration-[3000ms] hover:bg-[#f1f1f1] hover:text-black">
                    Os posts são avaliados através de um algoritmo de classificação que considera relevância, impacto ambiental e confiabilidade das informações compartilhadas.
                    </p>
                </div>
                </div>
            </section>

            
            <section className="group relative w-auto flex flex-col justify-center gap-[3rem] md:gap-[4rem] m-[1.6rem] md:m-[7rem] p-[1rem] bg-[var(--primary-dark)] rounded-[3rem] shadow-[7px_7px_7px_rgb(82,160,18)] border-3 border-[var(--secondary)]">
                <h3 className="text-[1.5rem] md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold text-center text-[var(--bg-primary)] mt-8 leading-[2.5rem] md:leading-normal">
                4) Qual o objetivo do ranking dentro da plataforma?
                </h3>
                <div className="relative inline-block text-center pb-4">
                <button className="bg-transparent text-[var(--bg-primary)] px-[20px] py-[12px] border-3 border-[var(--secondary-dark)] cursor-pointer rounded-[3rem] transition-all duration-1000 hover:bg-[var(--bg-primary)] hover:text-[var(--secondary-dark)]">
                    Ver Resposta
                </button>
                <div className="hidden group-hover:block absolute left-0 right-0 mx-auto w-full min-w-[160px] bg-[var(--secondary-dark)] shadow-[0_5px_15px_rgba(0,0,0,0.2)] rounded-[10px] overflow-hidden z-10">
                    <p className="block p-[12px] text-[var(--bg-primary)] transition-colors duration-[3000ms] hover:bg-[#f1f1f1] hover:text-black">
                    O ranking busca incentivar o engajamento e estimular os usuários a manterem hábitos sustentáveis de forma contínua e competitiva.
                    </p>
                </div>
                </div>
            </section>

            
            <section className="group relative w-auto flex flex-col justify-center gap-[3rem] md:gap-[4rem] m-[1.6rem] md:m-[7rem] p-[1rem] bg-[var(--primary-dark)] rounded-[3rem] shadow-[7px_7px_7px_rgb(82,160,18)] border-3 border-[var(--secondary)]">
                <h3 className="text-[1.5rem] md:text-[var(--sub-titulo)] font-[var(--fonte-texto)] font-bold text-center text-[var(--bg-primary)] mt-8 leading-[2.5rem] md:leading-normal">
                5) Como a Post UP verifica se uma ação sustentável é verdadeira?
                </h3>
                <div className="relative inline-block text-center pb-4">
                <button className="bg-transparent text-[var(--bg-primary)] px-[20px] py-[12px] border-3 border-[var(--secondary-dark)] cursor-pointer rounded-[3rem] transition-all duration-1000 hover:bg-[var(--bg-primary)] hover:text-[var(--secondary-dark)]">
                    Ver Resposta
                </button>
                <div className="hidden group-hover:block absolute left-0 right-0 mx-auto w-full min-w-[160px] bg-[var(--secondary-dark)] shadow-[0_5px_15px_rgba(0,0,0,0.2)] rounded-[10px] overflow-hidden z-10">
                    <p className="block p-[12px] text-[var(--bg-primary)] transition-colors duration-[3000ms] hover:bg-[#f1f1f1] hover:text-black">
                    A plataforma utiliza inteligência artificial e validação por geolocalização para identificar atividades suspeitas e reduzir fraudes. Isso garante maior confiabilidade nas publicações e pontuações dos usuários.
                    </p>
                </div>
                </div>
            </section>

        </section>
    </main>
)}