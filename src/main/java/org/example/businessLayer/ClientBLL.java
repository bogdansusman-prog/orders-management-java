package org.example.businessLayer;

import org.example.dataAccessLayer.ClientDAO;
import org.example.model.Client;

import java.util.List;

/**
 * Clasa responsabila pentru logica de business asociata clientilor.
 * Realizeaza validarea datelor si operatiile CRUD pentru clienti.
 */
public class ClientBLL {

    private final ClientDAO clientDAO = new ClientDAO();

    /**
     * Returneaza toti clientii existenti.
     *
     * @return lista de clienti
     */
    public List<Client> findAll(){
        return clientDAO.findAll();
    }

    /**
     * Cauta un client dupa id.
     *
     * @param id id-ul clientului
     * @return clientul gasit
     * @throws IllegalArgumentException daca clientul nu exista
     */
    public Client findById(int id){
        Client client = clientDAO.findById(id);

        if(client == null){
            throw new IllegalArgumentException("Clientul nu exista");
        }

        return client;
    }

    /**
     * Insereaza un client nou dupa validarea datelor.
     *
     * @param client clientul care va fi inserat
     */
    public void insert(Client client){
        validateClient(client);
        clientDAO.insert(client);
    }

    /**
     * Actualizeaza datele unui client existent.
     *
     * @param client clientul care va fi actualizat
     */
    public void update(Client client){
        validateClient(client);
        clientDAO.update(client);
    }

    /**
     * Sterge un client dupa id.
     *
     * @param id id-ul clientului
     */
    public void delete(int id){
        findById(id);
        clientDAO.delete(id);
    }

    /**
     * Valideaza datele clientului.
     *
     * @param client clientul verificat
     * @throws IllegalArgumentException daca datele sunt invalide
     */
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
