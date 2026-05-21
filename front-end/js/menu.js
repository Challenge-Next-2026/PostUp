const menuItens = document.querySelector(".cabecalho__item")

menuItens.addEventListener("mouseenter", () => {

    menuItens.style.transform = "translateY(-7px)";

});

menuItens.addEventListener("mouseleave", () => {

    menuItens.style.transform = "translateY(0)";

});
menuItens.addEventListener("click", () => {

    menuItens.classList.toggle("ativo");

});