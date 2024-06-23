package org.model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa{
    private Funcao funcao;
    private List<String> diasTrabalho;
    private List<String> cargaTrabalho;

    public Funcionario(String nome, String sexo, int idade, String cpf, String telefone) {
        super(nome, sexo, idade, cpf, telefone);
        this.diasTrabalho = new ArrayList<>();
        this.cargaTrabalho = new ArrayList<>();
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public List<String> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(List<String> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }

    public List<String> getCargaTrabalho() {
        return cargaTrabalho;
    }

    public void setCargaTrabalho(List<String> cargaTrabalho) {
        this.cargaTrabalho = cargaTrabalho;
    }

    public void modificaFuncao(Funcao novaFuncao) {
        this.funcao = novaFuncao;
    }

    public void modificaDiasTrabalho(List<String> novosDiasTrabalho) {
        this.diasTrabalho = new ArrayList<>(novosDiasTrabalho);
    }

    public void modificaCarga(List<String> novaCargaTrabalho) {
        this.cargaTrabalho = novaCargaTrabalho;
    }

    // Métodos de Serviço
    public void adicionaProduto(Servico servico, Produto produto, int quantidade) {
        servico.adicionarProduto(produto, quantidade);
    }

    public void finalizaServico(Servico servico) {
        servico.finalizarServico();
    }

    public void removeProduto(Servico servico, Produto produto, int quantidade) {
        servico.removerProduto(produto, quantidade);
    }

    public float getValorTotal(Servico servico) {
        return servico.getValorTotal();
    }

    // Métodos para Gerenciamento de Estoque
    public void adicionaItemEstoque(Estoque estoque, Produto produto, int quantidade) {
        estoque.adicionarProduto(produto, quantidade);
    }

    public void removeItemEstoque(Estoque estoque, Produto produto, int quantidade) {
        estoque.removerProduto(produto, quantidade);
    }

    public String pegarNomeItemEstoque(Estoque estoque, Produto produto) {
        return estoque.pegarNomeProduto(produto);
    }




}
