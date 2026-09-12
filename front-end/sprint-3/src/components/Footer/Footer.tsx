import logoBranca from "../../img/logo_branca.png";

export default function Footer(){
    return(
        <footer className="bg-[var(--secondary-dark)] text-[var(--bg-dark)] text-center py-16 px-8 leading-[1.8] border-t-3 border-[var(--primary)]">
            <h2 className="font-['BIZ_UDPGothic',sans-serif] font-bold text-[3rem] sm:text-[5rem] leading-[1.6] my-16 flex items-center justify-center gap-4">
                <span>Post UP</span>
                <img 
                style={{ width: "40px", height: "40px", minWidth: "40px", maxWidth: "40px" }} 
                className="!w-10 !h-10 object-contain shrink-0 inline-block" 
                src={logoBranca} 
                alt="Logo Post UP" 
                />
            </h2>

            <p className="font-['BIZ_UDGothic',sans-serif] text-[2rem] sm:text-[1.5rem] my-8 mx-auto max-w-4xl">
                Transformando ideias em impacto sustentável.
                Nossa missão é conectar inovação, consciência ambiental
                e tecnologia para construir um futuro mais verde.
            </p>

            <p className="font-['BIZ_UDGothic',sans-serif] text-[2rem] sm:text-[2rem] my-8">
                © 2026 Post UP — Todos os direitos reservados.
            </p>

        </footer>
    )
}