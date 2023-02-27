package bar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import bar.exception.IdException;
import bar.exception.ReservationException;
import bar.model.Civilite;
import bar.model.Client;
import bar.model.Jeu;
import bar.model.Reservation;
import bar.model.TableBar;
import bar.service.ClientService;
import bar.service.JeuService;
import bar.service.ReservationService;
import bar.service.TableService;

@SpringBootTest
@Transactional
@Rollback
class ReservationServiceTest {
	
	@Autowired
	ReservationService resaSrv;
	
	@Autowired
	ClientService clientSrv;
	
	@Autowired
	TableService tableSrv;
	
	@Autowired
	JeuService jeuSrv;
	
	@Test
	@Disabled
	void injectionReservationServiceTest() {
		assertNotNull(resaSrv);
	}
	
	@Test
	@Disabled
	void creationReservationTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		//System.out.println(resa1.toString());
		resa1 = resaSrv.create(resa1);
		assertEquals(resa1.getId(), resaSrv.findById(resa1.getId()).getId());
	}
	
	@Test
	@Disabled
	void creationReservationAvecJeuxTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		Jeu jeu1 = new Jeu("6 qui prend !",2,10,10,20,"Gigamic","2007",14.9,"\\Projet_Final\\bdd\\image_jeu\\6-qui-prend.png","logique,réflexes",6, "qui prend, la version française de 6 nimmt!");
		
		jeu1=jeuSrv.create(jeu1);
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1,jeu1);
		//System.out.println(resa1.toString());
		resa1 = resaSrv.create(resa1);
		assertEquals(resa1.getId(), resaSrv.findById(resa1.getId()).getId());
	}

	@Test
	@Disabled
	void deleteReservationTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		resaSrv.create(resa1);
		assertNotNull(resaSrv.findById(resa1.getId()));
		resaSrv.delete(resa1);
		IdException thrown1= assertThrows(IdException.class, () -> {
			resaSrv.findById(resa1.getId());
		});
		assertTrue(thrown1.getMessage().contentEquals("id introuvable"));
	}
	
	@Test
	@Disabled
	void deleteReservationIdTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		resaSrv.create(resa1);
		assertNotNull(resaSrv.findById(resa1.getId()));
		resaSrv.delete(resa1.getId());
		IdException thrown1= assertThrows(IdException.class, () -> {
			resaSrv.findById(resa1.getId());
		});
		//System.out.println(thrown1.getMessage());
		assertTrue(thrown1.getMessage().contentEquals("id introuvable"));
	}
	
	@Test
	@Disabled
	void updateReservationTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		Jeu jeu1 = new Jeu("6 qui prend !",2,10,10,20,"Gigamic","2007",14.9,"\\Projet_Final\\bdd\\image_jeu\\6-qui-prend.png","logique,réflexes",6, "qui prend, la version française de 6 nimmt!");
		
		jeu1=jeuSrv.create(jeu1);
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		Reservation resaCreate = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		resaCreate = resaSrv.create(resaCreate);
		Reservation resaUpdate = new Reservation(resaCreate.getId(),LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1,jeu1);
		resaUpdate = resaSrv.update(resaUpdate);
		assertEquals(resaUpdate.getJeu(), resaSrv.findById(resaUpdate.getId()).getJeu());
		
	}
	
	@Test
	@Disabled
	void disableDatesReservationTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		Reservation resa2 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("11:00:00"),4,table1,client1);
		Reservation resa3 = new Reservation(LocalDate.parse("2024-02-23"),LocalTime.parse("10:00:00"),4,table1,client1);
		
		
		resaSrv.create(resa1);
		resaSrv.create(resa2);
		resaSrv.create(resa3);
		//System.out.println(datesDisable);
		assertEquals(1, resaSrv.findAllDisableDate(4).size());
	}
	
	@Test
	@Disabled
	void disableHeuresReservationTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		TableBar table2 = new TableBar(4,1);
		
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		table2=tableSrv.create(table2);
		
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		Reservation resa2 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("11:00:00"),4,table1,client1);
		Reservation resa3 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table2,client1);
		
		
		resaSrv.create(resa1);
		resaSrv.create(resa2);
		resaSrv.create(resa3);
		//System.out.println(heuresDisable);
		assertEquals(1, resaSrv.findAllDisableHeureparDate(4,LocalDate.parse("2024-02-22")).size());
	}
	
	@Test
	@Disabled
	void findByIdThrowsTest() {
		IdException thrown1= assertThrows(IdException.class, () -> {
			resaSrv.findById(null);
		});
		//System.out.println(thrown1.getMessage());
		assertTrue(thrown1.getMessage().contentEquals("id obligatoire"));
	}

	@Test
	@Disabled
	void checkConstraintThrowsTest() {
		
//		ReservationException thrown1= assertThrows(ReservationException.class, () -> {
//			Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
//			client1=clientSrv.save(client1);
//			resaSrv.create(new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,null,client1));
//		});
//		ReservationException thrown2= assertThrows(ReservationException.class, () -> {
//			TableBar table1 = new TableBar(4,1);
//			table1=tableSrv.create(table1);
//			resaSrv.create(new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,null));
//		});
		ReservationException thrown3= assertThrows(ReservationException.class, () -> {
			Client client2 = new Client("client2@test.fr","client2","client2","client2","0600000002",Civilite.homme);
			client2=clientSrv.save(client2);
			TableBar table1 = new TableBar(4,1);
			table1=tableSrv.create(table1);
			resaSrv.create(new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),0,table1,client2));
		});
		ReservationException thrown4= assertThrows(ReservationException.class, () -> {
			Client client3 = new Client("client3@test.fr","client3","client3","client3","0600000003",Civilite.homme);
			client3=clientSrv.save(client3);
			TableBar table1 = new TableBar(4,1);
			table1=tableSrv.create(table1);
			resaSrv.create(new Reservation(LocalDate.parse("2024-02-22"),null,4,table1,client3));
		});
		ReservationException thrown5= assertThrows(ReservationException.class, () -> {
			Client client4 = new Client("client4@test.fr","client4","client4","client4","0600000004",Civilite.homme);
			client4=clientSrv.save(client4);
			TableBar table1 = new TableBar(4,1);
			table1=tableSrv.create(table1);
			resaSrv.create(new Reservation(null,LocalTime.parse("10:00:00"),4,table1,client4));
		});
		
//		assertTrue(thrown1.getMessage().contentEquals("table obligatoire"));
//		assertTrue(thrown2.getMessage().contentEquals("client obligatoire"));
		assertTrue(thrown3.getMessage().contentEquals("personne obligatoire"));
		assertTrue(thrown4.getMessage().contentEquals("heure obligatoire"));
		assertTrue(thrown5.getMessage().contentEquals("date obligatoire"));
				
	}
	
	@Test
	@Disabled
	void checkNotNullThrowsTest() {
		ReservationException thrown1= assertThrows(ReservationException.class, () -> {
			resaSrv.create(null);
		});
		assertTrue(thrown1.getMessage().contentEquals("réservation obligatoire"));
	}
	
	@Test
	@Disabled
	void findAllByClientId() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		client1=clientSrv.save(client1);
		TableBar table1 = new TableBar(4,1);
		table1=tableSrv.create(table1);
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1));
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-23"),LocalTime.parse("10:00:00"),4,table1,client1));
		
		//System.out.println(resaSrv.findAllbyIdClient(client1.getId()));
		assertEquals(2, resaSrv.findAllByClientId(client1.getId()).size());

	}
	
	@Test
	@Disabled
	void findAll() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		client1=clientSrv.save(client1);
		TableBar table1 = new TableBar(4,1);
		table1=tableSrv.create(table1);
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1));
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-23"),LocalTime.parse("10:00:00"),4,table1,client1));
		
		//System.out.println(resaSrv.findAllbyIdClient(client1.getId()));
		assertEquals(2, resaSrv.findAll().size());

	}
	
	@Test
	
	void findAllByDateRes() {
		Client client1 = new Client("client10@test.fr","client10","client10","client1","0600000001",Civilite.homme);
		client1=clientSrv.save(client1);
		Client client2 = new Client("client20@test.fr","client20","client20","client2","0600000002",Civilite.homme);
		client2=clientSrv.save(client2);
		TableBar table1 = new TableBar(4,1);
		table1=tableSrv.create(table1);
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1));
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-23"),LocalTime.parse("10:00:00"),4,table1,client1));
		resaSrv.create(new Reservation(LocalDate.parse("2021-01-23"),LocalTime.parse("10:00:00"),4,table1,client1));
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-24"),LocalTime.parse("10:00:00"),4,table1,client2));
		resaSrv.create(new Reservation(LocalDate.parse("2024-02-25"),LocalTime.parse("10:00:00"),4,table1,client2));
		
		List<Reservation> dateAfter=resaSrv.findAllByAfterDateRes(client1.getId());
		List<Reservation> dateBefore=resaSrv.findAllByBeforeDateRes(client1.getId());
		System.out.println(client1.getId());
		System.out.println(client2.getId());
		System.out.println(dateAfter.get(0).getDateRes());
		System.out.println(dateAfter.get(1).getDateRes());
		System.out.println(dateBefore.get(0).getDateRes());
		assertEquals(2, dateAfter.size());
		assertEquals(1, dateBefore.size());

	}
	
	@Test
	@Disabled
	void disableIdTableTest() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		TableBar table2 = new TableBar(4,1);
		
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		table2=tableSrv.create(table2);
		
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		Reservation resa2 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("11:00:00"),4,table1,client1);
		Reservation resa3 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table2,client1);
		
		resaSrv.create(resa1);
		resaSrv.create(resa2);
		resaSrv.create(resa3);
		//System.out.println(heuresDisable);
		List<Integer> idDate1=resaSrv.findAllIdByDateResandHeureRes(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"));
		List<Integer> idDate2=resaSrv.findAllIdByDateResandHeureRes(LocalDate.parse("2024-02-22"),LocalTime.parse("11:00:00"));
		assertEquals(2, idDate1.size());
		assertEquals(1, idDate2.size());
	}
	
	@Test
	@Disabled
	void removeDisableHeure() {
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		Reservation resa2 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("11:00:00"),4,table1,client1);
		Reservation resa3 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		Reservation resa4 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("11:00:00"),4,table1,client1);
		
		resaSrv.create(resa1);
		resaSrv.create(resa2);
		resaSrv.create(resa3);
		resaSrv.create(resa4);
		
		List<LocalTime> heuresEnable = resaSrv.AllEnableHeures();
		List<LocalTime> heuresDisable = resaSrv.findAllDisableHeureparDate(4,LocalDate.parse("2024-02-22"));

		System.out.println("1er print");
		
		heuresDisable.forEach(System.out::println);
		
		System.out.println("2eme print");
		
		heuresEnable.removeIf(x -> heuresDisable.contains(x));
		
		heuresEnable.forEach(System.out::println);
		
		
	}
	
	
}
