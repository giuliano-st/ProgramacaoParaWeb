import { useNavigate } from "react-router-dom";
import { useClienteDados } from "../hooks/useClienteDados";
import "./Clientes.css";
import {Navbar} from "../componentes/Navbar.tsx";

const Clientes = () => {
    const { data: clientes } = useClienteDados();
    const navigate = useNavigate();

    const selecionarCliente = (clienteId: number | undefined) => {
        if (clienteId === undefined) return; //Remove aquele erro aleatório
        navigate(`/novo-pedido/${clienteId}`);
    };

    return (
        <>
            <Navbar/>

        <div className="container">
            <h1>Selecionar Cliente</h1>

            <div className="lista-clientes">
                {clientes?.map((cliente) => (
                    <div
                        key={cliente.id}
                        className="card-cliente"
                        onClick={() => selecionarCliente(cliente.id)}
                    >
                        <h3>{cliente.nome}</h3>
                        <p>{cliente.email}</p>
                    </div>
                ))}
            </div>
        </div>
        </>
    );
};

export default Clientes;