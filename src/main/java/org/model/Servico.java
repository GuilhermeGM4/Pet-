package org.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Servico {
    private int id;
    private ArrayList<Produto> produtos;
    private float valorTotal;
    private Cliente cliente;
    private ArrayList<Funcionario> funcionarios;
    private LocalDate data;
    private boolean finalizado;

    public Servico(int id) {
        this.id = id;
    }

    public void adicionaProduto(){}

    public void removeProduto(){}

    public void adicionaFuncionario(){}

    private void calculaTotal(){}

    public void finalizarServico(){
        finalizado = true;
    }

    public ArrayList<Produto> getProdutos() {
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
