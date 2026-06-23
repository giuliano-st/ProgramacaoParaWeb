import axios from "axios";
import { useQuery } from "@tanstack/react-query";
import type { PedidoDados } from "../interfaces/PedidoDados";

const API_URL = `http://${window.location.hostname}:8080`;

const buscarPedidos = async (): Promise<PedidoDados[]> => {
    // Certifique-se de que a rota mapeada no seu @RestController seja /pedidos
    const response = await axios.get<PedidoDados[]>(`${API_URL}/pedidos`);
    return response.data;
};

export function usePedidoDados() {
    return useQuery({
        queryKey: ["pedido-dados"],
        queryFn: buscarPedidos,
        retry: 2,
    });
}