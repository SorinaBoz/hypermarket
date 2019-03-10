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
        List<Client> clients = clientDAO.getAll();
        Assert.assertEquals("Irina Mihai", client.getName());
    }

    @Test
    public void getClientByIdTest() {
        Client client = new Client();
        Client client1 = clientDAO.getClientById(67L);
        Assert.assertEquals("Irina Mihai", client1.getName());
    }

    @Test
    public void updateClientTest(){

        Client client = clientDAO.getClientById(65L);
        client.setName("Maria Lupu");
        clientDAO.updateClient(client);
        List<Client> clients = clientDAO.getAll();
        Assert.assertEquals("Maria Lupu", clients.get(0).getName());
    }

    @Test
    public void deleteClientTest(){
        Client client = clientDAO.getClientById(67L);
        clientDAO.deleteClient(client);
        List<Client> clients = clientDAO.getAll();
        Assert.assertTrue(clients.isEmpty());
    }

    @Test
    public void deleteClientTest1(){
        List<Client> clients = clientDAO.getAll();
        int size = clients.size();
        Client client = clientDAO.getClientById(68L);
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

        List<Client> clients = clientDAO.getAll();
        Assert.assertEquals(2, clients.size());
    }
}
