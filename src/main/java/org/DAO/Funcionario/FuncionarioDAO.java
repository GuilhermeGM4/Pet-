package org.DAO.Funcionario;

import org.DAO.LoaderDAO;
import org.model.Cliente;
import org.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    LoaderDAO loaderDAO = new LoaderDAO();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1; // Variável para controlar o próximo ID a ser atribuído


    public ArrayList<Funcionario> getAllFuncionarios() {
        return funcionarios;
    }

    public boolean adicionarFuncionario(Funcionario funcionario) {
        try {
            // Verifica se o funcionário já existe pelo CPF (ou outra identificação única)
            if (existeFuncionario(funcionario.getCpf())) {
                return false; // Já existe um funcionário com esse CPF
            }

            // Adiciona o funcionário à lista de funcionários no DAO
            funcionarios.add(funcionario);

            // Tente escrever os dados no arquivo JSON
            loaderDAO.writeEmployeeData(funcionarios);

            return true; // Funcionário adicionado com sucesso
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace da exceção para debug
            return false; // Erro ao adicionar funcionário
        }
    }

    public boolean existeFuncionario(String cpf) {
        // Verifica se já existe algum funcionário com o CPF fornecido
        return funcionarios.stream().anyMatch(f -> f.getCpf().equals(cpf));
    }

    public String atualizarFuncionario(Funcionario funcionarioEdited) {
        for (Funcionario func : funcionarios) {
            if (func.getCpf().equals(funcionarioEdited.getCpf())) {
                return updateThatFunc(funcionarioEdited, func);
            }
        }
        return "Funcionario não encontrado";
    }
    public Funcionario getFuncionarioByCPF(String cpf){
        for (Funcionario funcionario : funcionarios) {
            if(funcionario.getCpf().equals(cpf)){
                return funcionario;
            }
    }
    return null;
    }
    public String cadastrarFuncionario(Funcionario funcionario){
        try {
            for(Funcionario f : funcionarios){
                if(f.getCpf().equals(funcionario.getCpf())){
                    return "CPF já cadastrado";
                }
            }
            funcionarios.add(funcionario);

            // Tente escrever os dados no arquivo JSON
            loaderDAO.writeEmployeeData(funcionarios);

            return "Funcionário cadastrado com sucesso!";
        } catch (Exception e){
            e.printStackTrace(); // Imprime o stack trace da exceção para debug
            return "Erro ao cadastrar funcionário: " + e.getMessage();
        }
    }
    private String updateThatFunc(Funcionario editedClient, Funcionario funcionario){
        funcionarios.remove(funcionario);
        funcionarios.add(editedClient);
        try{
            loaderDAO.writeEmployeeData(funcionarios);
        }catch (Exception e){
        return e.getMessage();
        }
        return "Alterado com sucesso.";
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
