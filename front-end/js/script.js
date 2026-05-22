

//JS ==> Menu

const cabecalho = document.querySelector('.cabecalho');


cabecalho.addEventListener('mouseenter', () => {
    
    cabecalho.style.boxShadow = '0px 4px 15px rgba(115, 228, 63, 0.87)';
    cabecalho.style.transition = '0.4s';
});


cabecalho.addEventListener('mouseleave', () => {
    
    cabecalho.style.boxShadow = 'none';
    cabecalho.style.transition = '0.4s';
});



const menuItems = document.querySelectorAll('.cabecalho__item');

menuItems.forEach(item => {


    item.addEventListener('mouseenter', () => {
        item.style.borderBottom = '3px solid var(--secondary)';
        item.style.paddingBottom = '5px';
        item.style.transition = '0.3s'; 
    });

    item.addEventListener('mouseleave', () => {
        item.style.borderBottom = '3px solid var(--secondary)';
        item.style.paddingBottom = '0px';
        item.style.borderBottom = '2px solid transparent';
    });
});

const menuContato = document.querySelector('.cabecalho__item__contato')

menuContato.addEventListener('mouseenter',() =>{
        menuContato.style.borderBottom = '3px solid var(--secondary)';
        menuContato.style.paddingBottom = '5px';
        menuContato.style.transition = '0.3s'; 
        menuContato.style.color = 'var(--bg-primary)'

})
menuContato.addEventListener('mouseleave', () => {
        menuContato.style.borderBottom = '3px solid var(--secondary)';
        menuContato.style.paddingBottom = '0px';
        menuContato.style.borderBottom = '2px solid transparent';
        menuContato.style.color = 'var(--text-main)'
    });


const menuLogo = document.querySelector('.cabecalho__item__logo')

    
menuLogo.addEventListener('mouseenter', () => {
    menuLogo.style.transform = 'translateY(-6px)';
});

menuLogo.addEventListener('mouseleave', () => {
    menuLogo.style.transform = 'translateY(0)';
});
    


    

//JS ==> hero


const hero = document.querySelector('.hero');

// Verifica se a URL da página contém a palavra "paginas"
const estaNaPastaPaginas = window.location.pathname.includes("/paginas/");

// Define o prefixo: se estiver na pasta paginas, usa ../ senão usa ./
const prefixo = estaNaPastaPaginas ? "../img/" : "./img/";

const imagens = [
    `url("${prefixo}fundo_cabecalho.jpg")`,
    `url("${prefixo}fundo2.jpg")`,
    `url("${prefixo}fundo3.jpg")`
];

let index = 0;
hero.style.backgroundImage = `linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.9)), ${imagens[index]}`;
setInterval(() => {
    index = (index + 1) % imagens.length;
    hero.style.backgroundImage = `linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.9)), ${imagens[index]}`;
}, 4000);

//JS ==> conteudo

const conteudoTituloBloco = document.querySelector('.conteudo__diferenciais__titulo')

conteudoTituloBloco.forEach(titulo => {


    titulo.addEventListener('mouseenter', () => {
        titulo.style.textDecoration = 'underline';
        titulo.style.textDecorationColor = 'var (--primary)'; 
        titulo.style.paddingBottom = '5px';
        titulo.style.transition = '0.3s'; 
    });

    titulo.addEventListener('mouseleave', () => {
        titulo.style.borderBottom = '3px solid var(--primary)';
        titulo.style.paddingBottom = '0px';
        titulo.style.borderBottom = '2px solid transparent';
    });
});

const conteudoIdeia = document.querySelector('.conteudo__diferenciais')

conteudoIdeia.addEventListener('mouseenter', () => {
    conteudoIdeia.style.transform = 'translateY(-6px)';
});

conteudoIdeia.addEventListener('mouseleave', () => {
    conteudoIdeia.style.transform = 'translateY(0)';
});



