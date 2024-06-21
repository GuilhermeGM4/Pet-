package org.UseCases.GerenciarCliente;

import org.DAO.Cliente.ClienteDAO;
import org.model.Cliente;
import org.model.Pet;
import org.model.Porte;
import org.model.Raca;

public class EditarCliente {
    ClienteDAO dao = new ClienteDAO();
    Cliente client = new Cliente("John Doe", "Masculino", 19, "12345678900", "12345678901");

    public String editar(String newName, String cpf, String newAge, String newGender, String newPhone){
        if(newName.isEmpty() || newPhone.isEmpty() || newGender.isEmpty() || newAge.isEmpty()) return "Nenhum campo deve estar vazio.";
        if(Integer.parseInt(newAge) < 18) return "O cliente deve ter mais de 18 anos.";
        if(!(newGender.equals("Masculino") || newGender.equals("Feminino") || newGender.equals("Outro"))){
            return "Sexo inválido";
        }
        if(newPhone.length() != 11) return "Telefone deve ter 11 digitos.";

        String name = !newName.equals(client.getNome())? newName: client.getNome();
        String gender = !newGender.equals(client.getSexo())? newGender: client.getSexo();
        int age = Integer.parseInt(newAge) != client.getIdade()? Integer.parseInt(newAge): client.getIdade();
        String phone = !newPhone.equals(client.getTelefone())? newPhone: client.getTelefone();
        Cliente editedClient = new Cliente(name, gender, age, client.getCpf(), phone);
        return dao.alterar(editedClient);
    }

    public String addPet(String name, String age, String breed, String size){
        if(name.isEmpty() || age.isEmpty() || breed.isEmpty() || size.isEmpty()) return "Nenhum campo deve estar vazio.";
        if(Integer.parseInt(age) < 0) return "Idade inválida";
        if(!(size.equals("PEQUENO") || size.equals("MEDIO") || size.equals("GRANDE"))){
            return "Porte inválido";
        }

        Porte petSize = Porte.valueOf(size);
        Raca petBreed = Raca.valueOf(breed);
        Pet pet = new Pet(name, Integer.parseInt(age), petBreed, petSize);
        return dao.addPet(pet, client);
    }

    public void setClient(Cliente client){
        this.client = client;
    }
}
