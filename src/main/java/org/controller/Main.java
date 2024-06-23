package org.controller;

import org.DAO.LoaderDAO;
import org.controller.Cliente.ClienteController;

public class Main {
    public static void main(String[] args) {
        ClienteController clienteTest = new ClienteController();
        clienteTest.runTest();
        try {
            LoaderDAO loaderDAO = new LoaderDAO();
            loaderDAO.loadClientData();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
