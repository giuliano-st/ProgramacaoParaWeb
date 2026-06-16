// routes.tsx
import { Routes, Route, Navigate } from "react-router-dom";
import Menu from "./pages/Menu";
import Login from "./pages/Login";

const AppRoutes = () => {
    // Pegamos o e-mail que salvamos no localStorage durante o Login
    const usuarioLogado = localStorage.getItem("usuarioLogado");
    const isAdmin = usuarioLogado === "admin@email.com";

    return (
        <Routes>
            {/* Rota do Cliente/Visitante */}
            <Route path="/" element={<Menu isAdmin={false} />} />

            {/* Rota do Login */}
            <Route path="/login" element={<Login />} />

            {/* Rota exclusiva do Admin. Se um cliente tentar acessar digitando na URL,
                ele é mandado de volta para a página de ‘login’ */}
            <Route
                path="/admin/produtos"
                element={isAdmin ? <Menu isAdmin={true} /> : <Navigate to="/login" />}
            />
        </Routes>
    );
};

export default AppRoutes;