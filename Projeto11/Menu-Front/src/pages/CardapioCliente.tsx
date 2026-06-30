import { useState } from "react";
import { useProdutoDados } from "../hooks/useProdutoDados.ts";
import type { ProdutoDados } from "../interfaces/ProdutoDados.ts";
import type { ItemPedidoDados } from "../interfaces/ItemPedidoDados.ts";
import { Navbar } from "../componentes/Navbar.tsx";
import { CartaoProduto } from "../componentes/CartaoProduto.tsx";
import { SacolaLateral } from "../componentes/SacolaLateral.tsx";
import "../pages/Menu.css"; // Reaproveita os estilos grid do seu Menu original

const CardapioCliente = () => {
    const { data: produtos } = useProdutoDados();
    const [sacola, setSacola] = useState<ItemPedidoDados[]>([]);

    // Adiciona o produto na sacola ou incrementa se ele já estiver lá
    const adicionarAoCarrinho = (produto: ProdutoDados) => {
        setSacola((itensAtuais) => {
            const itemExiste = itensAtuais.find((item) => item.produtoId.id === produto.id);

            if (itemExiste) {
                return itensAtuais.map((item) =>
                    item.produtoId.id === produto.id
                        ? { ...item, quantity: item.quantidade++ } // Corrigido para retornar o objeto atualizado de forma imutável:
                        : item
                );
            }
            return [...itensAtuais, { produtoId: produto, quantidade: 1 }];
        });
    };

    // Ajuste da função imutável para evitar efeitos colaterais com ++
    const handleAdicionarComSeguranca = (produto: ProdutoDados) => {
        setSacola((itensAtuais) => {
            const itemExiste = itensAtuais.find((item) => item.produtoId.id === produto.id);
            if (itemExiste) {
                return itensAtuais.map((item) =>
                    item.produtoId.id === produto.id
                        ? { ...item, quantidade: item.quantidade + 1 }
                        : item
                );
            }
            return [...itensAtuais, { produtoId: produto, quantidade: 1 }];
        });
    };

    return (
        <>
            <Navbar />
            <div className="layout-admin-dashboard"> {/* Reaproveita a estrutura flex lateral/conteúdo */}
                <main className="container" style={{ flex: 1 }}>
                    <div style={{ marginBottom: '30px' }}>
                        <h1>Faça seu Pedido</h1>
                        <p style={{ color: '#666' }}>Escolha os itens abaixo e monte sua sacola em tempo real.</p>
                    </div>

                    <section className="grade-cartoes">
                        {produtos?.filter(p => p.disponibilidade).map((produto) => (
                            <div key={produto.id} style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                                {/* Exibe seu cartão original */}
                                <CartaoProduto
                                    produto={produto}
                                    onVerDetalhes={() => {}} // Pode deixar vazio ou abrir modal de visualização se preferir
                                    onEditar={() => {}} // Oculto/Desabilitado na visão do cliente
                                    onDeletar={() => {}}
                                    onDesabilitar={() => {}}
                                />

                                <button
                                    onClick={() => handleAdicionarComSeguranca(produto)}
                                    style={{ width: '100%', padding: '10px', backgroundColor: '#2ec4b6', color: 'white', border: 'none', borderRadius: '4px', marginTop: '10px', cursor: 'pointer', fontWeight: 'bold' }}
                                >
                                    Adicionar à Sacola
                                </button>
                            </div>
                        ))}
                    </section>
                </main>

                {/* Painel Lateral que virou a Sacola */}
                <SacolaLateral sacola={sacola} setSacola={setSacola} />
            </div>
        </>
    );
};

export default CardapioCliente;