package org.example.businessLayer;

import org.example.dataAccessLayer.ClientDAO;
import org.example.model.Client;

import java.util.List;


public class ClientBLL {

    private final ClientDAO clientDAO = new ClientDAO();

    public List<Client> findAll(){
        return clientDAO.findAll();
    }

    public Client findById(int id){
        Client client = clientDAO.findById(id);

        if(client == null){
            throw new IllegalArgumentException("Clientul nu exista");
        }

        return client;
    }

    public void insert(Client client){
        validateClient(client);
        clientDAO.insert(client);
    }

    public void update(Client client){
        validateClient(client);
        clientDAO.update(client);
    }

    public void delete(int id){
        findById(id);
        clientDAO.delete(id);
    }

    private void validateClient(Client client){
        if(client.getName() == null || client.getName().isBlank()){
            throw new IllegalArgumentException("Numele clientului nu poate fi gol.");
        }

        if(client.getEmail() == null || client.getEmail().isBlank()){
            throw new IllegalArgumentException("Emailul clientului nu poate fi gol.");
        }

        if(client.getAddress() == null || client.getAddress().isBlank()){
            throw new IllegalArgumentException("Adresa clientului nu poate fi goala.");
        }
    }
}
