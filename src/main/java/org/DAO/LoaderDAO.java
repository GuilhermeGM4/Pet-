package org.DAO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.model.Cliente;
import org.model.Pet;
import org.model.Porte;
import org.model.Raca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoaderDAO {
    //TODO: remove main and static from methods, then move clients and envUsername outside of loadData
    public static void main(String[] args) throws Exception {
        loadData();
    }

    public static ArrayList<Cliente> loadData() throws IOException, ParseException {
        ArrayList<Cliente> clients = new ArrayList<>();
        final String envUsername = System.getenv("USERNAME");

        final File filePath = new File("C:\\Users\\"+envUsername+"\\Desktop\\data.json");
        if(!filePath.exists()){
            System.out.println("File does not exist, creating...");
            if(filePath.createNewFile()){
                System.out.println("File created: " + filePath.getAbsolutePath());
                return null;
            }
        }
        if(!filePath.canRead()){
            throw new IllegalStateException("File cannot be read.");
        }
        if(!filePath.canWrite()){
            throw new IllegalStateException("File cannot be written.");
        }

        JSONParser parser = new JSONParser();
        try{
            Object fileObj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) fileObj;
            ArrayList<JSONObject> clientsJson = (ArrayList<JSONObject>) jsonObject.get("clientes");
            for(JSONObject client : clientsJson){
                clients.add(convertJsonToCliente(client));
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return clients;
    }

    public String writeData(){
        return null;
    }

    private static Cliente convertJsonToCliente(JSONObject clientJson){
        String name = (String) clientJson.get("nome");
        String sex = (String) clientJson.get("sexo");
        Long ageLong = (Long) clientJson.get("idade");
        int age = ageLong.intValue();
        String cpf = (String) clientJson.get("cpf");
        String phone = (String) clientJson.get("telefone");
        Cliente client = new Cliente(name, sex, age, cpf, phone);
        ArrayList<JSONObject> petsJson = (ArrayList<JSONObject>) clientJson.get("pets");
        for(JSONObject pet : petsJson){
            client.cadastrarPet(convertJsonToPet(pet));
        }
        return client;
    }

    private static Pet convertJsonToPet(JSONObject petJson){
        String name = (String) petJson.get("nome");
        Long ageLong = (Long) petJson.get("idade");
        int age = ageLong.intValue();
        String breed = (String) petJson.get("raca");
        String size = (String) petJson.get("porte");
        ArrayList<String> guardians = (ArrayList<String>) petJson.get("responsaveis");
        Map<String, String> observations = (Map<String, String>) petJson.get("observacoes");
        Pet pet = new Pet(name, age, Raca.valueOf(breed), Porte.valueOf(size));
        for(String guardian : guardians){
            pet.addResponsavel(guardian);
        }
        for (String key : observations.keySet()) {
            pet.addObservacao(key, observations.get(key));
        }
        return pet;
    }
}
