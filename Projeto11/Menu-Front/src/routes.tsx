import { Routes, Route} from "react-router-dom";
import Menu from "./pages/Menu";
import Login from "./pages/Login";
import Pedidos from "./pages/Pedidos";
import Clientes from "./pages/Clientes.tsx";
import {FormularioProduto} from "./componentes/FormularioProduto.tsx";
import CardapioCliente from "./pages/CardapioCliente.tsx";

const AppRoutes = () => {

    return (
        <Routes>
            <Route path="/" element={<Menu/>} />

            <Route path="/cardapio" element={<CardapioCliente />} />

            <Route path="/login" element={<Login />} />

            <Route path="/pedidos" element={<Pedidos />} />

            <Route path="/clientes" element={<Clientes />} />

            <Route path="/testes" element={<FormularioProduto />} />
        </Routes>
    );
};

export default AppRoutes;