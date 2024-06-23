package org.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Servico {
    private int id;
    private Map<Produto, Integer> produtos;
    private float valorTotal;
    private Cliente cliente;
    private ArrayList<Funcionario> funcionarios;
    private LocalDate data;
    private boolean finalizado;

    public Servico(int id, Cliente cliente, LocalDate data) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.produtos = new HashMap<>();
        this.funcionarios = new ArrayList<>();
        this.valorTotal = 0;
        this.finalizado = false;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, produtos.getOrDefault(produto, 0) + quantidade);
        calculaTotal();
    }

    public void removerProduto(Produto produto, int quantidade) {
        if (produtos.containsKey(produto)) {
            int quantidadeAtual = produtos.get(produto);
            if (quantidade >= quantidadeAtual) {
                produtos.remove(produto);
            } else {
                produtos.put(produto, quantidadeAtual - quantidade);
            }
            calculaTotal();
        }
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        if (!funcionarios.contains(funcionario)) {
            funcionarios.add(funcionario);
        }
    }

    private void calculaTotal() {
        valorTotal = 0;
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            valorTotal += entry.getKey().getValor() * entry.getValue();
        }
    }

    public void finalizarServico() {
        finalizado = true;
    }

    // Getters

    public int getId() {
        return id;
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isFinalizado() {
        return finalizado;
    }
}
