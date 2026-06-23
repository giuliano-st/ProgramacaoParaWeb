// routes.tsx
import { Routes, Route} from "react-router-dom";
import Menu from "./pages/Menu";
import Login from "./pages/Login";
import Pedidos from "./pages/Pedidos";

const AppRoutes = () => {

    return (
        <Routes>
            <Route path="/" element={<Menu/>} />

            <Route path="/login" element={<Login />} />

            <Route path="/pedidos" element={<Pedidos />} />
        </Routes>
    );
};

export default AppRoutes;