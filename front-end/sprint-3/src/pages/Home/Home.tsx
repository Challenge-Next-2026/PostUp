import { Link } from "react-router-dom";

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
            <div className="conteudo__container">
                <section className="conteudo__projeto">
                    <section className="conteudo__info">
                        <h2 className="conteudo__info__titulo">Nossa ideia principal!</h2>
                        <p className="conteudo__info__texto">
                            Trabalhar em união direta com a empresa parceira Soul UP, <br />
                            para fazer um sistema de postagens sustentaveis que vai oferecer:
                        </p>
                    </section>
                    <div className="conteudo__diferenciais__container">
                        <section className="conteudo__diferenciais">
                            <h2 className="conteudo__diferenciais__titulo">ALGORITMO DE CLASSIFICAÇÃO</h2>
                            <p className="conteudo__diferenciais__texto">Definir uma escala de importância perante as postagens em nível de sustentabilidade.</p>
                        </section>
                        <section className="conteudo__diferenciais">
                            <h2 className="conteudo__diferenciais__titulo">SEGURANÇA E IA</h2>
                            <p className="conteudo__diferenciais__texto">Camada projetada especificamente para prevenir atividades fraudulentas usando uma IA treinada.</p>
                        </section>
                        <section className="conteudo__diferenciais">
                            <h2 className="conteudo__diferenciais__titulo">RANKING</h2>
                            <p className="conteudo__diferenciais__texto">Sistema de comparação entre usuários.</p>
                        </section>
                        <section className="conteudo__diferenciais">
                            <h2 className="conteudo__diferenciais__titulo">CAMADA DE INTEGRAÇÃO</h2>
                            <p className="conteudo__diferenciais__texto">Interface amigável com proposta gamificada.</p>
                        </section>
                    </div>
                </section>
                <section className="conteudo__sec">
                    <div className="conteudo__texto__video">
                        <h2 className="conteudo__texto__titulo">Vídeo Pitch Post UP</h2>
                        <p className="conteudo__texto__descricao">Entenda como transformamos propósito em ação no vídeo a seguir...</p>
                    </div>
                    <div className="conteudo__video__container">
                        <iframe 
                            width="560" 
                            height="315" 
                            src="https://www.youtube.com/embed/VECOCcgvD8Q?si=o_zhMD7YNVaAJRRY" 
                            title="YouTube video player" 
                            frameBorder="0" 
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
                            referrerPolicy="strict-origin-when-cross-origin" 
                            allowFullScreen
                        ></iframe>
                    </div>
                </section>

                <div className="conteudo__faq__container">
                    <section className="conteudo__faq">
                        <h2 className="conteudo__faq__titulo">Ficou Com Alguma Dúvida ?</h2>
                        <p className="conteudo__faq__texto">
                            Caso tenha alguma dúvida sobre a Post UP aqui está um FAQ de perguntas <br /> 
                            que recebemos frequentemente, a sua duvida pode estar lá !!
                        </p>
                        <button className="conteudo__faq__botao"><Link to="/faq">Tire sua duvida</Link></button>
                    </section>
                </div>
            </div>
        </main>
)}