import {useProdutoDados} from "../hooks/useProdutoDados.ts";
import {useState} from "react";
import type {ProdutoDados} from "../interfaces/ProdutoDados.ts";
import {useProdutoDeletar} from "../hooks/useProdutoDeletar.ts";
import {useProdutoAtualizar} from "../hooks/useProdutoEditar.ts";
import {Navbar} from "../componentes/Navbar.tsx";
import {FormularioProduto} from "../componentes/FormularioProduto.tsx";
import {CartaoProduto} from "../componentes/CartaoProduto.tsx";
import "./Menu.css";

const Menu = () => {
    const { data } = useProdutoDados();
    const [modalAberto, setModalAberto] = useState(false);
    const [tipoAcao, setTipoAcao] = useState<"cadastrar" | "editar" | "deletar" | "detalhes" | null>(null);

    const [produtoSelecionado, setProdutoSelecionado] = useState<ProdutoDados | null>(null);

    const { mutate: deletarProduto, isPending: isDeletando } = useProdutoDeletar();
    const { mutate: editarProduto } = useProdutoAtualizar();

    const abrirCadastro = () => {
        setTipoAcao("cadastrar");
        setProdutoSelecionado(null);
        setModalAberto(true);
    };

    const abrirEdicao = (produto: ProdutoDados) => {
        setTipoAcao("editar");
        setProdutoSelecionado(produto);
        setModalAberto(true);
    };

    const abrirDelecao = (produto: ProdutoDados) => {
        setTipoAcao("deletar");
        setProdutoSelecionado(produto);
        setModalAberto(true);
    };

    const abrirDetalhes = (produto: ProdutoDados) => {
        setTipoAcao("detalhes");
        setProdutoSelecionado(produto);
        setModalAberto(true);
    };

    const handleDesabilitar = (produto: ProdutoDados) => {
        if (!produto.id) return;

        const produtoAtualizado: ProdutoDados = {
            ...produto,
            disponibilidade: !produto.disponibilidade
        };

        editarProduto(produtoAtualizado, {
            onSuccess: () => {
                const acao = produtoAtualizado.disponibilidade ? "ativado" : "desabilitado";
                alert(`Produto "${produto.nome}" ${acao} com sucesso!`);
            },
            onError: () => {
                alert("Erro ao alterar a disponibilidade do produto.");
            }
        });
    };
    const fecharModal = () => {
        setModalAberto(false);
        setTipoAcao(null);
        setProdutoSelecionado(null);
    };

    const handleConfirmarDelecao = () => {
        if (produtoSelecionado?.id) {
            deletarProduto(produtoSelecionado.id, {
                onSuccess: () => {
                    fecharModal();
                    alert("Produto deletado com sucesso!");
                }
            });
        }
    };

    const handleEditar = (dadosEditados: ProdutoDados) => {
        if (produtoSelecionado?.id) {
            editarProduto({id: produtoSelecionado.id, ...dadosEditados}, {
                onSuccess: () => {
                    fecharModal();
                    alert("Produto editado com sucesso!");
                }
            })
        }
    }

    return (
        <>
            <Navbar/>

            <main className="container">
                <h1>Cardápio</h1>

                {modalAberto && (
                    <div className="overlay">
                        <div className="modal">
                            <button className="fechar" onClick={fecharModal}>X</button>

                            {tipoAcao === "cadastrar" && (
                                <>
                                    <h2>Cadastrar Novo Produto</h2>
                                    <FormularioProduto />
                                </>
                            )}

                            {tipoAcao === "editar" && (
                                <>
                                    <h2>Editar: {produtoSelecionado?.nome}</h2>
                                    <FormularioProduto produtoInicial={produtoSelecionado}
                                                       onSubmit={handleEditar}
                                    />
                                </>
                            )}

                            {tipoAcao === "detalhes" && produtoSelecionado && (
                                <div className="modal-detalhes">
                                    <h2>Detalhes do Produto</h2>

                                    <div className="detalhes-imagem-container">
                                        <img src={produtoSelecionado.imagem} alt={produtoSelecionado.nome} />
                                    </div>

                                    <div className="detalhes-info">
                                        <p><strong>Nome:</strong> {produtoSelecionado.nome}</p>
                                        <p><strong>Descrição:</strong> {produtoSelecionado.descricao}</p>
                                        <p><strong>Categoria:</strong> {produtoSelecionado.categoria}</p>
                                        <p><strong>Preço:</strong> R$ {produtoSelecionado.preco.toFixed(2)}</p>
                                        <p><strong>Status:</strong> {produtoSelecionado.disponibilidade ? "Disponível" : "Indisponível"}</p>
                                    </div>

                                    <button className="btn-cancelar" onClick={fecharModal}>Fechar</button>
                                </div>
                            )}

                            {tipoAcao === "deletar" && (
                                <div className="modal-deletar">
                                    <h2>Excluir Produto</h2>
                                    <p>Tem certeza que deseja excluir o produto <strong>{produtoSelecionado?.nome}</strong>?</p>
                                    <div className="botoes-alerta">
                                        <button className="btn-confirmar-delete" onClick={handleConfirmarDelecao} disabled={isDeletando}>
                                            {isDeletando ? "Excluindo..." : "Sim, excluir"}
                                        </button>
                                        <button className="btn-cancelar" onClick={fecharModal}>Cancelar</button>
                                    </div>
                                </div>
                            )}
                        </div>
                    </div>
                )}

                <section className="grade-cartoes">
                    {data?.map((produto) => (
                        <CartaoProduto
                            key={produto.id}
                            produto={produto}
                            onVerDetalhes={() => abrirDetalhes(produto)}
                            onEditar={() => abrirEdicao(produto)}
                            onDeletar={() => abrirDelecao(produto)}
                            onDesabilitar={() => handleDesabilitar(produto)}
                        />
                    ))}
                </section>
                {/* BOTÃO FLUTUANTE DE CADASTRO (Aparece apenas se for admin) */}
                {(
                    <button
                        className="btn-flutuante-cadastro"
                        onClick={abrirCadastro}
                        title="Cadastrar Novo Produto"
                    >
                        +
                    </button>
                )}
            </main>
        </>
    );
};

export default Menu;