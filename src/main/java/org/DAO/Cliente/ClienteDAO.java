package org.DAO.Cliente;

import org.model.Cliente;

import java.util.ArrayList;

public class ClienteDAO {
//    String nome;
//    String sexo;
//    int idade;
//    String cpf;
//    String telefone;
    ArrayList<Cliente> clients = new ArrayList<Cliente>();

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
}
