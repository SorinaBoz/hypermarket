import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.ClientDAO;
import ro.sda.hypermarket.core.entity.Client;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ClientDaoTest {

    @Autowired
    private ClientDAO clientDAO;

    @Test
    public void createClientTest() {

        Client client = new Client();
        client.setName("Irina Mihai");
        clientDAO.createClient(client);
        Client actualClient = clientDAO.getClientByName("Irina Mihai");
        Client expectedClient = client;
        Assert.assertEquals(expectedClient, actualClient);
    }

    @Test
    public void getClientByIdTest() {
        Client expectedClient = clientDAO.getClientById(71L);
        List<Client> clients = clientDAO.getAll();
        Assert.assertEquals(expectedClient, clients.get(0));
    }

    @Test
    public void getClientByNameTest(){
        Client actualClient = clientDAO.getClientById(71L);
        Client expectedClient = clientDAO.getClientByName("Madalina Georgiana");
        Assert.assertEquals(expectedClient, actualClient);
    }

    @Test
    public void updateClientTest(){

        Client client = clientDAO.getClientById(72L);
        client.setName("Lucretia Micu");
        clientDAO.updateClient(client);
        Client actualClient = clientDAO.getClientByName("Lucretia Micu");
        Client expectedClient = client;
        Assert.assertEquals(expectedClient, actualClient);
    }

//    @Test
//    public void deleteClientTest(){
//        Client client = clientDAO.getClientById(67L);
//        clientDAO.deleteClient(client);
//        List<Client> clients = clientDAO.getAll();
//        Assert.assertTrue(clients.isEmpty());
//    }

    @Test
    public void deleteClientTest1(){
        List<Client> clients = clientDAO.getAll();
        int size = clients.size();
        Client client = clientDAO.getClientById(75L);
        clientDAO.deleteClient(client);
        clients = clientDAO.getAll();
        Assert.assertEquals(size - 1, clients.size());
    }

    @Test
    public void getAllClientsTest() {

        Client client = new Client();
        client.setName("Madalina Georgiana");
        clientDAO.createClient(client);

        Client client1 = new Client();
        client1.setName("Mardare Cristina");
        clientDAO.createClient(client1);

        List<Client> actualClients = clientDAO.getAll();
        Assert.assertEquals(8, actualClients.size());
    }
}
