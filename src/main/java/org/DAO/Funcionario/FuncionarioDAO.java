package org.DAO.Funcionario;

import org.model.Funcao;
import org.model.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FuncionarioDAO {
    void adicionarFuncionario(Funcionario funcionario);
    void atualizarFuncionario(Funcionario funcionario);
    Funcionario buscarFuncionario(int id);
    List<Funcionario> listarFuncionarios();
    void removerFuncionario(int id);
}
