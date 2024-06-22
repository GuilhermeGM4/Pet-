package org.DAO;

import org.model.Cliente;
import org.model.Estoque;
import org.model.Funcionario;
import org.model.Servico;

import java.util.ArrayList;

public interface LoaderDAOInterface {
    public Cliente loadClientData();
    public Funcionario loadEmployeeData();
    public Servico loadServiceData();
    public Estoque loadInventoryData();

    public void writeClientsData(ArrayList<Cliente> clients);
    public void writeEmployeeData(ArrayList<Funcionario> employees);
    public void writeServiceData(ArrayList<Servico> services);
    public void writeInventoryData(ArrayList<Estoque> inventory);

    public void writeData();

}