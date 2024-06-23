package org.model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
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
    public void adicionarProduto(Servico servico, Produto produto, int quantidade) {
        servico.adicionarProduto(produto, quantidade);
    }

    public void finalizarServico(Servico servico) {
        servico.finalizarServico();
    }

    public void removerProduto(Servico servico, Produto produto, int quantidade) {
        servico.removerProduto(produto, quantidade);
    }

    public float getValorTotal(Servico servico) {
        return servico.getValorTotal();
    }

    // Métodos para Gerenciamento de Estoque
    public void adicionarItemEstoque(Estoque estoque, Produto produto, int quantidade) {
        estoque.adicionarProduto(produto, quantidade);
    }

    public void removerItemEstoque(Estoque estoque, Produto produto, int quantidade) {
        estoque.removerProduto(produto, quantidade);
    }

    public String pegarNomeItemEstoque(Estoque estoque, Produto produto) {
        return estoque.pegarNomeProduto(produto);
    }

    public void reduzirItemEstoque(Estoque estoque, Produto produto, int quantidade) {
        estoque.reduzEstoque(produto, quantidade);
    }

    public void verificarAlertaBaixoEstoque(Estoque estoque, Produto produto, int limite) {
        estoque.alertaBaixoEstoque(produto, limite);
    }
}
