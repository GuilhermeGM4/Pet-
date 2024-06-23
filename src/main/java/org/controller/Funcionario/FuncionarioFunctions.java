package org.controller.Funcionario;

import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Funcionario;

import java.util.List;

public class FuncionarioFunctions {
    private FuncionarioDAO funcionarioDAO;

    public FuncionarioFunctions() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public void cadastrarFuncionario(Funcionario funcionario, Funcionario gerente) {
        if (funcionarioDAO.isGerente(gerente.getId())) {
            funcionarioDAO.adicionarFuncionario(funcionario);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem cadastrar novos funcionários.");
        }
    }

    public void atualizarFuncionario(Funcionario funcionario, Funcionario gerente) {
        if (funcionarioDAO.isGerente(gerente.getId())) {
            funcionarioDAO.atualizarFuncionario(funcionario);
        } else {
            throw new UnsupportedOperationException("Somente gerentes podem atualizar funcionários.");
        }
    }

    public List<String> obterHistoricoServicoPrestado(int funcionarioId) {
        return funcionarioDAO.historicoServicoPrestado(funcionarioId);
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioDAO.listarTodos();
    }

}
