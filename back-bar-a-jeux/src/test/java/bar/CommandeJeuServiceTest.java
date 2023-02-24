package bar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import bar.exception.CommandeJeuException;
import bar.exception.IdException;
import bar.model.Civilite;
import bar.model.Client;
import bar.model.CommandeJeu;
import bar.model.Statut;
import bar.service.AchatJeuService;
import bar.service.ClientService;
import bar.service.CommandeJeuService;
import bar.service.JeuService;

@SpringBootTest
@Transactional
@Rollback
public class CommandeJeuServiceTest {
	
	@Autowired
	AchatJeuService achatJeuSrv;
	
	@Autowired
	JeuService jeuSrv;
	
	@Autowired
	ClientService clientSrv;
	
	@Autowired
	CommandeJeuService cmdJeuSrv;
	

	@Test
	void injectionCommandeServiceTest() {
		assertNotNull(cmdJeuSrv);
	}
	
	
	
	@Test
	void creationCommande() {
	Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
	clientSrv.save(clientTest);
	CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,clientTest);
	cmdJeuSrv.create(cmdTest);
	assertNotNull(cmdJeuSrv.findById(cmdTest.getId()));
	}

	
	@Test
	void checkConstraintThrowsTest() {
		
		CommandeJeuException thrown1 = assertThrows(CommandeJeuException.class, () -> {
			Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
			clientSrv.save(clientTest);
			CommandeJeu cmdTest = new CommandeJeu(null,clientTest);
			cmdJeuSrv.create(cmdTest);
		});
		CommandeJeuException thrown2 = assertThrows(CommandeJeuException.class, () -> {
			CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,null);
			cmdJeuSrv.create(cmdTest);
		});		
		assertTrue(thrown1.getMessage().contentEquals("statut obligatoire"));
		assertTrue(thrown2.getMessage().contentEquals("client obligatoire"));		
	}

	
	
	@Test
	void findByIdThrowsTest() {
		IdException thrown1 = assertThrows(IdException.class, () -> {
			cmdJeuSrv.findById(100);
		});
		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
	}
	
	@Test
	void findByIdThrowsNullTest() {
		IdException thrown1 = assertThrows(IdException.class, () -> {
			cmdJeuSrv.findById(null);
		});
		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
	}


	@Test
	void findByIdTest() {
		Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
		clientSrv.save(clientTest);
		CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,clientTest);
		cmdJeuSrv.create(cmdTest);
		assertNotNull(cmdJeuSrv.findById(cmdTest.getId()));
	}

	@Test
	void updateStatut() {
		
		Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
		clientSrv.save(clientTest);
		CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,clientTest);
		cmdJeuSrv.create(cmdTest);
		
		
		CommandeJeu commandeUpdate = new CommandeJeu(cmdTest.getId(),Statut.Validee,clientTest);
		commandeUpdate = cmdJeuSrv.update(commandeUpdate);
		assertEquals(Statut.Validee.toString(),commandeUpdate.getStatut().toString());
	}
		
	
	@Test
	void updateClientTest() {
		
		Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
		clientSrv.save(clientTest);
		CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,clientTest);
		cmdJeuSrv.create(cmdTest);
		Client clientTest2 = new Client("client2@test.fr","clienttest2","clienttest2","clienttest2","0600000002",Civilite.homme, LocalDate.parse("2000-02-01"));	
		clientSrv.save(clientTest2);
		CommandeJeu cmdJeuUpdate = new CommandeJeu(cmdTest.getId(),Statut.EnCours,clientTest2);
		cmdJeuUpdate = cmdJeuSrv.update(cmdJeuUpdate);
		assertEquals(clientTest2,cmdJeuUpdate.getClient());
	}
	
	@Test
	void checkNotNullTest() {
		CommandeJeu commandeJeuTest = null;
		CommandeJeuException thrown = assertThrows(CommandeJeuException.class, () -> {
			cmdJeuSrv.create(commandeJeuTest);
		});
		assertTrue(thrown.getMessage().contentEquals("commandeJeu obligatoire"));
	}
	
	@Test
	void deleteByIdTest() {
		
		Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
		clientSrv.save(clientTest);
		CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,clientTest);
		cmdJeuSrv.create(cmdTest);
		
		cmdJeuSrv.delete(cmdTest.getId());	
		IdException thrown1 = assertThrows(IdException.class, () -> {
			cmdJeuSrv.findById(cmdTest.getId());
		});	
		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
	}

	@Test
	void deleteByObjectTest() {
		
		Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
		clientSrv.save(clientTest);
		CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,clientTest);
		cmdJeuSrv.create(cmdTest);
	
		cmdJeuSrv.delete(cmdTest);	
		IdException thrown1 = assertThrows(IdException.class, () -> {
			cmdJeuSrv.findById(cmdTest.getId());
		});	
		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
	}


	
	@Test 
	void findAllTest() {
		List<CommandeJeu> commandeJeux = new ArrayList<>();	
		Client clientTest = new Client("client1@test.fr","clienttest1","clienttest1","clienttest1","0600000001",Civilite.homme, LocalDate.parse("2000-01-01"));	
		clientSrv.save(clientTest);
		CommandeJeu cmdTest = new CommandeJeu(Statut.EnCours,clientTest);
		commandeJeux.add(cmdTest);
		cmdJeuSrv.create(cmdTest);
		
		//vérif renvoie bien une liste
		assertEquals(commandeJeux.getClass(),cmdJeuSrv.findAll().getClass());	
		//vérif la class des objets dans la liste
		assertEquals(cmdTest.getClass(),cmdJeuSrv.findAll().get(0).getClass());		
	}
	
	
}
