import axios from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import type { PedidoDados } from "../interfaces/PedidoDados";
// Função PUT atualizar()
const API_URL = `http://${window.location.hostname}:8080`;

const atualizaDados = async (pedido: PedidoDados): Promise<PedidoDados> => {
    const response = await axios.put<PedidoDados>(`${API_URL}/produtos/${pedido.id}`, pedido);
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