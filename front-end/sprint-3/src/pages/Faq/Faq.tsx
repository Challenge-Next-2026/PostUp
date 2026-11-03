import Card from "../../components/Card/Card";
import imgFundo from "../../img/fundo2.jpg"
import Button from "../../components/Button/Button";
import { useParams } from "react-router-dom";
import { useState } from "react";
import FaqItem from "../../components/FaqItem/FaqItem";

export default function Faq(){
    // id armazenado como String
    const { id } = useParams();

    const perguntas = [
        {
            id: 1,
            pergunta: "Como funciona o sistema de pontuação da Post UP?",
            resposta:
                "A plataforma utiliza um sistema de score dinâmico baseado em impacto, frequência, dificuldade e confiabilidade das ações sustentáveis realizadas pelos usuários. Além disso, existe um ranking para incentivar a competitividade saudável entre os participantes."
        },

        {
            id: 2,
            pergunta: "O que acontece quando um usuário acumula pontos?",
            resposta:
                "Os usuários podem subir no ranking da plataforma e participar do sistema de recompensas baseado em ações sustentáveis e engajamento dentro da comunidade."
        },

        {
            id: 3,
            pergunta: "Como os posts sustentáveis são classificados?",
            resposta:
                "Os posts são avaliados através de um algoritmo de classificação que considera relevância, impacto ambiental e confiabilidade das informações compartilhadas."
        },

        {
            id: 4,
            pergunta: "Qual o objetivo do ranking dentro da plataforma?",
            resposta:
                "O ranking busca incentivar o engajamento e estimular os usuários a manterem hábitos sustentáveis de forma contínua e competitiva."
        },

        {
            id: 5,
            pergunta: "Como a Post UP verifica se uma ação sustentável é verdadeira?",
            resposta:
                "A plataforma utiliza inteligência artificial e validação por geolocalização para identificar atividades suspeitas e reduzir fraudes. Isso garante maior confiabilidade nas publicações e pontuações dos usuários."
        }
    ];
    // useState()
    const [perguntaAberta, setPerguntaAberta] = useState<number | null>(null);
    // Serve para exibir apenas uma pergunta em específico - Uso do useParams()
    const perguntasExibidas = id ? perguntas.filter(
        (pergunta) => pergunta.id === Number(id)
      ) : perguntas;

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
                    titulo="FAQ" 
                    sub="Algumas perguntas e respostas" 
                    descricao="O FAQ da Post UP reúne perguntas e respostas rápidas para ajudar os usuários a entenderem o funcionamento da 
                        plataforma, suas funcionalidades e o sistema de sustentabilidade e recompensas." 
                    />
                    <Button navegacao="/sistema" texto="Conheça mais"/>
                </div>
            </section>
            
        <section className="w-full h-auto">
            
            {/* Perguntas */}
                {perguntasExibidas.map((pergunta) => (
                    <FaqItem
                        key={pergunta.id}
                        pergunta={pergunta.pergunta}
                        resposta={pergunta.resposta}
                        aberta={perguntaAberta === pergunta.id}
                        onToggle={() => {
                            setPerguntaAberta(
                                perguntaAberta === pergunta.id
                                    ? null
                                    : pergunta.id
                            );
                        }}
                    />
                ))}

        </section>
    </main>
)}