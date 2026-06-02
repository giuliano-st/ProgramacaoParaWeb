/*Atividade 1*/
let nome = "Nome da Silva";
let idade = 25;
let habilidades = ["Habilidade1","Habilidade2","Habilidade3"]

/*Atividade 2*/
const botao = document.getElementById("btnHabilidade");
const habilidade = document.getElementById("habilidadeOculta");

function mostrarHabilidadeOculta() {
    habilidade.style.display = "block";
    botao.style.display = "none";
}

botao.addEventListener("click", mostrarHabilidadeOculta);