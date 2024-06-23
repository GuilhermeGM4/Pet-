package org.DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class LoaderDAO implements LoaderDAOInterface {
    private ArrayList<Cliente> clients = new ArrayList<>();
    private ArrayList<Funcionario> employees = new ArrayList<>();
    private ArrayList<Servico> services = new ArrayList<>();
    private Estoque inventory = new Estoque();

    private final String envUsername = System.getenv("USERNAME");
    private final File filePath = new File("C:\\Users\\"+envUsername+"\\Desktop\\data.json");

    public ArrayList<Cliente> loadClientData() {
        try {
            if (!filePath.exists()) {
                System.out.println("File does not exist, creating...");
                if (filePath.createNewFile()) {
                    System.out.println("File created: " + filePath.getAbsolutePath());
                    return new ArrayList<>();
                }
            }
        } catch (IOException e){
            System.out.println("Failed to create file.");
            System.out.println(e.getMessage());
            return null;
        }

        try{
            JSONObject jsonObject = (JSONObject) loadFileData();
            ArrayList<JSONObject> clientsJson = (ArrayList<JSONObject>) jsonObject.get("clientes");
            for(JSONObject client : clientsJson){
                clients.add(convertJsonToCliente(client));
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return clients;
    }

    @Override
    public ArrayList<Funcionario> loadEmployeeData() {
        //TODO implement loadEmployeeData to transform employee data from the json file into Funcionario objects
        return new ArrayList<Funcionario>();
    }

    @Override
    public ArrayList<Servico> loadServiceData() {
        //TODO implement loadServiceData to transform service data from the json file into Servico objects
        return new ArrayList<Servico>();
    }

    @Override
    public Estoque loadInventoryData() {
        try{
            JSONObject jsonObject = (JSONObject) loadFileData();
            ArrayList<JSONObject> inventoryJson = (ArrayList<JSONObject>) jsonObject.get("estoque");
            for(JSONObject product : inventoryJson){
                convertJsonToEtoque(product);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return inventory;
    }

    @Override
    public void writeClientsData(ArrayList<Cliente> clients) {
        this.clients = clients;
        loadEmployeeData();
        loadServiceData();
        loadInventoryData();
        try {
            writeData();
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Client stored.");
    }

    @Override
    public void writeEmployeeData(ArrayList<Funcionario> employees) {
        this.employees = employees;
        loadClientData();
        loadServiceData();
        loadInventoryData();
        try {
            writeData();
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Employee stored.");
    }

    @Override
    public void writeServiceData(ArrayList<Servico> services) {
        this.services = services;
        loadClientData();
        loadEmployeeData();
        loadInventoryData();
        try {
            writeData();
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Service stored.");
    }

    @Override
    public void writeInventoryData(Estoque inventory) {
        this.inventory = inventory;
        loadClientData();
        loadEmployeeData();
        loadServiceData();
        try {
            writeData();
        }catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Inventory stored.");
    }

    private void writeData() throws IOException {
        JSONObject jsonObject = new JSONObject();
        if(!filePath.exists()){
            if(filePath.createNewFile()){
                System.out.println("File created: " + filePath.getAbsolutePath());
            }
        }
        try{
            if(clients.isEmpty()) loadClientData();
            ArrayList<JSONObject> clientsJson = new ArrayList<>();
            for(Cliente client : clients){
                clientsJson.add(convertClienteToJsonObject(client));
            }
            jsonObject.put("clientes", clientsJson);
            jsonObject.put("funcionarios", employees);
            jsonObject.put("servicos", services);
            jsonObject.put("estoque", inventory);
            FileWriter writer = new FileWriter(filePath);
            writer.write(jsonObject.toJSONString());
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private Object loadFileData() throws IOException, ParseException {
        if(!filePath.canRead()){
            throw new IllegalStateException("File cannot be read.");
        }
        if(!filePath.canWrite()){
            throw new IllegalStateException("File cannot be written.");
        }
        JSONParser parser = new JSONParser();
        return parser.parse(new FileReader(filePath));
    }

    private Cliente convertJsonToCliente(JSONObject clientJson){
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

    private Pet convertJsonToPet(JSONObject petJson){
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

    private void convertJsonToEtoque(JSONObject inventoryJson){
        String name = (String) inventoryJson.get("nome");
        Long quantityLong = (Long) inventoryJson.get("quantidade");
        int quantity = quantityLong.intValue();
        Double priceDouble = (Double) inventoryJson.get("preco");
        float price = priceDouble.floatValue();
//        Estoque estoque = new Estoque();
        inventory.adicionarProduto(name, price, quantity);
//        return estoque;
    }

    private JSONObject convertClienteToJsonObject(Cliente cliente) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("nome", cliente.getNome());
        jsonObject.put("sexo", cliente.getSexo());
        jsonObject.put("idade", cliente.getIdade());
        jsonObject.put("cpf", cliente.getCpf());
        jsonObject.put("telefone", cliente.getTelefone());
        for(Pet pet : cliente.getPets()){
            jsonArray.add(convertPetToJsonObject(pet));
        }
        jsonObject.put("pets", jsonArray);
        return jsonObject;
    }

    private JSONObject convertPetToJsonObject(Pet pet) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nome", pet.getNome());
        jsonObject.put("idade", pet.getIdade());
        jsonObject.put("raca", pet.getRaca().toString());
        jsonObject.put("porte", pet.getPorte().toString());
        jsonObject.put("responsaveis", pet.getResponsaveis());
        jsonObject.put("observacoes", pet.getObservacoes());
        return jsonObject;
    }
}
