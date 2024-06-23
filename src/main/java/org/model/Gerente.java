package org.model;

import java.util.List;

public class Gerente extends Funcionario {

    public Gerente(String nome, String sexo, int idade, String cpf, String telefone) {
        super(nome, sexo, idade, cpf, telefone);
        setFuncao(Funcao.GERENTE);
    }

    public void gerenciarModificacaoFuncao(Funcionario funcionario, Funcao novaFuncao) {
        funcionario.modificaFuncao(novaFuncao);
    }

    public void gerenciarModificacaoDiasTrabalho(Funcionario funcionario, List<String> novosDiasTrabalho) {
        funcionario.modificaDiasTrabalho(novosDiasTrabalho);
    }

    public void gerenciarModificacaoCarga(Funcionario funcionario, List<String> novaCargaTrabalho) {
        funcionario.modificaCarga(novaCargaTrabalho);
    }
}
