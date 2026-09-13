import Card from "../../components/Card/Card";
import imgFundo from "../../img/fundo2.jpg"
import Button from "../../components/Button/Button";
import { useForm } from "react-hook-form";

interface FormularioData {
    nome: string;
    email: string;
    mensagem: string;
}

export default function Contato(){
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors }
    } = useForm<FormularioData>();

    const onSubmit = (data: FormularioData) => {
        console.log(data);

        alert("Mensagem enviada com sucesso!");

        reset();
    };
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
                    titulo="Contato" 
                    sub="Fale conosco!!" 
                    descricao="Na Post UP, nós acreditamos que grandes parcerias começam com uma boa conversa. Se você quer tirar uma ideia do papel, 
                        escalar os seus resultados ou apenas entender como podemos ajudar a sua empresa a crescer, você está no lugar certo." 
                    />
                    <Button navegacao="/sistema" texto="Conheça mais"/>
                </div>
            </section>
            <section
                    className="
                        w-full
                        bg-[var(--primary)]
                        rounded-[3rem]
                        p-6
                        md:p-12
                        lg:p-20
                        flex
                        flex-col
                        items-center
                        gap-10
                        md:gap-14
                    "
                >


                    <div
                        className="
                            w-full
                            max-w-2xl
                            flex
                            flex-col
                            md:flex-row
                            items-center
                            justify-center
                            gap-3
                            md:gap-5
                            p-6
                            md:p-8
                            bg-[var(--bg-primary)]
                            border-3
                            border-[var(--secondary)]
                            rounded-[3rem]
                        "
                    >
                        <span
                            className="
                                font-[var(--fonte-texto)]
                                text-base
                                md:text-xl
                                font-bold
                                text-[var(--text-main)]
                                text-center
                                break-all
                            "
                        >
                            challengecfgr.2026@gmail.com
                        </span>

                    </div>

                    <form
                        onSubmit={handleSubmit(onSubmit)}
                        className="
                            w-full
                            md:w-[80%]
                            lg:w-[65%]
                            flex
                            flex-col
                            gap-7
                            bg-[var(--bg-primary)]
                            p-8
                            md:p-12
                            lg:p-16
                            rounded-[3rem]
                            border-3
                            border-[var(--secondary-dark)]
                            shadow-[0_15px_40px_rgba(0,0,0,0.15)]
                        "
                    >

                        <div
                            className="
                                flex
                                flex-col
                                items-center
                                text-center
                                gap-3
                                mb-3
                            "
                        >

                            <h3
                                className="
                                    font-[var(--fonte-principal)]
                                    text-2xl
                                    md:text-3xl
                                    font-bold
                                    text-[var(--text-main)]
                                "
                            >
                                Envie sua mensagem
                            </h3>

                            <p
                                className="
                                    font-[var(--fonte-texto)]
                                    text-base
                                    md:text-lg
                                    text-[var(--text-main)]
                                "
                            >
                                Preencha os campos abaixo e fale com nossa equipe.
                            </p>

                        </div>




                        <div
                            className="
                                flex
                                flex-col
                                gap-2
                            "
                        >

                            <label
                                htmlFor="nome"
                                className="
                                    font-[var(--fonte-texto)]
                                    text-lg
                                    font-bold
                                    text-[var(--text-main)]
                                "
                            >
                                Nome
                            </label>

                            <input
                                id="nome"
                                type="text"
                                placeholder="Digite seu nome"
                                className={`
                                    w-full
                                    p-4
                                    md:p-5
                                    rounded-[1.2rem]
                                    border-2
                                    outline-none
                                    transition-all
                                    font-[var(--fonte-texto)]
                                    text-base
                                    md:text-lg
                                    text-[var(--text-main)]
                                    placeholder:text-gray-500
                                    ${
                                        errors.nome
                                            ? "border-red-500"
                                            : "border-[var(--primary)] focus:border-[var(--primary-dark)]"
                                    }
                                `}
                                {...register("nome", {
                                    required: "O nome é obrigatório.",

                                    minLength: {
                                        value: 3,
                                        message:
                                            "O nome deve ter pelo menos 3 caracteres."
                                    }
                                })}
                            />

                            {errors.nome && (
                                <span
                                    className="
                                        text-red-500
                                        text-sm
                                        font-semibold
                                        mt-1
                                    "
                                >
                                    {errors.nome.message}
                                </span>
                            )}

                        </div>



                        <div
                            className="
                                flex
                                flex-col
                                gap-2
                            "
                        >

                            <label
                                htmlFor="email"
                                className="
                                    font-[var(--fonte-texto)]
                                    text-lg
                                    font-bold
                                    text-[var(--text-main)]
                                "
                            >
                                E-mail
                            </label>

                            <input
                                id="email"
                                type="email"
                                placeholder="Digite seu e-mail"
                                className={`
                                    w-full
                                    p-4
                                    md:p-5
                                    rounded-[1.2rem]
                                    border-2
                                    outline-none
                                    transition-all
                                    font-[var(--fonte-texto)]
                                    text-base
                                    md:text-lg
                                    text-[var(--text-main)]
                                    placeholder:text-gray-500

                                    ${
                                        errors.email
                                            ? "border-red-500"
                                            : "border-[var(--primary)] focus:border-[var(--primary-dark)]"
                                    }
                                `}
                                {...register("email", {
                                    required: "O e-mail é obrigatório.",

                                    pattern: {
                                        value:
                                            /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                                        message:
                                            "Digite um e-mail válido."
                                    }
                                })}
                            />

                            {errors.email && (
                                <span
                                    className="
                                        text-red-500
                                        text-sm
                                        font-semibold
                                        mt-1
                                    "
                                >
                                    {errors.email.message}
                                </span>
                            )}

                        </div>

                        <div
                            className="
                                flex
                                flex-col
                                gap-2
                            "
                        >

                            <label
                                htmlFor="mensagem"
                                className="
                                    font-[var(--fonte-texto)]
                                    text-lg
                                    font-bold
                                    text-[var(--text-main)]
                                "
                            >
                                Mensagem
                            </label>

                            <textarea
                                id="mensagem"
                                placeholder="Digite sua mensagem"
                                className={`
                                    w-full
                                    min-h-[180px]
                                    p-4
                                    md:p-5
                                    rounded-[1.2rem]
                                    border-2
                                    outline-none
                                    resize-none
                                    transition-all
                                    font-[var(--fonte-texto)]
                                    text-base
                                    md:text-lg
                                    text-[var(--text-main)]
                                    placeholder:text-gray-500

                                    ${
                                        errors.mensagem
                                            ? "border-red-500"
                                            : "border-[var(--primary)] focus:border-[var(--primary-dark)]"
                                    }
                                `}
                                {...register("mensagem", {
                                    required:
                                        "A mensagem é obrigatória.",

                                    minLength: {
                                        value: 10,
                                        message:
                                            "A mensagem deve ter pelo menos 10 caracteres."
                                    }
                                })}
                            />

                            {errors.mensagem && (
                                <span
                                    className="
                                        text-red-500
                                        text-sm
                                        font-semibold
                                        mt-1
                                    "
                                >
                                    {errors.mensagem.message}
                                </span>
                            )}

                        </div>

                        <button
                            type="submit"
                            className="
                                w-full
                                p-4
                                md:p-5
                                mt-2
                                rounded-[1.2rem]
                                border-2
                                border-[var(--secondary-dark)]
                                bg-[var(--primary-dark)]
                                text-[var(--bg-primary)]
                                font-[var(--fonte-texto)]
                                text-lg
                                md:text-xl
                                font-bold
                                cursor-pointer
                                transition-all
                                duration-300
                                hover:bg-[var(--secondary-dark)]
                                hover:-translate-y-1
                                hover:shadow-lg
                            "
                        >
                            Enviar mensagem
                        </button>

                    </form>

                </section>
    </main>
)}