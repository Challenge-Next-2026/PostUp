export default function Contato(){
    return (
        <main className="conteudo">
            <section className="hero">
                <div className="hero__container">
                    <h1 className="hero__title">Contato</h1>
                    <h2 className="hero__slogan">Fale conosco!!</h2>
                    <p className="hero__desc">
                        Na Post UP, nós acreditamos que grandes parcerias começam com uma boa conversa. Se você quer tirar uma ideia do papel, <br /> 
                        escalar os seus resultados ou apenas entender como podemos ajudar a sua empresa a crescer, você está no lugar certo.
                    </p>
                    <button className="hero__botao"><a href="./sistema.html">Conheça mais</a></button>
                </div>
            </section>
            <div className="contato__container">
                <h2 className="contato__titulo">Entre em contato com a equipe da Post UP</h2>
                <p className="contato__texto">
                    Nesta página, você pode tirar dúvidas, enviar sugestões, compartilhar ideias e falar diretamente com nossa equipe. A Post UP está sempre aberta para ouvir a comunidade e fortalecer conexões em prol da inovação e da sustentabilidade.
                </p>
                <section className="contatos__meios">
                    <p className="contato__email"><img src="../img/icone_email.png" alt="icone de email" />challengecfgr.2026@gmail.com</p>
                    <form className="formulario" action="challengecfgr.2026@gmail.com" method="POST">
                        <p>
                            Envie sua mensagem para a equipe da Post UP.
                            Responderemos o mais rápido possível.
                        </p>

                        <div className="formulario__caixa">
                            <input
                                type="text"
                                name="nome"
                                placeholder="Seu nome"
                                required
                            />
                        </div>

                        <div className="formulario__caixa">
                            <input
                                type="email"
                                name="email"
                                placeholder="Seu email"
                                required
                            />
                        </div>

                        <div className="formulario__caixa">
                            <textarea
                                name="mensagem"
                                placeholder="Digite sua mensagem"
                                required
                            >
                            </textarea>
                        </div>

                        <button className="btn__formulario" type="submit">
                            Enviar mensagem
                        </button>
                    </form>
                </section>
            </div>
        </main>
)}