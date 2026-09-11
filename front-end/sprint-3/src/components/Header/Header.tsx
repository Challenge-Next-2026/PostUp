import { Link } from "react-router-dom";
import logoBranca from "../../img/logo_branca.png";

export default function Header(){
    return (
        <header>
        <nav>
            <ul>
                <li><Link to ="/"><img src={logoBranca} alt="Logo Post Up Branca" /></Link></li>
                <li><Link to = "/quem-somos">Quem Somos</Link></li>
                <li><Link to = "/sobre">Sobre</Link></li>
                <li><Link to = "/sistema">Sistema</Link></li>
                <li><Link to = "/faq">FAQ</Link></li>
                <li><Link to = "/contato">Contato</Link></li>
            </ul>
        </nav>
            
        </header>
)}