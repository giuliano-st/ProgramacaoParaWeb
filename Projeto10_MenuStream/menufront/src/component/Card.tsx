import type {ProdutoDados} from "../interface/Produto.ts";
import "./card.css";
interface CartaoProdutoProps {
    produto: ProdutoDados;
}

export function CartaoProduto({ produto }: CartaoProdutoProps) {
    return (
        <div className="cartao">
            <img src={produto.imagem} alt={`Imagem de ${produto.nome}`} />

            <div className="cartao-conteudo">
                <h2>{produto.nome}</h2>
                <p>{produto.descricao}</p>
                <p><strong>Categoria:</strong> {produto.categoria}</p>
                <p><strong>Valor:</strong> R$ {produto.preco}</p>
                <p>
                    <strong>Status:</strong>{" "}
                    {produto.disponibilidade ? "Disponível" : "Indisponível"}
                </p>
            </div>
        </div>
    );
}