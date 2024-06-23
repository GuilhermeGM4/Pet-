package org.model;

import java.util.ArrayList;

public class Gerente extends Funcionario {

    public Gerente(int id, String nome, String sexo, int idade, String cpf, String telefone) {
        super(id, nome, sexo, idade, cpf, telefone);
        setFuncao(Funcao.GERENTE);
    }

    public void gerenciarModificacaoDiasTrabalho(Funcionario funcionario, ArrayList<String> novosDiasTrabalho) {
        funcionario.modificaDiasTrabalho(novosDiasTrabalho);
    }

    public void gerenciarModificacaoCarga(Funcionario funcionario, ArrayList<String> novaCargaTrabalho) {
        funcionario.modificaCarga(novaCargaTrabalho);
    }
}
