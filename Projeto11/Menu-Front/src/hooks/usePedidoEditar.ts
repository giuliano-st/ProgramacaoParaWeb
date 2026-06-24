import axios from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import type { PedidoDados } from "../interfaces/PedidoDados";
// Função PUT atualizar()
const API_URL = `http://${window.location.hostname}:8080`;

const atualizaDados = async (pedido: PedidoDados): Promise<PedidoDados> => {
    // Como o Spring espera "Long", mandamos apenas o número puro do ID!
    const payload = {
        id: pedido.id,
        status: pedido.status,
        dataPedido: pedido.dataPedido,
        dataEntrega: pedido.dataEntrega,
        valorTotal: pedido.valorTotal,

        // Pega apenas o número do ID do cliente
        clienteId: pedido.clienteId?.id || null,

        // Faz o mesmo para os itens, passando o ID do produto como número puro
        itensPedido: pedido.itensPedido?.map(item => ({
            id: item.id,
            quantidade: item.quantidade,
            produtoId: item.produtoId?.id || null // Número puro do Long do Java
        }))
    };

    // Enviamos para o Spring Boot
    const response = await axios.put<PedidoDados>(`${API_URL}/pedidos/${pedido.id}`, payload);
    return response.data;
};

export function usePedidoAtualizar() {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: atualizaDados,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ["pedido-dados"] });
        }
    });
}