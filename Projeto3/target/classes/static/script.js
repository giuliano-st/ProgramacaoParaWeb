function changeStyle(sheet) {
    document.getElementById('theme').href = sheet;
}

const formulario = document.getElementById("meuFormulario");
const campoNome = document.getElementById("nome");
const campoEmail = document.getElementById("email");
const erroMensagem = document.getElementById("erroMensagem");
const erroEmail = document.getElementById("erroEmail");

formulario.addEventListener("submit", function(evento) {
    let textoNome = campoNome.value;
    let email = campoEmail.value;

    if (textoNome.length < 10) {
        evento.preventDefault();

        erroMensagem.style.display = "block";

        campoNome.style.border = "2px solid red";
    }
    else if (!email.includes('@')) {
        evento.preventDefault();

        erroEmail.style.display = "block";

        campoEmail.style.border = "2px solid red";
    }
    else {
        erroMensagem.style.display = "none";
        campoNome.style.border = "1px solid #ccc";
        alert("Formulário validado e pronto para envio real!");
    }
});

const botao = document.getElementById("btnHabilidade");
const habilidade = document.getElementById("habilidadeOculta");

function mostrarHabilidadeOculta() {
    habilidade.style.display = "block";
    botao.style.display = "none";
}

botao.addEventListener("click", mostrarHabilidadeOculta);