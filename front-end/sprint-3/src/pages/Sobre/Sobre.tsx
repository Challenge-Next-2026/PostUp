import { Link } from "react-router-dom";
import Footer from "../../components/Footer/Footer";
import Header from "../../components/Header/Header";
import demostracao from "../../img/Demonstração de funcionamento da Plataforma.png";
import Button from "../../components/Button/Button";

export default function Sobre(){
    return (
                    <main className="conteudo">

            <section className="hero">

                <div className="hero__container">

                    <h1 className="hero__title">Sobre</h1>

                    <h2 className="hero__slogan">Detalhes do projeto e demonstração de diferenciáis</h2>

                    <p className="hero__desc">Introdução, Objetivos, Justificativa e RoadMap</p>

                </div>

            </section>

            <div className="conteudo__container__sobre">

                    <section className="conteudo__introducao">

                        <h2 className="conteudo__sobre__titulo">Introdução</h2>

                        <p className="conteudo__sobre__texto">Nossa solução visa cumprir com os requisitos propostos pela empresa parceira <strong>Soul UP</strong>, no que inclui o <em>Desafio 01 do Challenge Next 2026</em>. Nossa tarefa é usar de funcionalidades já presentes no sistema da empresa e incrementar com um sistema gamificado de pontuação que recompensa usuários com pontos ECOA por fazerem postagens sustentáveis. Além de implementar um Ranking Mensal onde o usuário com mais pontos acumuládos através de postagens terá sua conta de luz do mês totalmente quitada.</p>

                    </section>

                    <section className="conteudo__objetivos">

                        <h2 className="conteudo__sobre__titulo">Objetivos</h2>

                        <p className="conteudo__sobre__texto">Como objetivo base temos a busca pela satisfação da empresa parceira e a oportunidade de aplicar o conhecimento adquirido em uma solução real que atende aos requisitos do mercado. Entretanto, é de suma importância que objetivos particulares da nossa solução sejam alcançados. Por implementar um sistema gamificado e competitivo através do ranking, buscamos incentivar e motivar nossos usuários a fortalecer seu impacto ambiental positivo e tornar rotineira ações sustentáveis que mesmo simples fazem a diferença. Como complemento desejamos promover a plataforma da SoulUp como divertida, funcional e confiável. Alcançando assim cada vez mais usuários e propagando a semente da sustentabilidade em cada um deles.</p>

                    </section>

                    <section className="conteudo__justificativa">

                        <h2 className="conteudo__sobre__titulo">Justificativa</h2>

                        <p className="conteudo__sobre__texto">A utilização de um sistema de

                        atribuição de recompensa por ações sustentáveis pode impactar positivamente no incentivo aos

                        usuários agirem, e esse é o principal pilar que estrutura a nossa solução, motivar as pessoas a

                        agirem. Baseamos nosso pilar em um método desenvolvido por David Kolb que aborda o tema do Aprendizado Experiencial e o divide em um ciclo de quatro etapas: [1] Experiência Completa (o indivíduo vivencia uma situação prática); [2] Observação Reflexiva (reflete sobre a experiência); [3] Conceituzalização Abstrata (experiência transmformada em conceitos, teorias ou modelos mentáis para entendimento); [4] Experimentação Ativa (Conhecimento adquirido é testado em novas situações). Através da PostUp, incentivamos o indivíduo à prática e repetição para fixação de conhecimento, nesse caso sobre sustentabilidade.</p>

                    </section>

                    <section className="conteudo__roadmap">

                        <h2 className="conteudo__roadmap__titulo">RoadMap</h2>

                        <img className="conteudo__roadmap__imagem" src={demostracao} alt="Fluxo de ação"/>

                        </section>

                

            </div>

        </main>
)}