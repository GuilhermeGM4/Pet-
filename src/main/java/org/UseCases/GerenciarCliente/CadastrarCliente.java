package org.UseCases.GerenciarCliente;

import org.model.Cliente;

public class CadastrarCliente {
    private String nome;
    private String sexo;
    private int idade;
    private String cpf;
    private String telefone;

    public String cadastrar(String nome, String sexo, int idade, String cpf, String telefone){
        // Verifica se todos os campos são validos
        final String validationCheck = isValid(nome, sexo, idade, cpf, telefone);
        if(!validationCheck.isEmpty()) return validationCheck;

        // Logica de negocio
        Cliente cliente = new Cliente(nome, sexo, idade, cpf, telefone);
        //TODO: enviar para o DAO de cliente
        return "Cliente cadastrado";
    }

    private String isValid(String nome, String sexo, int idade, String cpf, String telefone){
        if(nome.isEmpty() || sexo.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) return "Preencha todos os campos";
        if(idade < 18) return "Cliente deve ter no minimo 18 anos.";
        if(sexo.length() > 1) return "Sexo deve ter no maximo 1 caracter.";
        if(cpf.length() != 11) return "CPF deve ter 11 digitos.";
        if(telefone.length() != 11) return "Telefone deve ter 11 digitos.";

        return "";
    }
}
