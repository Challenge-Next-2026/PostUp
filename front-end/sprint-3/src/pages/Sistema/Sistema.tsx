import uplode from "../../img/UploadPage.png";
import rankig from "../../img/Protótipo Ranking.png";
import Button from "../../components/Button/Button";
import Card from "../../components/Card/Card";
import imgFundo from "../../img/fundo3.jpg"

export default function Sistema(){
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
                        sub="Funcionamento prático" 
                        descricao="Demonstração visual e Protótipo" 
                        />
                        <Button navegacao="/sistema2" texto="Conheça mais"/>
                    </div>
                </section>
                    <div className="w-full h-auto flex flex-col gap-24">
                <section className="flex flex-col gap-20 mt-16 bg-[var(--primary)] text-[var(--bg-dark)] border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                    <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] underline decoration-[var(--secondary)] text-center">
                        Ranking Mensal
                    </h2>
                    <p className="text-[2.1rem] font-[family-name:var(--fonte-texto)] text-center leading-[4rem]">
                        Esta seção tem como objetivo apresentar uma prototipação inicial de como a interface do ranking seria implementada no projeto.
                    </p>
                    <img
                        className="rounded-2xl p-0 max-w-[90%] max-h-[80vh] w-auto h-auto block mx-auto object-contain"
                        src={rankig}
                        alt="Protítipo Ranking"
                    />
                </section>

                <section className="flex flex-col gap-20 mt-16 bg-[#8BA61D] text-white border-4 border-[var(--secondary)] p-20 rounded-[4rem]">
                    <h2 className="text-[length:var(--titulo-main)] font-[family-name:var(--fonte-texto)] underline decoration-white text-center">
                        Upload
                    </h2>
                    <p className="text-[2.1rem] font-[family-name:var(--fonte-texto)] text-center leading-[4rem]">
                        Esta seção tem como objetivo apresentar uma prototipação inicial de como a interface da página de upload seria implementada no projeto.
                    </p>
                    <img
                        className="rounded-2xl p-0 max-w-[90%] max-h-[80vh] w-auto h-auto block mx-auto object-contain"
                        src={uplode}
                        alt="Protótipo Upload"
                    />
                </section>

                <Button navegacao="/sistema2" texto="Conheça Mais" />
            </div>
        </main>

)}