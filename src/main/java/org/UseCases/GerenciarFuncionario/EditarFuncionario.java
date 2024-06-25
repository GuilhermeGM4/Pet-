package org.UseCases.GerenciarFuncionario;

import org.DAO.Funcionario.FuncionarioDAO;
import org.model.Funcionario;

public class EditarFuncionario {
    FuncionarioDAO funcionarioDAO;

    public String editarFuncionario(int id, String newName, String newCpf, String newAge, String newGender, String newPhone, String newFuncao, ArrayList<String> newDiasTrabalho, ArrayList<String> newCargaTrabalho) {
        if (newName.isEmpty() || newPhone.isEmpty() || newGender.isEmpty() || newAge.isEmpty() || newCpf.isEmpty())
            return "Nenhum campo deve estar vazio.";
        if (Integer.parseInt(newAge) < 18)
            return "O funcionário deve ter mais de 18 anos.";
        if (!(newGender.equals("Masculino") || newGender.equals("Feminino") || newGender.equals("Outro")))
            return "Sexo inválido.";
        if (newPhone.length() != 11)
            return "Telefone deve ter 11 dígitos.";
        Funcao funcao;
        try {
            funcao = Funcao.valueOf(newFuncao);
        } catch (IllegalArgumentException e) {
            return "Função inválida.";
        }
        Funcionario funcionario = funcionarioDAO.buscarFuncionario(id);
        if (funcionario == null)
            return "Funcionário não encontrado.";
        funcionario.setNome(newName);
        funcionario.setCpf(newCpf);
        funcionario.setIdade(Integer.parseInt(newAge));
        funcionario.setSexo(newGender);
        funcionario.setTelefone(newPhone);
        funcionario.setFuncao(funcao);
        funcionario.setDiasTrabalho(newDiasTrabalho);
        funcionario.setCargaTrabalho(newCargaTrabalho);
        return funcionarioDAO.atualizarFuncionario(funcionario);
    }

}
