import "./App.css";
import AppFooter from "./componentes/Footer.tsx"

import { Link } from "react-router-dom";
import AppRoutes from "./routes";

function App() {

    return (
        <>
            <nav className="menu-navegacao">
                <Link to="/">Menu </Link>
                <Link to="/Login">Login</Link>
            </nav>
            <AppRoutes />

            <AppFooter/>
        </>
    );
}

export default App;