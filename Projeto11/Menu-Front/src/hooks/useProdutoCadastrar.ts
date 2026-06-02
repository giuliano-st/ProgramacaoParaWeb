import axios from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import type { ProdutoDados } from "../interfaces/ProdutoDados";

const API_URL = "http://localhost:8080";

const cadastrarDados = async (novoProduto: Omit<ProdutoDados, "id">): Promise<ProdutoDados> => {
    const response = await axios.post<ProdutoDados>(`${API_URL}/produtos`, novoProduto);
    return response.data;
};

export function useProdutoCadastrar() {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: cadastrarDados,
        onSuccess: () => {
            // Atualiza a lista na tela automaticamente
            queryClient.invalidateQueries({ queryKey: ["produto-dados"] });
        }
    });
}