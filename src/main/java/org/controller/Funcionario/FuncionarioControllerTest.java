package org.controller.Funcionario;

import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Funcao;
import org.model.Funcionario;
import org.model.Gerente;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioControllerTest {

    public static void main(String[] args) {
        // Instanciar o DAO (pode ser um DAO mockado ou uma implementação concreta)
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(); // Supondo que você tem uma implementação concreta

        // Instanciar o controlador
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioDAO);

        // Adicionar funcionários
        Gerente gerente1 = new Gerente(1, "João", "M", 30, "123456789", "9999-9999");
        gerente1.setDiasTrabalho(new ArrayList<>(List.of("Segunda", "Terça")));
        gerente1.setCargaTrabalho(new ArrayList<>(List.of("08:00-17:00")));

        funcionarioController.adicionarFuncionario(gerente1);

        Funcionario funcionario2 = new Funcionario(2, "Maria", "F", 25, "987654321", "8888-8888");
        funcionario2.setFuncao(Funcao.ATENDENTE);
        funcionario2.setDiasTrabalho(new ArrayList<>(List.of("Quarta", "Quinta")));
        funcionario2.setCargaTrabalho(new ArrayList<>(List.of("09:00-18:00")));

        funcionarioController.adicionarFuncionario(funcionario2);

        // Atualizar um funcionário
        gerente1.setNome("João da Silva");
        funcionarioController.atualizarFuncionario(gerente1);

        // Buscar um funcionário
        Funcionario funcionarioBuscado = funcionarioController.buscarFuncionario(gerente1.getId());
        System.out.println("Funcionário buscado: " + funcionarioBuscado.getNome());

        // Listar todos os funcionários
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        System.out.println("Lista de funcionários:");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", Função: " + f.getFuncao());
        }

        // Remover um funcionário
        funcionarioController.removerFuncionario(funcionario2.getId());
        System.out.println("Funcionário removido: " + funcionario2.getNome());

        // Listar todos os funcionários novamente
        funcionarios = funcionarioController.listarFuncionarios();
        System.out.println("Lista de funcionários após remoção:");
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome() + ", Função: " + f.getFuncao());
        }
    }
}
