package org.DAO.Cliente;

import org.model.Cliente;
import org.model.Pet;

import java.util.ArrayList;

public class ClienteDAO {
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
        for(Cliente c : clients){
            if(c.getCpf().equals(editedClient.getCpf())){
                clients.remove(c);
                clients.add(editedClient);
                return "Cliente alterado com sucesso";
            }
        }
        return "Cliente não encontrado";
    }

    public String alterarPet(Pet editedPet, Cliente owner){
        for(Pet pet : owner.getPets()){
            if(pet.getNome().equals(editedPet.getNome())){
                owner.removerPet(pet);
                owner.cadastrarPet(editedPet);
                return "Pet alterado com sucesso";
            }
        }
        return "Pet não encontrado";
    }
}
