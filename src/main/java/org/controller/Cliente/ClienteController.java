package org.controller.Cliente;

import org.model.Cliente;
import org.model.Pet;
import org.model.Porte;
import org.model.Raca;

import java.util.ArrayList;

public class ClienteController {
    public void runTest() {
        ArrayList<Cliente> clients = new ArrayList<>();

        Cliente john = new Cliente("John Doe", "M", 30, "12345678900", "1234567890");
        clients.add(john);
        Pet johnPet = new Pet("Buddy", 5, Raca.GOLDEN_RETRIEVER, Porte.GRANDE);
        Pet johnPet2 = new Pet("Max", 3, Raca.BULLDOG, Porte.PEQUENO);

        Cliente steve = new Cliente("Steve Carell", "M", 32,"12345678900", "1234567890");
        clients.add(steve);
        Pet stevePet = new Pet("Pal", 2, Raca.VIRA_LATA, Porte.MEDIO);
        Pet stevePet2 = new Pet("Batman", 9, Raca.CHIHUAHUA, Porte.PEQUENO);

        john.cadastrarPet(johnPet);
        john.adicionaObservacaoPet("Health Status", "Healthy", johnPet);
        john.cadastrarPet(johnPet2);
        john.adicionaObservacaoPet("Health Status", "Healthy", johnPet2);
        john.adicionaResponsavelPet("Karen Doe", johnPet);

        steve.cadastrarPet(stevePet);
        steve.adicionaObservacaoPet("Health Status", "Healthy", stevePet);
        steve.cadastrarPet(stevePet2);
        steve.adicionaObservacaoPet("Health Status", "Healthy", stevePet2);

        for(Cliente client : clients) {
            System.out.println("Cliente:");
            System.out.println("Nome: " + client.getNome());
            System.out.println("Sexo: " + client.getSexo());
            System.out.println("Idade: " + client.getIdade());
            System.out.println("CPF: " + client.getCpf());
            System.out.println("Telefone: " + client.getTelefone());

            System.out.println("\n" + client.getNome() + "'s Pets");
            for (Pet pet : client.getPets()) {
                System.out.println("Nome: " + pet.getNome());
                System.out.println("Idade: " + pet.getIdade());
                System.out.println("Raça: " + pet.getRaca());
                System.out.println("Porte: " + pet.getPorte());
                System.out.println("Observação: " + pet.getObservacao("Health Status"));
                System.out.println("*---------------------------------------------------------------------------------*");
            }
        }
    }
}
