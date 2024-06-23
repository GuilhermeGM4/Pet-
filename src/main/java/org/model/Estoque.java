package org.model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> produtos;

    public Estoque() {
        this.produtos = new HashMap<>();
    }

    // Adiciona um produto ao estoque com uma quantidade específica
    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, produtos.getOrDefault(produto, 0) + quantidade);
    }

    // Remove uma quantidade específica de um produto do estoque
    public void removerProduto(Produto produto, int quantidade) {
        if (produtos.containsKey(produto)) {
            int quantidadeAtual = produtos.get(produto);
            if (quantidade >= quantidadeAtual) {
                produtos.remove(produto);
            } else {
                produtos.put(produto, quantidadeAtual - quantidade);
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
        if (produtos.getOrDefault(produto, 0) < limite) {
            System.out.println("Alerta: Estoque baixo para o produto " + produto.getNome());
        }
    }

    // Getters e Setters

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<Produto, Integer> produtos) {
        this.produtos = produtos;
    }
}
