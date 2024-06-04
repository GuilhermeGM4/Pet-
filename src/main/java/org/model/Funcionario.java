package org.model;

import java.util.ArrayList;

public class Funcionario extends Pessoa{
    private Funcao funcao;
    private ArrayList<String> diasTrabalho;
    private ArrayList<String> cargaTrabalho;

    public Funcionario(String nome, String sexo, int idade, String cpf, String telefone) {
        super(nome, sexo, idade, cpf, telefone);
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public ArrayList<String> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(ArrayList<String> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }

    public ArrayList<String> getCargaTrabalho() {
        return cargaTrabalho;
    }

    public void setCargaTrabalho(ArrayList<String> cargaTrabalho) {
        this.cargaTrabalho = cargaTrabalho;
    }

    public void modificaFuncao(Funcao novaFuncao) {
        this.funcao = novaFuncao;
    }

    public void modificaDiasTrabalho(ArrayList<String> novosDiasTrabalho) {
        this.diasTrabalho = new ArrayList<>(novosDiasTrabalho);
    }

    public void modificaCarga(ArrayList<String> novaCargaTrabalho) {
        this.cargaTrabalho = novaCargaTrabalho;
    }

    //Métodos Externos
    public void cadastraCliente(Cliente cliente)
    {
        cliente.cadastrarPet();
    }
    public void adicionaProduto(Produto produto, Servico servico)
    {
        servico.adicionaProduto();
    }
    public void finalizaServico(Servico servico)
    {
        servico.finalizarServico();
    }
    public void removeProduto(Servico servico,Produto produto)
    {
        servico.removeProduto();
    }
    public float getValorTotal (Servico servico){
        return servico.getValorTotal();
    }

    // Métodos de Gerenciamento para um Gerente
    public void gerenciarModificacaoFuncao(Funcionario funcionario, Funcao novaFuncao) {
        if (Funcao.GERENTE == funcionario.getFuncao()) {
            funcionario.modificaFuncao(novaFuncao);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem modificar a função de outros funcionários.");
        }
    }

    public void gerenciarModificacaoDiasTrabalho(Funcionario funcionario, ArrayList<String> novosDiasTrabalho) {
        if (Funcao.GERENTE == funcionario.getFuncao()) {
            funcionario.modificaDiasTrabalho(novosDiasTrabalho);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem modificar os dias de trabalho de outros funcionários.");
        }
    }

    public void gerenciarModificacaoCarga(Funcionario funcionario, ArrayList<String> novaCargaTrabalho) {
        if (Funcao.GERENTE == funcionario.getFuncao()) {
            funcionario.modificaCarga(novaCargaTrabalho);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem modificar a carga de trabalho de outros funcionários.");
        }
    }

    //Métodos para Gerenciamento de Estoque

    public void adicionaItemEstoque(Estoque estoque, Produto produto)
    {
        estoque.adicionarProduto(produto);
    }

    public void removeItemEstoque(Estoque estoque, Produto produto)
    {
        estoque.removerItem(produto);
    }

    public void pegarNomeItemEstoque(Estoque estoque, Produto produto)
    {
        estoque.pegarNomeProduto(produto);
    }




}
