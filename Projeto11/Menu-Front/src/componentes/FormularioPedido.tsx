import { useState, useEffect } from "react";
import type { PedidoDados, ItemPedido, Cliente } from "../interfaces/PedidoDados";
import { useProdutoDados } from "../hooks/useProdutoDados.ts"; // Para carregar os produtos no select
import { usePedidoMutate } from "../hooks/usePedidoMutate.ts"; // Sua mutação de cadastro
import "./formularioPedido.css";

interface FormularioProps {
    pedidoInicial?: PedidoDados | null;
    onSubmit?: (dados: PedidoDados) => void; // Prop para o caso de edição
}

export function FormularioPedido({ pedidoInicial, onSubmit }: FormularioProps) {
    // 1. Estados locais do Pedido
    const [clienteId, setClienteId] = useState<number>(pedidoInicial?.clienteId?.id || 0);
    const [clienteNome, setClienteNome] = useState<string>(pedidoInicial?.clienteId?.nome || "");
    const [status, setStatus] = useState<string>(pedidoInicial?.status || "PENDENTE");
    const [itensPedido, setItensPedido] = useState<ItemPedido[]>(pedidoInicial?.itensPedido || []);

    // 2. Estados auxiliares para adicionar itens dinamicamente
    const { data: produtos } = useProdutoDados();
    const [produtoSelecionadoId, setProdutoSelecionadoId] = useState<string>("");
    const [quantidadeSelecionada, setQuantidadeSelecionada] = useState<number>(1);

    const { mutate: cadastrarPedido } = usePedidoMutate();

    // 3. Sincroniza se o pedido inicial mudar (modo edição)
    useEffect(() => {
        if (pedidoInicial) {
            setClienteId(pedidoInicial.clienteId.id);
            setClienteNome(pedidoInicial.clienteId.nome);
            setStatus(pedidoInicial.status);
            setItensPedido(pedidoInicial.itensPedido);
        }
    }, [pedidoInicial]);

    // 4. Lógica para gerenciar a lista de itens locais do formulário
    const adicionarItem = () => {
        if (!produtoSelecionadoId) return alert("Selecione um produto!");

        const produtoEncontrado = produtos?.find(p => p.id === Number(produtoSelecionadoId));
        if (!produtoEncontrado) return;

        // Evita duplicar o mesmo produto na lista; soma a quantidade se já existir
        const itemExistente = itensPedido.find(item => item.produtoId.id === produtoEncontrado.id);
        if (itemExistente) {
            setItensPedido(itensPedido.map(item =>
                item.produtoId.id === produtoEncontrado.id
                    ? { ...item, quantidade: item.quantidade + quantidadeSelecionada }
                    : item
            ));
        } else {
            const novoItem: ItemPedido = {
                produtoId: produtoEncontrado,
                quantidade: quantidadeSelecionada
            };
            setItensPedido([...itensPedido, novoItem]);
        }

        // Reseta os seletores auxiliares
        setProdutoSelecionadoId("");
        setQuantidadeSelecionada(1);
    };

    const removerItem = (produtoId?: number) => {
        setItensPedido(itensPedido.filter(item => item.produtoId.id !== produtoId));
    };

    // 5. Envio do formulário
    function enviarFormulario(event: React.FormEvent) {
        event.preventDefault();

        if (itensPedido.length === 0) {
            return alert("Adicione pelo menos um item ao pedido!");
        }

        // Montamos o objeto Cliente fake para bater com a interface esperada
        const clienteObjeto: Cliente = {
            id: clienteId,
            nome: clienteNome || `Cliente #${clienteId}`
        };

        const pedido: PedidoDados = {
            ...(pedidoInicial?.id && { id: pedidoInicial.id }),
            clienteId: clienteObjeto,
            itensPedido,
            status,
            valorTotal: pedidoInicial?.valorTotal || 0, // Backend recalcula isso de qualquer forma
            dataPedido: pedidoInicial?.dataPedido || new Date().toISOString()
        };

        if (onSubmit) {
            onSubmit(pedido);
        } else {
            cadastrarPedido(pedido);
            limparCampos();
        }
    }

    const limparCampos = () => {
        setClienteId(0);
        setClienteNome("");
        setStatus("PENDENTE");
        setItensPedido([]);
    };

    return (
        <form className="formulario" onSubmit={enviarFormulario}>
            <h2>{pedidoInicial ? `Editar Pedido #${pedidoInicial.id}` : "Novo Pedido"}</h2>

            {/* Informações do Cliente */}
            <div className="grupo-input">
                <input
                    type="number"
                    placeholder="ID do Cliente"
                    value={clienteId || ""}
                    onChange={(e) => setClienteId(Number(e.target.value))}
                    required
                />
                <input
                    type="text"
                    placeholder="Nome do Cliente (Opcional)"
                    value={clienteNome}
                    onChange={(e) => setClienteNome(e.target.value)}
                />
            </div>

            {/* Status do Pedido */}
            <select value={status} onChange={(e) => setStatus(e.target.value)}>
                <option value="PENDENTE">Pendente</option>
                <option value="PREPARANDO">Preparando</option>
                <option value="PRONTO">Pronto</option>
                <option value="ENTREGUE">Entregue</option>
                <option value="CANCELADO">Cancelado</option>
            </select>

            <hr />
            <h3>Adicionar Itens</h3>

            {/* Seletor dinâmico de Produtos */}
            <div className="adicionar-itens-container">
                <select
                    value={produtoSelecionadoId}
                    onChange={(e) => setProdutoSelecionadoId(e.target.value)}
                >
                    <option value="">Selecione um Produto...</option>
                    {produtos?.filter(p => p.disponibilidade).map(p => (
                        <option key={p.id} value={p.id}>{p.nome} - R$ {p.preco.toFixed(2)}</option>
                    ))}
                </select>

                <input
                    type="number"
                    min="1"
                    value={quantidadeSelecionada}
                    onChange={(e) => setQuantidadeSelecionada(Number(e.target.value))}
                />

                <button type="button" className="btn-adicionar" onClick={adicionarItem}>
                    + Adicionar
                </button>
            </div>

            {/* Lista Provisória de Itens inseridos */}
            <div className="lista-itens-adicionados">
                <h4>Itens do Pedido atual:</h4>
                {itensPedido.length === 0 ? (
                    <p className="aviso-vazio">Nenhum item adicionado ainda.</p>
                ) : (
                    <ul>
                        {itensPedido.map((item, index) => (
                            <li key={index}>
                                {item.quantidade}x {item.produtoId.nome}
                                <button
                                    type="button"
                                    className="btn-remover-item"
                                    onClick={() => removerItem(item.produtoId.id)}
                                >
                                    Remover
                                </button>
                            </li>
                        ))}
                    </ul>
                )}
            </div>
            <hr />

            <button type="submit">
                {pedidoInicial ? "Salvar Alterações" : "Fechar e Enviar Pedido"}
            </button>
        </form>
    );
}