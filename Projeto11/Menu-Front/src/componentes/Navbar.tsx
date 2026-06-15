import "./Navbar.css";
interface NavbarProps {
    onNovoProduto: () => void;
}

export function Navbar({ onNovoProduto }: NavbarProps) {
    return (
        <nav className="navbar">
            <div className="navbar-logo">
                <img src={"./src/assets/logo_small.png"} alt="logo" />
                <h2>Xis Comeu Morreu</h2>
            </div>
            <ul className="navbar-links">
                <li>
                    <button className="btn-nav btn-cadastrar" onClick={onNovoProduto}>
                        Login
                    </button>
                </li>
                <li>
                    <button className="btn-nav btn-cadastrar" onClick={onNovoProduto}>
                        Novo Produto
                    </button>
                </li>
            </ul>
        </nav>
    );
}