package org.UseCases.GerenciarFuncionario;

import org.DAO.Cliente.ClienteDAO;
import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Cliente;
import org.model.Funcionario;

import java.util.ArrayList;

public class ManageFuncionario {
    Funcionario funcionario;
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public ArrayList<Funcionario> getAllClients(){
        return funcionarioDAO.getAllFuncionarios();
    }

    public Funcionario getFuncionarioByCPF(String cpf){
        return funcionarioDAO.getFuncionarioByCPF(cpf);
    }
}
