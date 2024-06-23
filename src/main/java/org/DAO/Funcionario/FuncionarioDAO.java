package org.DAO.Funcionario;

import org.model.Funcao;
import org.model.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioDAO implements FuncionarioDAOInterface {
    private List<Funcionario> funcionarios;

    public FuncionarioDAO() {
        this.funcionarios = new ArrayList<>();
    }

    @Override
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    @Override
    public void atualizarFuncionario(Funcionario funcionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == funcionario.getId()) {
                funcionarios.set(i, funcionario);
                return;
            }
        }
    }

    @Override
    public Optional<Funcionario> buscarFuncionarioPorId(int id) {
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst();
    }

    @Override
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }

    @Override
    public List<String> historicoServicoPrestado(int funcionarioId) {
        Optional<Funcionario> funcionario = buscarFuncionarioPorId(funcionarioId);
        return funcionario.map(Funcionario::getServicos).orElse(new ArrayList<>());
    }

    @Override
    public boolean isGerente(int funcionarioId) {
        Optional<Funcionario> funcionario = buscarFuncionarioPorId(funcionarioId);
        return funcionario.map(f -> f.getFuncao() == Funcao.GERENTE).orElse(false);
    }
}
