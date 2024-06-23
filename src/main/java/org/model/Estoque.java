package org.model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private final Map<String, Float> produtos = new HashMap<String, Float>();
    private final Map<String, Integer> quantidadeNoEstoque = new HashMap<String, Integer>();

    public Estoque() {
    }

    public void adicionarProduto(String nome, float preco, int quantidade){
        produtos.put(nome, preco);
        quantidadeNoEstoque.put(nome, quantidade);
    }

    public Map<String, Float> getProdutos(){
        return produtos;
    }

    public Map<String, Integer> getQuantidadeNoEstoque(){
        return quantidadeNoEstoque;
    }

    public Integer getQtdProduto(String nome){
        return quantidadeNoEstoque.get(nome);
    }

    public Float getPrecoProduto(String nome){
        return produtos.get(nome);
    }

    public void removerItem(){}

    public String pegarNomeProduto(){
        return "";
    }

    public void reduzEstoque(){}

    public void alertaBaixoEstoque(){}
}
