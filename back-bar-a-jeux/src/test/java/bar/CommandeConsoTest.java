package bar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import bar.model.Civilite;
import bar.model.Client;
import bar.model.CommandeConso;
import bar.model.Conso;
import bar.model.Reservation;
import bar.model.TableBar;
import bar.model.TypeConso;
import bar.service.ClientService;
import bar.service.CommandeConsoService;
import bar.service.ConsoService;
import bar.service.ReservationService;
import bar.service.TableService;

@Transactional
@Rollback
@SpringBootTest
class CommandeConsoTest {
	
	@Autowired
	CommandeConsoService CommandeConsoServ;
	
	@Autowired
	ConsoService consoServ;
	
	@Autowired
	ReservationService resaSrv;
	
	@Autowired
	ClientService clientSrv;
	
	@Autowired
	TableService tableSrv;
	
	@Test
	void createTest() {
		Conso conso1 = consoServ.create(10.0,2,"cocktail",TypeConso.boisson);
		List <Conso> consos =new ArrayList<>();
		consos.add(conso1);
		Client client1 = new Client("client1@test.fr","client1","client1","client1","0600000001",Civilite.homme);
		TableBar table1 = new TableBar(4,1);
		client1=clientSrv.save(client1);
		table1=tableSrv.create(table1);
		Reservation resa1 = new Reservation(LocalDate.parse("2024-02-22"),LocalTime.parse("10:00:00"),4,table1,client1);
		resa1 = resaSrv.create(resa1);
		CommandeConso cmdConso= new CommandeConso(consos,resa1);
		assertEquals(cmdConso.getId(), CommandeConsoServ.findById(cmdConso.getId()).getId());
		
	}

}