package org.DAO.Funcionario;

import org.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1; // Variável para controlar o próximo ID a ser atribuído

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionario.setId(proximoId); // Define o ID do funcionário
        funcionarios.add(funcionario);
        proximoId++; // Incrementa o próximo ID para o próximo funcionário
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        // Implementar lógica para atualizar o funcionário na lista
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == funcionario.getId()) {
                funcionarios.set(i, funcionario);
                break;
            }
        }
    }

    public Funcionario buscarFuncionario(int id) {
        // Implementar lógica para buscar o funcionário pelo ID
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public void removerFuncionario(int id) {
        // Implementar lógica para remover o funcionário pelo ID
        funcionarios.removeIf(f -> f.getId() == id);
    }
}
