import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Client;
import ro.sda.hypermarket.core.service.ClientService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ClientDaoTest {

    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    @Transactional
    public void createClientTest() {

        Client client = new Client();
        client.setName("Argentina Mihai");
        clientService.create(client, false);
        Assert.assertNotNull(client);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getClientByIdTest() {
        Client expectedClient = clientService.getClientById(71L,false);
        List<Client> clients = clientService.findAll(false);
        Assert.assertEquals(expectedClient, clients.get(0));
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getClientByNameTest(){
        Client actualClient = clientService.getClientById(72L, false);
        Client expectedClient = clientService.getClientByName("Lucretia Micu", false);
        Assert.assertEquals(expectedClient, actualClient);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void updateClientTest(){

        Client client = clientService.getClientById(74L, false);
        client.setName("Maria Ioana");
        clientService.update(client, false);
//        Client actualClient = clientService.getClientByName("Maria Ioana", false);
//        Client expectedClient = client;
//        Assert.assertEquals(expectedClient, actualClient);
    }

//    @Test
//    @Rollback(false)
//    @Transactional
//    public void deleteClientTest(){
//        Client client = clientService.getClientById(67L, false);
//        clientService.delete(client, false);
//        List<Client> clients = clientService.findAll(false);
//        Assert.assertTrue(clients.isEmpty());
//    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteClientTest(){
        List<Client> clients = clientService.findAll(false);
        int size = clients.size();
        Client client = clientService.getClientById(78L, false);
        clientService.delete(client, false);
        clients = clientService.findAll(false);
        Assert.assertEquals(size - 1, clients.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllClientsTest() {

        List<Client> actualClients = clientService.findAll(false);
        Assert.assertEquals(8, actualClients.size());
    }
}
