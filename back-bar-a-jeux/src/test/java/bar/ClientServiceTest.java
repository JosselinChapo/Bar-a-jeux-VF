package bar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import bar.exception.ClientException;
import bar.exception.IdException;
import bar.model.Civilite;
import bar.model.Client;
import bar.service.ClientService;


@SpringBootTest
@Transactional
@Rollback
public class ClientServiceTest {

	@Autowired 
	ClientService clientSrv;


	@Test
	void injectionClientServiceTest() {
		assertNotNull(clientSrv);
	}

	@Test
	void saveClientTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		assertNotNull(clientSrv.findById(client1.getId()));
	}

	@Test
	void createWithBirthdayTest () {
		Client client2 = clientSrv.createWithBirthday("client2@test.fr","clienttestPassword2","client","clienttestNom2","clienttestPrenom2","0600000002",Civilite.femme, LocalDate.parse("2000-01-02"));
		assertEquals("client2@test.fr", client2.getMail());
		assertEquals("clienttestPassword2", client2.getPassword());
		assertEquals("clienttestNom2", client2.getNom());
		assertEquals("clienttestPrenom2", client2.getPrenom());
		assertEquals("0600000002", client2.getTel());
		assertEquals(Civilite.femme, client2.getCivilite());
		assertEquals(LocalDate.parse("2000-01-02"), client2.getDateNaissance());
	}

	@Test
	void createLessBirthdayTest () {
		Client client2 = clientSrv.createLessBirthday("client2@test.fr","clienttestPassword2","client","clienttestNom2","clienttestPrenom2","0600000002",Civilite.femme);
		assertEquals("client2@test.fr", client2.getMail());
		assertEquals("clienttestPassword2", client2.getPassword());
		assertEquals("clienttestNom2", client2.getNom());
		assertEquals("clienttestPrenom2", client2.getPrenom());
		assertEquals("0600000002", client2.getTel());
		assertEquals(Civilite.femme, client2.getCivilite());
	}

	@Test
	void checkNotNullTest() {
		Client client3 = null;
		ClientException thrown = assertThrows(ClientException.class, () -> {
			clientSrv.checkNotNull(client3);
		});
		assertTrue(thrown.getMessage().contentEquals("compte client obligatoire"));
	}

	@Test
	void checkConstraintMailTest() {
		ClientException thrown1 = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client(null, "clienttestPassword2","client","clienttestNom2","clienttestPrenom2","0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown1.getMessage().contentEquals("mail obligatoire"));

		ClientException thrown2 = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client(" ", "clienttestPassword2","client","clienttestNom2","clienttestPrenom2","0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown2.getMessage().contentEquals("mail obligatoire"));
	}

	@Test
	void checkConstraintPasswordTest() {
		ClientException thrown = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", null,"client","clienttestNom2","clienttestPrenom2","0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown.getMessage().contentEquals("mot de passe obligatoire"));
		ClientException thrown2 = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", " ","client","clienttestNom2","clienttestPrenom2","0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown2.getMessage().contentEquals("mot de passe obligatoire"));
	}

	@Test
	void checkConstraintNomTest() {
		ClientException thrown = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", "clienttestPassword2","client",null,"clienttestPrenom2","0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown.getMessage().contentEquals("nom obligatoire"));
		ClientException thrown2 = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", "clienttestPassword2","client"," ","clienttestPrenom2","0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown2.getMessage().contentEquals("nom obligatoire"));
	}	

	@Test
	void checkConstraintPrenomTest() {
		ClientException thrown = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", "clienttestPassword2","client","clienttestPrenom2",null,"0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown.getMessage().contentEquals("prenom obligatoire"));
		ClientException thrown2 = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", "clienttestPassword2","client","clienttestNom2"," ","0600000002",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown2.getMessage().contentEquals("prenom obligatoire"));
	}	

	@Test
	void checkConstraintTelTest() {
		ClientException thrown = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", "clienttestPassword2","client","clienttestPrenom2","clienttestPrenom2",null,Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown.getMessage().contentEquals("téléphone obligatoire"));
		ClientException thrown2 = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", "clienttestPassword2","client","clienttestNom2","clienttestPrenom2"," ",Civilite.femme, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown2.getMessage().contentEquals("téléphone obligatoire"));
	}	

	@Test
	void checkConstraintCiviliteTest() {
		ClientException thrown = assertThrows(ClientException.class, () -> {
			clientSrv.save(new Client("client2@test.fr", "clienttestPassword2","client","clienttestPrenom2","clienttestPrenom2","0600000002",null, LocalDate.parse("2000-01-02")));
		});
		assertTrue(thrown.getMessage().contentEquals("civilite obligatoire"));
	}	
	
	@Test
	void findByIdThrowsTest() {
		IdException thrown1= assertThrows(IdException.class, () -> {
			clientSrv.findById(null);
		});
		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
	}

	@Test
	void deleteClientTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		clientSrv.delete(client1);		
		assertThrows(ClientException.class, () -> {
			clientSrv.findById(client1.getId());
		});	
	}

	@Test
	void deleteByIdTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);	
		clientSrv.delete(client1.getId());	
		assertThrows(ClientException.class, () -> {
			clientSrv.findById(client1.getId());
		});	
	}

	@Test
	void findByIdTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);	
		assertNotNull(clientSrv.findById(client1.getId()));
	}

	@Test 
	void findAllTest() {
		List<Client> clients = new ArrayList<>();	
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clients.add(client1);
		clientSrv.save(client1);
		//vérif renvoie bien une liste
		assertEquals(clients.getClass(),clientSrv.findAll().getClass());	
		//vérif la class des objets dans la liste
		assertEquals(client1.getClass(),clientSrv.findAll().get(0).getClass());		
	}

	@Test
	void updateMailTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		Client clientUpdate = new Client(client1.getId(),"client1Update@test.fr","client","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.update(clientUpdate);
		assertEquals("client1Update@test.fr",clientUpdate.getMail());
	}

	@Test
	void updatePasswordTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		Client clientUpdate = new Client(client1.getId(),"client1@test.fr","client","clienttestUpdate1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.update(clientUpdate);
		assertEquals("clienttestUpdate1",clientUpdate.getPassword());
	}
	
	@Test
	void updateCiviliteTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		Client clientUpdate = new Client(client1.getId(),"client1@test.fr","client","clienttest1","clienttest1","clienttest1","0600000001",Civilite.femme, LocalDate.parse("2000-01-01"));
		clientSrv.update(clientUpdate);
		assertEquals(Civilite.femme,clientUpdate.getCivilite());
	}	
	
	@Test
	void updateNomTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		Client clientUpdate = new Client(client1.getId(),"client1@test.fr","client","clienttest1","clienttestUpdate1","clienttest1","0600000001",Civilite.femme, LocalDate.parse("2000-01-01"));
		clientSrv.update(clientUpdate);
		assertEquals("clienttestUpdate1",clientUpdate.getNom());
	}
	
	@Test
	void updatePrenomTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		Client clientUpdate = new Client(client1.getId(),"client1@test.fr","client","clienttest1","clienttest1","clienttestUpdate1","0600000001",Civilite.femme, LocalDate.parse("2000-01-01"));
		clientSrv.update(clientUpdate);
		assertEquals("clienttestUpdate1",clientUpdate.getPrenom());
	}
	
	@Test
	void updateTelTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		Client clientUpdate = new Client(client1.getId(),"client1@test.fr","client","clienttest1","clienttest1","clienttest1","0600000002",Civilite.femme, LocalDate.parse("2000-01-01"));
		clientSrv.update(clientUpdate);
		assertEquals("0600000002",clientUpdate.getTel());
	}
	
	@Test
	void updateDateNaissanceTest() {
		Client client1 = new Client("client1@test.fr","clienttest1","client","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));
		clientSrv.save(client1);
		Client clientUpdate = new Client(client1.getId(),"client1@test.fr","client","clienttest1","clienttest1","clienttest1","0600000001",Civilite.femme, LocalDate.parse("2000-01-03"));
		clientSrv.update(clientUpdate);
		assertEquals(LocalDate.parse("2000-01-03"),clientUpdate.getDateNaissance());
	}
	
	
}
