export default function NotFound(){
    return (
        <main className="w-full min-h-[70vh] flex flex-col items-center justify-center gap-10 p-6 md:p-16 my-8 md:my-16"> 
            
            <div className="w-full max-w-4xl bg-[var(--primary)] border-3 border-[var(--secondary-dark)] rounded-[4rem] p-8 md:p-16 text-center shadow-lg">
                
    
                <h1 className="font-[var(--fonte-texto)] text-7xl md:text-9xl font-extrabold text-[var(--bg-primary)] tracking-wider mb-4 drop-shadow-md">
                404
                </h1>

                
                <h2 className="font-[var(--fonte-texto)] text-2xl md:text-4xl font-bold text-[var(--bg-primary)] mb-6">
                Ops! Página não encontrada.
                </h2>

                <p className="font-[var(--fonte-texto)] text-lg md:text-2xl text-[var(--bg-primary)] leading-relaxed max-w-2xl mx-auto mb-10">
                Parece que você tentou acessar um caminho que não existe na <span className="font-bold underline">Post UP</span>. 
                Mas não se preocupe, a sustentabilidade e a inovação continuam por aqui!
                </p>

                <div className="flex justify-center">
                <a
                    href="/"
                    className="inline-block bg-[var(--bg-primary)] text-[var(--secondary-dark)] font-[var(--fonte-texto)] font-bold text-lg md:text-xl px-10 py-5 rounded-[3rem] border-3 border-[var(--secondary-dark)] transition-all duration-300 hover:bg-[var(--secondary-dark)] hover:text-[var(--bg-primary)] hover:-translate-y-1 hover:shadow-[0_10px_25px_rgba(0,0,0,0.25)]"
                >
                    Voltar para o início
                </a>
                </div>

            </div>

            
            <section className="bg-[var(--secondary-dark)] border-3 border-[var(--primary)] rounded-[3rem] p-6 text-center text-[var(--bg-primary)] max-w-xl">
                <p className="font-[var(--fonte-texto)] text-base md:text-lg">
                    Precisa de ajuda? Entre em contato com a equipe da <span className="font-bold">Post UP</span> através da nossa página de suporte.
                </p>
            </section>
    </main>
)}