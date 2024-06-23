package org.model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> produtosNoEstoque;
    private Map<String, Produto> produtosPorNome;

    public Estoque() {
        this.produtosNoEstoque = new HashMap<>();
        this.produtosPorNome = new HashMap<>();
    }

    // Adiciona um produto ao estoque com uma quantidade específica
    public void adicionarProduto(Produto produto, int quantidade) {
        produtosNoEstoque.put(produto, produtosNoEstoque.getOrDefault(produto, 0) + quantidade);
        produtosPorNome.put(produto.getNome(), produto);
    }

    // Remove uma quantidade específica de um produto do estoque
    public void removerProduto(Produto produto, int quantidade) {
        if (produtosNoEstoque.containsKey(produto)) {
            int quantidadeAtual = produtosNoEstoque.get(produto);
            if (quantidade >= quantidadeAtual) {
                produtosNoEstoque.remove(produto);
                produtosPorNome.remove(produto.getNome());
            } else {
                produtosNoEstoque.put(produto, quantidadeAtual - quantidade);
            }
        }
    }

    // Retorna o nome do produto
    public String pegarNomeProduto(Produto produto) {
        return produto.getNome();
    }

    // Reduz a quantidade de um produto no estoque
    public void reduzEstoque(Produto produto, int quantidade) {
        removerProduto(produto, quantidade);
    }

    // Gera um alerta se a quantidade de um produto estiver abaixo de um determinado limite
    public void alertaBaixoEstoque(Produto produto, int limite) {
        if (produtosNoEstoque.getOrDefault(produto, 0) < limite) {
            System.out.println("Alerta: Estoque baixo para o produto " + produto.getNome());
        }
    }

    // Métodos adicionais para obter informações sobre os produtos
    public Produto getProdutoPorNome(String nome) {
        return produtosPorNome.get(nome);
    }

    public Integer getQuantidadePorProduto(Produto produto) {
        return produtosNoEstoque.get(produto);
    }

    public Float getPrecoPorNome(String nome) {
        Produto produto = produtosPorNome.get(nome);
        return (produto != null) ? produto.getValor() : null;
    }

    // Getters e Setters

    public Map<Produto, Integer> getProdutosNoEstoque() {
        return produtosNoEstoque;
    }

    public void setProdutosNoEstoque(Map<Produto, Integer> produtosNoEstoque) {
        this.produtosNoEstoque = produtosNoEstoque;
    }

    public Map<String, Produto> getProdutosPorNome() {
        return produtosPorNome;
    }

    public void setProdutosPorNome(Map<String, Produto> produtosPorNome) {
        this.produtosPorNome = produtosPorNome;
    }
}
