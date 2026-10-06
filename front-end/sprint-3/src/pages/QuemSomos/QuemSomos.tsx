import giovanni from "../../img/IMG-20260328-WA0007.jpg";
import raphael from "../../img/3x4 Raphael Gomes.jpeg";
import felipe from "../../img/image.png";
import Card from "../../components/Card/Card";
import imgFundo from "../../img/fundo3.jpg"
import Button from "../../components/Button/Button";
import IntegranteCard from "../../components/IntegranteCard/IntegranteCard";


export default function QuemSomos(){
    // Array de dados para reutilização de componente IntegranteCard
    const integrantes = [
    {
        nome: "Giovanni Zorzetto",
        rm: "RM569464",
        turma: "1TDSPH",
        foto: giovanni,
        descricao:
            "A Post UP me mostrou como a tecnologia pode gerar impacto positivo de verdade. Trabalhar em equipe e desenvolver soluções sustentáveis foi uma experiência incrível.",
        linkedin:
            "https://www.linkedin.com/in/giovanni-zorzetto-oliveira-8375b9305/",
        github: "https://github.com/Gizetto61",
        fundo: "primary" as const
    },
    {
        nome: "Raphael Gomes",
        rm: "RM572637",
        turma: "1TDSPH",
        foto: raphael,
        descricao:
            "Participar da Post UP ampliou minha visão sobre inovação e colaboração. Foi uma oportunidade de crescer profissionalmente e contribuir para algo com propósito.",
        linkedin:
            "https://www.linkedin.com/in/raphaelbritorgb/",
        github: "https://github.com/PhaelRGB",
        fundo: "secondary" as const
    },
    {
        nome: "Felipe Lima",
        rm: "RM569947",
        turma: "1TDSPH",
        foto: felipe,
        descricao:
            "Fazer parte da Post UP foi desafiador e inspirador ao mesmo tempo. Aprendi muito sobre criatividade, sustentabilidade e trabalho em equipe.",
        linkedin:
            "https://www.linkedin.com/in/felipe-lima-a4215832a/",
        github: "https://github.com/felipelima2005",
        fundo: "primary" as const
    }
    ];

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
                    titulo="Quem Somos" 
                    sub="Profissionais Qualificados." 
                    descricao="Profissionais apaixonados por inovação, tecnologia e impacto social.  
                        Queremos transformar ideias em soluções sustentáveis, 
                        criando experiências que conectam pessoas, propósito e crescimento." 
                    />
                    <Button navegacao="/sistema" texto="Conheça mais"/>
                </div>
            </section>

                <div className="w-full h-auto flex flex-col gap-50  m-2rem md:gap-24 my-10 md:my-20">
                    <section className="w-full h-full flex flex-col gap-10 justify-center items-center border-b-[5px] border-[var(--secondary)] bg-[var(--bg-dark)] py-12 md:py-20">
                        <div className="w-[90%] md:w-[85%] bg-[var(--secondary-dark)] text-[var(--bg-dark)] p-10 md:p-20 rounded-[12px] text-center shadow-lg">
                        
                        <h2 className="text-4xl md:text-[var(--titulo-main)] font-[var(--fonte-texto)] font-semibold mb-8 md:mb-14 underline decoration-[var(--primary)]">
                            Equipe Post UP
                        </h2>

                        <p className="text-xl md:text-[2.3rem] font-[var(--fonte-texto)] text-[var(--bg-dark)] leading-relaxed md:leading-[3.5rem] max-w-5xl mx-auto">
                            Movidos pela inovação, criatividade e compromisso com a sustentabilidade. <br className="hidden md:inline" />
                            Nossa equipe trabalha para desenvolver soluções modernas e eficientes, conectando tecnologia <br className="hidden md:inline" />
                            e impacto positivo para construir um futuro mais consciente e sustentável.
                        </p>

                        </div>
                    </section>

                    {/* Integrantes */}
                    <div className="w-full h-auto flex flex-col gap-50 m-2rem md:gap-24 my-10 md:my-20">

                        {integrantes.map((integrante) => (
                            <IntegranteCard
                                key={integrante.rm}
                                integrante={integrante}
                            />
                        ))}
                    </div>
            </div>
        </main>
)}