import { Link } from "react-router-dom";
import email from "../../img/icone_email.png";

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
                    <button className="hero__botao"><Link to = "/sistema">Conheça mais</Link></button>
                </div>
            </section>
        <div className="w-full h-auto flex flex-col content-between gap-20 md:gap-32 p-4 md:p-20 my-8 md:my-16">
            <h2 className="font-[var(--fonte-texto)] text-2xl md:text-4xl leading-[3rem] md:leading-normal text-[var(--bg-primary)] text-center p-8 md:p-10 bg-[var(--primary)] border-3 border-[var(--secondary-dark)] rounded-[4rem]">
                Entre em contato com a equipe da Post UP
            </h2>

            
            <p className="font-[var(--fonte-texto)] text-[1.5rem] md:text-[var(--sub-titulo)] text-[var(--bg-primary)] bg-[var(--secondary-dark)] border-3 border-[var(--primary)] text-center p-10 md:p-16 rounded-[4rem] leading-[2.7rem] md:leading-[3.5rem]">
                Nesta página, você pode tirar dúvidas, enviar sugestões, compartilhar ideias e falar diretamente com nossa equipe. A Post UP está sempre aberta para ouvir a comunidade e fortalecer conexões em prol da inovação e da sustentabilidade.
            </p>

            <section className="font-sans bg-[var(--primary)] flex flex-col-reverse items-center justify-center gap-16 md:gap-24 w-full rounded-[3rem] p-8 md:p-20">
                
                
                <p className="font-[var(--fonte-texto)] text-xl md:text-2xl font-bold text-[var(--text-main)] p-6 md:p-8 w-full max-w-2xl bg-[var(--bg-primary)] border-3 border-[var(--secondary)] rounded-[4rem] text-center flex items-center justify-center gap-4">
                <img 
                    style={{ width: "24px", height: "24px", minWidth: "24px", maxWidth: "24px" }} 
                    className="!w-6 !h-6 object-contain shrink-0 inline-block" 
                    src={email} 
                    alt="icone de email" 
                />
                <span>challengecfgr.2026@gmail.com</span>
                </p>

                
                <form className="w-full md:w-[70%] lg:w-[60%] bg-white/12 backdrop-blur-md p-10 md:p-16 rounded-[24px] shadow-[0_15px_40px_rgba(0,0,0,0.25)] text-white" action="challengecfgr.2026@gmail.com" method="POST">
                
                <p className="text-lg md:text-xl font-medium text-center mb-10 leading-relaxed">
                    Envie sua mensagem para a equipe da Post UP. <br className="hidden md:inline" />
                    Responderemos o mais rápido possível.
                </p>

                
                <div className="mb-8">
                    <input
                    className="w-full p-5 border-none rounded-[12px] outline-none text-lg md:text-xl text-slate-800 placeholder:text-slate-500 placeholder:text-base md:placeholder:text-lg"
                    type="text"
                    name="nome"
                    placeholder="Seu nome"
                    required
                    />
                </div>

                <div className="mb-8">
                    <input
                    className="w-full p-5 border-none rounded-[12px] outline-none text-lg md:text-xl text-slate-800 placeholder:text-slate-500 placeholder:text-base md:placeholder:text-lg"
                    type="email"
                    name="email"
                    placeholder="Seu email"
                    required
                    />
                </div>

                <div className="mb-10">
                    <textarea
                    className="w-full p-5 border-none rounded-[12px] outline-none text-lg md:text-xl text-slate-800 placeholder:text-slate-500 placeholder:text-base md:placeholder:text-lg resize-none h-[160px]"
                    name="mensagem"
                    placeholder="Digite sua mensagem"
                    required
                    ></textarea>
                </div>

                
                <button className="w-full p-5 border-none rounded-[12px] bg-white text-[#12959e] text-lg md:text-xl font-bold cursor-pointer transition-all duration-300 hover:-translate-y-[3px] hover:shadow-[0_10px_25px_rgba(255,255,255,0.25)]" type="submit">
                    Enviar mensagem
                </button>
                </form>
            </section>

        </div>
    </main>
)}