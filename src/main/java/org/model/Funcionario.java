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
}
