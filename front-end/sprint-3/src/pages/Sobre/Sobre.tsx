
import Button from "../../components/Button/Button";
import Card from "../../components/Card/Card";
import demostracao from "../../img/Demonstração de funcionamento da Plataforma.png";
import imgFundo from "../../img/fundo3.jpg"


export default function Sobre(){
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
                    titulo="Sobre" 
                    sub="Detalhes do projeto e demonstração de diferenciais" 
                    descricao="Introdução, Objetivos, Justificativa e RoadMap" 
                    />
                </div>
            </section>
            <div className="w-full h-auto flex flex-col gap-[6rem]">
                
                
                <section className="flex flex-col gap-[5rem] mt-[4rem] bg-[var(--primary)] text-[var(--bg-dark)] border-[4px] border-[var(--secondary)] p-[2rem] min-[426px]:p-[5rem] rounded-[4rem]">
                    <h2 className="text-[2.5rem] min-[426px]:text-[length:var(--titulo-main)] font-[var(--fonte-texto)] font-normal underline decoration-[var(--secondary)] text-center">
                    Introdução
                    </h2>
                    <p className="text-[1.5rem] leading-[2.4rem] min-[426px]:text-[2.1rem] min-[426px]:leading-[4rem] font-[var(--fonte-texto)] text-center">
                    Nossa solução visa cumprir com os requisitos propostos pela empresa parceira <strong>Soul UP</strong>, no que inclui o <em>Desafio 01 do Challenge Next 2026</em>. Nossa tarefa é usar de funcionalidades já presentes no sistema da empresa e incrementar com um sistema gamificado de pontuação que recompensa usuários com pontos ECOA por fazerem postagens sustentáveis. Além de implementar um Ranking Mensal onde o usuário com mais pontos acumuládos através de postagens terá sua conta de luz do mês totalmente quitada.
                    </p>
                </section>

                
                <section className="flex flex-col gap-[5rem] mt-[4rem] bg-[var(--secondary-dark)] text-[var(--bg-dark)] border-[4px] border-[var(--primary)] p-[2rem] min-[426px]:p-[5rem] rounded-[4rem]">
                    <h2 className="text-[2.5rem] min-[426px]:text-[length:var(--titulo-main)] font-[var(--fonte-texto)] font-normal underline decoration-[var(--secondary)] text-center">
                    Objetivos
                    </h2>
                    <p className="text-[1.5rem] leading-[2.4rem] min-[426px]:text-[2.1rem] min-[426px]:leading-[4rem] font-[var(--fonte-texto)] text-center">
                    Como objetivo base temos a busca pela satisfação da empresa parceira e a oportunidade de aplicar o conhecimento adquirido em uma solução real que atende aos requisitos do mercado. Entretanto, é de suma importância que objetivos particulares da nossa solução sejam alcançados. Por implementar um sistema gamificado e competitivo através do ranking, buscamos incentivar e motivar nossos usuários a fortalecer seu impacto ambiental positivo e tornar rotineira ações sustentáveis que mesmo simples fazem a diferença. Como complemento desejamos promover a plataforma da SoulUp como divertida, funcional e confiável. Alcançando assim cada vez mais usuários e propagando a semente da sustentabilidade em cada um deles.
                    </p>
                </section>

                
                <section className="flex flex-col gap-[5rem] mt-[4rem] bg-[var(--primary)] text-[var(--bg-dark)] border-[4px] border-[var(--secondary)] p-[2rem] min-[426px]:p-[5rem] rounded-[4rem]">
                    <h2 className="text-[2.5rem] min-[426px]:text-[length:var(--titulo-main)] font-[var(--fonte-texto)] font-normal underline decoration-[var(--secondary)] text-center">
                    Justificativa
                    </h2>
                    <p className="text-[1.5rem] leading-[2.4rem] min-[426px]:text-[2.1rem] min-[426px]:leading-[4rem] font-[var(--fonte-texto)] text-center">
                    A utilização de um sistema de atribuição de recompensa por ações sustentáveis pode impactar positivamente no incentivo aos usuários agirem, e esse é o principal pilar que estrutura a nossa solução, motivar as pessoas a agirem. Baseamos nosso pilar em um método desenvolvido por David Kolb que aborda o tema do Aprendizado Experiencial e o divide em um ciclo de quatro etapas: [1] Experiência Completa (o indivíduo vivencia uma situação prática); [2] Observação Reflexiva (reflete sobre a experiência); [3] Conceituzalização Abstrata (experiência transmformada em conceitos, teorias ou modelos mentáis para entendimento); [4] Experimentação Ativa (Conhecimento adquirido é testado em novas situações). Através da PostUp, incentivamos o indivíduo à prática e repetição para fixação de conhecimento, nesse caso sobre sustentabilidade.
                    </p>
                </section>

                
                <section className="flex flex-col gap-[5rem] mt-[4rem] bg-[var(--secondary-dark)] text-[var(--bg-dark)] border-[4px] border-[var(--primary)] p-[2rem] min-[426px]:p-[5rem] rounded-[4rem]">
                    <h2 className="text-[2.5rem] min-[426px]:text-[length:var(--titulo-main)] font-[var(--fonte-texto)] font-normal underline decoration-[var(--secondary)] text-center">
                    RoadMap
                    </h2>
                    <img className="p-0 m-0 w-full h-auto block" src={demostracao} alt="Fluxo de ação" />
                </section>

                
                <div className="flex justify-center my-8">
                    <Button navegacao="/sistema2" texto="Conheça Mais" />
                </div>

        </div>

    </main>
)}