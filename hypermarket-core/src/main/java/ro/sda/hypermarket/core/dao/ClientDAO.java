package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.Client;

import java.util.List;

public interface ClientDAO {

    Client createClient(Client client);

    Client getClientById(Long id);

    List<Client> getAll();

    Client updateClient(Client client);

    void deleteClient(Client client);
}
