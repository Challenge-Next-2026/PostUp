export default function Faq(){
    return (
        <main className="conteudo">
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
            <section className="perguntas__resposta__container">
                <h1 className="perguntas__resposta__titulo">A sua dúvida pode estar aqui!!!</h1>
                <section className="perguntas">
                    <h3>1) Como funciona o sistema de pontuação da Post UP?</h3>
                    <div className="dropdown">
                        <button className="btn">Ver Resposta </button>
                        <div className="respostas">
                            <p>A plataforma utiliza um sistema de score dinâmico baseado em impacto, frequência, dificuldade e confiabilidade das ações sustentáveis realizadas pelos usuários. Além disso, existe um ranking para incentivar a competitividade saudável entre os participantes.</p>
                        </div>
                    </div>
                </section>
                <section className="perguntas">
                    <h3>2) O que acontece quando um usuário acumula pontos?</h3>
                    <div className="dropdown">
                        <button className="btn">Ver Resposta </button>
                        <div className="respostas">
                            <p>Os usuários podem subir no ranking da plataforma e participar do sistema de recompensas baseado em ações sustentáveis e engajamento dentro da comunidade.</p>
                        </div>
                    </div>
                </section>
                <section className="perguntas">
                    <h3>3) Como os posts sustentáveis são classificados?</h3>
                    <div className="dropdown">
                        <button className="btn">Ver Resposta </button>
                        <div className="respostas">
                            <p>Os posts são avaliados através de um algoritmo de classificação que considera relevância, impacto ambiental e confiabilidade das informações compartilhadas.</p>
                        </div>
                    </div>
                </section>
                <section className="perguntas">
                    <h3>4) Qual o objetivo do ranking dentro da plataforma?</h3>
                    <div className="dropdown">
                        <button className="btn">Ver Resposta </button>
                        <div className="respostas">
                            <p>O ranking busca incentivar o engajamento e estimular os usuários a manterem hábitos sustentáveis de forma contínua e competitiva.</p>
                        </div>
                    </div>
                </section>
                <section className="perguntas">
                    <h3>5) Como a Post UP verifica se uma ação sustentável é verdadeira?</h3>
                    <div className="dropdown">
                        <button className="btn">Ver Resposta </button>
                        <div className="respostas">
                            <p>A plataforma utiliza inteligência artificial e validação por geolocalização para identificar atividades suspeitas e reduzir fraudes. Isso garante maior confiabilidade nas publicações e pontuações dos usuários.</p>
                        </div>
                    </div>
                </section>
            </section>
        </main>
)}