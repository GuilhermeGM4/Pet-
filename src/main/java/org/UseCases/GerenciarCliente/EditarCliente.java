package org.UseCases.GerenciarCliente;

import org.model.Cliente;

public class EditarCliente {
    //TODO: retirar sets diretos em client e levar eles para o DAO
    Cliente client = new Cliente("John Doe", "Masculino", 19, "12345678900", "12345678901");
    public String editar(String newName, String cpf, String newPhone, int newAge, String newGender){
        if(newName.isEmpty() || newPhone.isEmpty() || newGender.isEmpty()) return "Nenhum campo deve estar vazio.";
        if(newAge < 18) return "O cliente deve ter mais de 18 anos.";
        if(!(newGender.equals("Masculino") || newGender.equals("Feminino") || newGender.equals("Outro"))){
            return "Sexo inválido";
        }
        if(newPhone.length()!= 11) return "Telefone deve ter 11 digitos.";

        String name = !newName.equals(client.getNome())? newName: client.getNome();
        String gender = !newGender.equals(client.getSexo())? newGender: client.getSexo();
        int age = newAge != client.getIdade()? newAge: client.getIdade();
        String phone = !newPhone.equals(client.getTelefone())? newPhone: client.getTelefone();
        Cliente editedClient = new Cliente(name, gender, age, cpf, phone); //utilizar no DAO
        client.setNome(name);
        client.setSexo(gender);
        client.setIdade(age);
        client.setTelefone(phone);
        return "Modificado com sucesso";
    }
}
