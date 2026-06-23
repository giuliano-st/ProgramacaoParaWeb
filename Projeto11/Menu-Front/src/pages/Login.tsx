import { useState } from "react";
import { useNavigate } from "react-router-dom";
import {Navbar} from "../componentes/Navbar.tsx"; // Caso queira redirecionar após o login
// import { useLogin } from "../hooks/useLogin.ts"; // Exemplo de hook futuro para a API

const Login = () => {
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const navigate = useNavigate();

    // Simulando o hook de mutação do React Query que você usa no Menu
    // const {mutate: fazerLogin, isPending } = useLogin();
    const isPending = false; // Mock por enquanto

// Dentro do seu componente ‘Login’.tsx
    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        if (!email || !senha) {
            alert("Por favor, preencha todos os campos.");
            return;
        }

        if (email === "admin@email.com") {
            localStorage.setItem("usuarioLogado", email);
            alert("Bem-vindo, Administrador!");
            navigate("/");
        } else {
            localStorage.setItem("usuarioLogado", email);
            alert(`Bem-vindo!`);
            navigate("/pedidos");
        }
    };

    return (
        <><Navbar/>
            <main className="container">
                {}
                <div className="overlay" style={{position: "relative", minHeight: "60vh", background: "none"}}>
                    <div className="modal"
                         style={{position: "relative", top: 0, left: 0, transform: "none", margin: "0 auto"}}>

                        <h2>Acessar o Sistema</h2>

                        <form onSubmit={handleSubmit} className="formulario-produto">
                            {/* Usei a classe fictícia 'formulario-produto' para herdar os estilos de input que você já tem */}

                            <div className="detalhes-info"
                                 style={{display: "flex", flexDirection: "column", gap: "15px", textAlign: "left"}}>
                                <div>
                                    <label htmlFor="email"
                                           style={{fontWeight: "bold", display: "block", marginBottom: "5px"}}>
                                        E-mail:
                                    </label>
                                    <input
                                        type="email"
                                        id="email"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        placeholder="seu-email@restaurante.com"
                                        required
                                        style={{
                                            width: "100%",
                                            padding: "8px",
                                            borderRadius: "4px",
                                            border: "1px solid #ccc"
                                        }}/>
                                </div>

                                <div>
                                    <label htmlFor="senha"
                                           style={{fontWeight: "bold", display: "block", marginBottom: "5px"}}>
                                        Senha:
                                    </label>
                                    <input
                                        type="password"
                                        id="senha"
                                        value={senha}
                                        onChange={(e) => setSenha(e.target.value)}
                                        placeholder="Sua senha"
                                        required
                                        style={{
                                            width: "100%",
                                            padding: "8px",
                                            borderRadius: "4px",
                                            border: "1px solid #ccc"
                                        }}/>
                                </div>
                            </div>

                            <div className="botoes-alerta" style={{marginTop: "25px"}}>
                                <button
                                    type="submit"
                                    className="btn-confirmar-delete"
                                    style={{backgroundColor: "#2ec4b6"}}
                                    disabled={isPending}
                                >
                                    {isPending ? "Entrando..." : "Entrar"}
                                </button>
                                <button
                                    type="button"
                                    className="btn-cancelar"
                                    onClick={() => navigate("/")}
                                >
                                    Voltar para o Menu
                                </button>
                            </div>
                        </form>

                    </div>
                </div>
            </main>
        </>
    );
};

export default Login;