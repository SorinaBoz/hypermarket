package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Client;

import java.util.List;

public interface ClientService {

    Client create(Client client, boolean useHibernate);

    Client update(Client client, boolean useHibernate);

    Client getClientById(Long id, boolean useHibernate);

    Client getClientByName(String name, boolean useHibernate);

    List<Client> findAll(boolean useHibernate);

    void delete(Client client, boolean useHibernate);
}