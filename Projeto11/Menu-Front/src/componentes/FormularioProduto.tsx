import { useState, useEffect } from "react";
import type { ProdutoDados } from "../interfaces/ProdutoDados";
import { useProdutoDadosMutate } from "../hooks/useProdutoDadosMutate";
import "./formularioProduto.css";

interface FormularioProps {
    produtoInicial?: ProdutoDados | null;
    onSubmit?: (dados: ProdutoDados) => void; // Prop opcional para o caso de edição
}

export function FormularioProduto({ produtoInicial, onSubmit }: FormularioProps) {
    // Inicializamos os estados com os valores do produtoInicial (se houver)
    const [nome, setNome] = useState(produtoInicial?.nome || "");
    const [descricao, setDescricao] = useState(produtoInicial?.descricao || "");
    const [preco, setPreco] = useState(produtoInicial?.preco?.toString() || "");
    const [categoria, setCategoria] = useState(produtoInicial?.categoria || "");
    const [imagem, setImagem] = useState(produtoInicial?.imagem || "");
    const [disponibilidade, setDisponibilidade] = useState(produtoInicial?.disponibilidade ?? true);

    const { mutate: cadastrar } = useProdutoDadosMutate();

    // Sincroniza os campos se o produto selecionado mudar enquanto o modal está aberto
    useEffect(() => {
        if (produtoInicial) {
            setNome(produtoInicial.nome);
            setDescricao(produtoInicial.descricao || "");
            setPreco(produtoInicial.preco.toString());
            setCategoria(produtoInicial.categoria);
            setImagem(produtoInicial.imagem);
            setDisponibilidade(produtoInicial.disponibilidade);
        }
    }, [produtoInicial]);

    function enviarFormulario(event: React.FormEvent) {
        event.preventDefault();

        const produto: ProdutoDados = {
            // Se for edição, mantemos o ID original
            ...(produtoInicial?.id && { id: produtoInicial.id }),
            nome,
            descricao,
            preco: Number(preco),
            categoria,
            imagem,
            disponibilidade
        };

        // Lógica: se houver a prop onSubmit (passada pelo App na edição), use-a.
        // Caso contrário, use a mutação de cadastro padrão.
        if (onSubmit) {
            onSubmit(produto);
        } else {
            cadastrar(produto);
            // Limpar campos apenas no cadastro
            limparCampos();
        }
    }

    const limparCampos = () => {
        setNome("");
        setDescricao("");
        setPreco("");
        setCategoria("");
        setImagem("");
        setDisponibilidade(true);
    }

    return (
        <form className="formulario" onSubmit={enviarFormulario}>
            {/* Título dinâmico */}
            <h2>{produtoInicial ? "Editar Produto" : "Novo Produto"}</h2>

            <input
                type="text"
                placeholder="Nome"
                value={nome}
                onChange={(e) => setNome(e.target.value)}
                required
            />

            <textarea
                placeholder="Descrição"
                value={descricao}
                onChange={(e) => setDescricao(e.target.value)}
            />

            <input
                type="number"
                placeholder="Preço"
                value={preco}
                onChange={(e) => setPreco(e.target.value)}
                required
            />

            <input
                type="text"
                placeholder="Categoria"
                value={categoria}
                onChange={(e) => setCategoria(e.target.value)}
            />

            <input
                type="text"
                placeholder="URL da imagem"
                value={imagem}
                onChange={(e) => setImagem(e.target.value)}
            />

            <label className="checkbox-label">
                <input
                    type="checkbox"
                    checked={disponibilidade}
                    onChange={(e) => setDisponibilidade(e.target.checked)}
                />
                Produto disponível
            </label>

            <button type="submit">
                {produtoInicial ? "Salvar Alterações" : "Cadastrar"}
            </button>
        </form>
    );
}