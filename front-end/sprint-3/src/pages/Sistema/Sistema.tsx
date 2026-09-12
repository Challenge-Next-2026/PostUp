import uplode from "../../img/UploadPage.png";
import rankig from "../../img/Protótipo Ranking.png";
import Button from "../../components/Button/Button";

export default function Sistema(){
    return (
        <main className="conteudo">
    <section className="hero">
        <div className="hero__container">
            <h1 className="hero__title">Sistema</h1>
            <h2 className="hero__slogan">Funcionamento prático</h2>
            <p className="hero__desc">Demonstração visual e Protótipo</p>
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