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

    public void cadastrar(Cliente client){
        clients.add(client);
    }

    public ArrayList<Cliente> getAllClients() {
        return clients;
    }
}
