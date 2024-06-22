package org.DAO.Cliente;

import org.model.Cliente;
import org.model.Pet;

import java.util.ArrayList;

public class ClienteDAO implements ClienteDAOInterface {
//    String nome;
//    String sexo;
//    int idade;
//    String cpf;
//    String telefone;
    ArrayList<Cliente> clients = new ArrayList<Cliente>();

    //TODO: implementar o banco de dados para retirar dados mockados
    public ClienteDAO() {
        for (int i = 0; i < 10; i++) {
            clients.add(new Cliente("nome" + i, "sexo" + i, i, "cpf" + i, "telefone" + i));
        }
    }

    public String cadastrar(Cliente client){
        for(Cliente c : clients){
            if(c.getCpf().equals(client.getCpf())){
                return "CPF já cadastrado";
            }
        }
        clients.add(client);
        return "Cliente cadastrado com sucesso";
    }

    public ArrayList<Cliente> getAllClients() {
        return clients;
    }

    public Cliente getClienteByCpf(String cpf){
        for (Cliente cliente : clients) {
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public String alterar(Cliente editedClient){
        for(Cliente client : clients){
            if(client.getCpf().equals(editedClient.getCpf())){
                client.setNome(editedClient.getNome());
                client.setSexo(editedClient.getSexo());
                client.setIdade(editedClient.getIdade());
                client.setTelefone(editedClient.getTelefone());
                return "Cliente alterado com sucesso";
            }
        }
        return "Cliente não encontrado";
    }

    public String addPet(Pet newPet, Cliente owner){
        for(Pet pet : owner.getPets()){
            if(pet.getNome().equals(newPet.getNome())){
                return "Pet já cadastrado";
            }
        }
        owner.cadastrarPet(newPet);
        alterar(owner);
        return "Pet cadastrado com sucesso.";
    }

    public String removePet(Cliente owner, String petName){
        for(Pet pet : owner.getPets()){
            if(pet.getNome().equals(petName)){
                owner.removerPet(pet);
                alterar(owner);
                return "Pet removido com sucesso";
            }
        }
        return "Pet não encontrado";
    }

    public String alterarPet(Pet editedPet, Cliente owner, String petName){
        for(Pet pet : owner.getPets()){
            if(pet.getNome().equals(petName)){
                pet.setNome(editedPet.getNome());
                pet.setIdade(editedPet.getIdade());
                pet.setPorte(editedPet.getPorte());
                return "Pet alterado com sucesso";
            }
        }
        return "Pet não encontrado";
    }

    public String addGuardian(Pet pet, Cliente owner, String guardianName){
        for(String guardian : pet.getResponsaveis()){
            if(guardian.equals(guardianName)){
                return "Responsável ja na lista.";
            }
        }
        owner.adicionaResponsavelPet(guardianName, pet);
        return "Responsável adicionado.";
    }

    public String removeGuardian(Pet pet, Cliente owner, String guardianName){
        for(String guardian : pet.getResponsaveis()){
            if(guardian.equals(guardianName)){
                pet.removeResponsavel(guardianName);
                alterarPet(pet, owner, pet.getNome());
                return "Responsável removido.";
            }
        }
        return "Responsável não encontrado.";
    }
}
