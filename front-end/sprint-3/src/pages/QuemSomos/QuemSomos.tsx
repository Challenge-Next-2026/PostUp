import giovanni from "../../img/IMG-20260328-WA0007.jpg";
import raphael from "../../img/3x4 Raphael Gomes.jpeg";
import felipe from "../../img/image.png";
import linkedin from "../../img/linkedin_logo.png";
import github from "../../img/github_logo.png";


export default function QuemSomos(){
    return (
        <main className="conteudo">
            <section className="hero">
                <div className="hero__container">
                    <h1 className="hero__title">Quem Somos</h1>
                    <h2 className="hero__slogan">Profissionais Qualificados.</h2>
                    <p className="hero__desc">
                        Profissionais apaixonados por inovação, tecnologia e impacto social. <br /> 
                        Queremos transformar ideias em soluções sustentáveis,<br /> 
                        criando experiências que conectam pessoas, propósito e crescimento.
                    </p>
                    <button className="hero__botao"><a href="./sistema.html">Conheça mais</a></button>
                </div>
            </section>
            
            <div className="quem__somos__container">
                <section className="quem__somos">
                    <div className="quem__somos__aviso">
                        <h2 className="quem__somos__titulo">Equipe Post UP</h2>
                        <p className="quem__somos__paragrafo">
                            Movidos pela inovação, criatividade e compromisso com a sustentabilidade. <br /> 
                            Nossa equipe trabalha para desenvolver soluções modernas e eficientes, conectando tecnologia <br /> 
                            e impacto positivo para construir um futuro mais consciente e sustentável.
                        </p>
                    </div>
                </section>

                <section className="quem__somos__profissional__1">
                    <img className="quem__somos__img" src={giovanni}alt="profissonal Soul UP" />
                    <section className="quem__somos__texto">
                        <h2 className="quem__somos__nome">Giovanni Zorzetto</h2>
                        <div className="quem__somos__info">
                            <p className="quem__somos__experiencia">“A Post UP me mostrou como a tecnologia pode gerar impacto positivo de verdade. Trabalhar em equipe e desenvolver soluções sustentáveis foi uma experiência incrível.”</p>
                            <ul className="quem__somos__lista">
                                <li className="quem__somos__item">RM569464</li>
                                <li className="quem__somos__item">
                                    <a href="https://www.linkedin.com/in/giovanni-zorzetto-oliveira-8375b9305/">
                                        <img src={linkedin} alt="linkedin logo" />
                                    </a>
                                </li>
                                <li className="quem__somos__item">
                                    <a href="https://github.com/Gizetto61">
                                        <img src={github} alt="GitHUb logo" />
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </section>
                </section>

                <section className="quem__somos__profissional__2">
                    <img className="quem__somos__img" src={raphael} alt="profissonal Soul UP" />
                    <section className="quem__somos__texto">
                        <h2 className="quem__somos__nome">Raphael Gomes</h2>
                        <div className="quem__somos__info">
                            <p className="quem__somos__experiencia">“Participar da Post UP ampliou minha visão sobre inovação e colaboração. Foi uma oportunidade de crescer profissionalmente e contribuir para algo com propósito.”</p>
                            <ul className="quem__somos__lista">
                                <li className="quem__somos__item">RM572637</li>
                                <li className="quem__somos__item">
                                    <a href="https://www.linkedin.com/in/raphaelbritorgb/">
                                        <img src={linkedin} alt="linkedin logo" />
                                    </a>
                                </li>
                                <li className="quem__somos__item">
                                    <a href="https://github.com/PhaelRGB">
                                        <img src={github} alt="GitHUb logo" />
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </section>
                </section>

                <section className="quem__somos__profissional__3">
                    <img className="quem__somos__img" src={felipe} alt="profissonal Soul UP" />
                    <section className="quem__somos__texto">
                        <h2 className="quem__somos__nome">Felipe Lima</h2>
                        <div className="quem__somos__info">
                            <p className="quem__somos__experiencia">"Fazer parte da Post UP foi desafiador e inspirador ao mesmo tempo. Aprendi muito sobre criatividade, sustentabilidade e trabalho em equipe."</p>
                            <ul className="quem__somos__lista">
                                <li className="quem__somos__item">RM569947</li>
                                <li className="quem__somos__item">
                                    <a href="https://www.linkedin.com/in/felipe-lima-a4215832a/">
                                        <img src={linkedin}alt="linkedin logo" />
                                    </a>
                                </li>
                                <li className="quem__somos__item">
                                    <a href="https://github.com/felipelima2005">
                                        <img src={github} alt="GitHUb logo" />
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </section>
                </section>
            </div>
        </main>
)}