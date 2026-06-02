import "./Navbar.css";
interface NavbarProps {
    onNovoProduto: () => void;
}

export function Navbar({ onNovoProduto }: NavbarProps) {
    return (
        <nav className="navbar">
            <div className="navbar-logo">
                <h2>MenuStream</h2>
            </div>
            <ul className="navbar-links">
                <li>
                    <button className="btn-nav btn-cadastrar" onClick={onNovoProduto}>
                        Novo Produto
                    </button>
                </li>
            </ul>
        </nav>
    );
}