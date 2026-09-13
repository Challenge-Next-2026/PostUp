import Button from "../../components/Button/Button";
import Card from "../../components/Card/Card";
import imgFundo from "../../img/fundo2.jpg"

export default function Sistema2(){
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
                        titulo="Sistema" 
                        sub="Mais sobre o nosso sitema" 
                        descricao="Nesta nova página vamos falar mais sobre o nosso sitema.Focando mais na ex do nosso explicação Score Dinâmico" 
                        />
                        <Button navegacao="/sistema2" texto="Conheça mais"/>
                    </div>
            </section>
            <div className="conteudo__container__score__dinamico">
                <section className="conteudo__score__dinamico">
                    <h2 className="conteudo__score__dinamico__titulo">I) Impacto Ambiental</h2>
                    <ol className="conteudo__score__dinamico__texto">
                        <li>CO₂ economizado;</li>
                        <li>água poupada;</li>
                        <li>energia economizada;</li>
                        <li>reciclagem;</li>
                        <li>impacto social.</li>
                    </ol>
                    <img className="conteudo__score__dinamico__imagem" src="../img/01_score.PNG" alt="tabela avaliação" />
                </section>
                <section className="conteudo__score__dinamico">
                    <h2 className="conteudo__score__dinamico__titulo">II) Confiabilidade</h2>
                    <ol className="conteudo__score__dinamico__texto">
                        <li>IA de verificação;</li>
                        <li>geolocalização;</li>
                        <li>metadata da imagem;</li>
                        <li>hash da foto;</li>
                        <li>detecção de IA generativa;</li>
                        <li>validação temporal;</li>
                        <li>duplicidade.</li>
                    </ol>
                    <img className="conteudo__score__dinamico__imagem" src="../img/02_score.PNG" alt="tabela avaliação" />
                </section>
                <section className="conteudo__score__dinamico">
                    <h2 className="conteudo__score__dinamico__titulo">III) Frequência Inteligente</h2>
                    <ol className="conteudo__score__dinamico__texto">
                        <li>consistência positiva;</li>
                        <li>porém com decaimento exponencial.</li>
                        <li>Até 1 post por dia: bônus máximo.</li>
                        <li>Muitos posts seguidos: redução gradual.</li>
                    </ol>
                    <img className="conteudo__score__dinamico__imagem" src="../img/03_score.PNG" alt="tabela avaliação" />
                </section>
                <section className="conteudo__score__dinamico">
                    <h2 className="conteudo__score__dinamico__titulo">IV) Dificuldade da Ação</h2>
                    <ol className="conteudo__score__dinamico__texto">
                        <li>Nem toda ação sustentável exige o mesmo esforço</li>
                    </ol>
                    <img className="conteudo__score__dinamico__imagem" src="../img/04_score.PNG" alt="tabela avaliação" />
                </section>
                <section className="conteudo__score__dinamico">
                    <h2 className="conteudo__score__dinamico__titulo">V) Bônus Inteligentes</h2>
                    <ol className="conteudo__score__dinamico__texto">
                        <li>O sistema pode incentivar comportamento coletivo</li>
                    </ol>
                    <img className="conteudo__score__dinamico__imagem" src="../img/05_score.PNG" alt="tabela avaliação" />
                </section>
                <section className="conteudo__score__dinamico">
                    <h2 className="conteudo__score__dinamico__titulo">VI) Penalidades Anti-Fraude</h2>
                    <ol className="conteudo__score__dinamico__texto">
                        <li>Muito importante para o ranking.</li>
                    </ol>
                    <img className="conteudo__score__dinamico__imagem" src="../img/06_score.PNG" alt="tabela avaliação" />
                </section>
                <section className="conteudo__score__dinamico">
                    <h2 className="conteudo__score__dinamico__titulo">Cálculo Completo</h2>
                    <ol className="conteudo__score__dinamico__texto">
                        <li>Imagem de fluxo ilustrativo.</li>
                    </ol>
                    <img className="conteudo__score__dinamico__imagem" src="../img/Fluxo-ScoreDinâmico.png" alt="tabela avaliação" />
                </section>
            </div>
    </main>
)}