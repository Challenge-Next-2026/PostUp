import Button from "../../components/Button/Button";
import Card from "../../components/Card/Card";
import imgFundo from "../../img/fundo3.jpg"
import imgIa from "../../img/ia_score.png"
import imgConfi from "../../img/confi_score.png"
import imgFi from "../../img/fi_score.png"
import imgDa from "../../img/da_score.png"
import imgBi from "../../img/bi_score.png"
import imgPaf from "../../img/paf_score.png"
import imgFc from "../../img/Fluxo-ScoreDinâmico.png"


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
                <div className="w-full h-auto flex flex-col gap-24">
                    <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                        <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] font-semibold underline decoration-[var(--secondary)] text-center">
                        I) Impacto Ambiental
                        </h2>
                        <ol className="text-[2.6rem] font-[family-name:var(--fonte-texto)] font-bold text-center leading-[4rem] flex flex-col items-center pl-0 list-inside">
                        <li className="text-center mb-2">CO₂ economizado;</li>
                        <li className="text-center mb-2">água poupada;</li>
                        <li className="text-center mb-2">energia economizada;</li>
                        <li className="text-center mb-2">reciclagem;</li>
                        <li className="text-center mb-2">impacto social.</li>
                        </ol>
                        <img className="rounded-[2.4rem] p-0" src={imgIa} alt="tabela avaliação" />
                    </section>

                    <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                        <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] font-semibold underline decoration-[var(--secondary)] text-center">
                        II) Confiabilidade
                        </h2>
                        <ol className="text-[2.6rem] font-[family-name:var(--fonte-texto)] font-bold text-center leading-[4rem] flex flex-col items-center pl-0 list-inside">
                        <li className="text-center mb-2">IA de verificação;</li>
                        <li className="text-center mb-2">geolocalização;</li>
                        <li className="text-center mb-2">metadata da imagem;</li>
                        <li className="text-center mb-2">hash da foto;</li>
                        <li className="text-center mb-2">detecção de IA generativa;</li>
                        <li className="text-center mb-2">validação temporal;</li>
                        <li className="text-center mb-2">duplicidade.</li>
                        </ol>
                        <img className="rounded-[2.4rem] p-0" src={imgConfi} alt="tabela avaliação" />
                    </section>

                    <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                        <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] font-semibold underline decoration-[var(--secondary)] text-center">
                        III) Frequência Inteligente
                        </h2>
                        <ol className="text-[2.6rem] font-[family-name:var(--fonte-texto)] font-bold text-center leading-[4rem] flex flex-col items-center pl-0 list-inside">
                        <li className="text-center mb-2">consistência positiva;</li>
                        <li className="text-center mb-2">porém com decaimento exponencial.</li>
                        <li className="text-center mb-2">Até 1 post por dia: bônus máximo.</li>
                        <li className="text-center mb-2">Muitos posts seguidos: redução gradual.</li>
                        </ol>
                        <img className="rounded-[2.4rem] p-0" src={imgFi} alt="tabela avaliação" />
                    </section>

                    <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                        <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] font-semibold underline decoration-[var(--secondary)] text-center">
                        IV) Dificuldade da Ação
                        </h2>
                        <ol className="text-[2.6rem] font-[family-name:var(--fonte-texto)] font-bold text-center leading-[4rem] flex flex-col items-center pl-0 list-inside">
                        <li className="text-center mb-2">Nem toda ação sustentável exige o mesmo esforço</li>
                        </ol>
                        <img className="rounded-[2.4rem] p-0" src={imgDa} alt="tabela avaliação" />
                    </section>

                    <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                        <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] font-semibold underline decoration-[var(--secondary)] text-center">
                        V) Bônus Inteligentes
                        </h2>
                        <ol className="text-[2.6rem] font-[family-name:var(--fonte-texto)] font-bold text-center leading-[4rem] flex flex-col items-center pl-0 list-inside">
                        <li className="text-center mb-2">O sistema pode incentivar comportamento coletivo</li>
                        </ol>
                        <img className="rounded-[2.4rem] p-0" src={imgBi} alt="tabela avaliação" />
                    </section>

                    <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                        <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] font-semibold underline decoration-[var(--secondary)] text-center">
                        VI) Penalidades Anti-Fraude
                        </h2>
                        <ol className="text-[2.6rem] font-[family-name:var(--fonte-texto)] font-bold text-center leading-[4rem] flex flex-col items-center pl-0 list-inside">
                        <li className="text-center mb-2">Muito importante para o ranking.</li>
                        </ol>
                        <img className="rounded-[2.4rem] p-0" src={imgPaf} alt="tabela avaliação" />
                    </section>

                    <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                        <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] font-semibold underline decoration-[var(--secondary)] text-center">
                        Cálculo Completo
                        </h2>
                        <ol className="text-[2.6rem] font-[family-name:var(--fonte-texto)] font-bold text-center leading-[4rem] flex flex-col items-center pl-0 list-inside">
                        <li className="text-center mb-2">Imagem de fluxo ilustrativo.</li>
                        </ol>
                        <img className="rounded-[2.4rem] p-0" src={imgFc} alt="tabela avaliação" />
                    </section>
                    </div>
    </main>
)}