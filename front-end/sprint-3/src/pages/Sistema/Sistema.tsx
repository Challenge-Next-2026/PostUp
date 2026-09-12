import { Link } from "react-router-dom";
import uplode from "../../img/UploadPage.png";
import rankig from "../../img/Protótipo Ranking.png";

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
    <div className="conteudo__container__ranking">
        <section className="conteudo__ranking">
            <h2 className="conteudo__ranking__titulo">Ranking Mensal</h2>
            <p className="conteudo__ranking__texto">Esta seção tem como objetivo apresentar uma prototipação inicial de como a interface do ranking seria implementada no projeto.</p>
            <img className="conteudo__ranking__imagem" src={rankig} alt="Protítipo Ranking" />
        </section>
        <section className="conteudo__objetivos">
            <h2 className="conteudo__sobre__titulo">Upload</h2>
            <p className="conteudo__sobre__texto">Esta seção tem como objetivo apresentar uma prototipação inicial de como a interface da página de upload seria implementada no projeto.</p>
            <img className="" src={uplode} alt="Protótipo Upload" />
        </section>
        <button className="conteudo__sobre__botao"><Link to="/sistema2">Conheça mais</Link></button>
    </div>
</main>

)}