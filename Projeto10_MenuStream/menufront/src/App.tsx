import './component/app.css'
import {useProdutoDados} from "./hooks/useProduto.ts";
import {CartaoProduto} from "./component/Card.tsx";

function App() {
  const { data } = useProdutoDados();

  return (
    <main className={"container"}>
          <h1>MenuStream - Produtos</h1>

          <section className="grade-cartoes">
            {data?.map((produto) => (
            <CartaoProduto key={produto.id} produto={produto} />
          ))}
    </section>
  </main>
  );
}

export default App
