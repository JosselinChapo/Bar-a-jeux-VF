package bar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import bar.exception.ConsoException;
import bar.model.Conso;
import bar.model.TypeConso;
import bar.service.ConsoService;

@SpringBootTest
@Transactional
@Rollback

public class ConsoServiceTest {
	
	@Autowired
	ConsoService consoServ;
	
	@Test
	void createTest() {
		
		Conso conso = consoServ.create("cocktail",10.0,TypeConso.boisson);
		assertEquals(10.0,conso.getPrix());
		assertEquals("cocktail",conso.getNom());
		assertEquals(TypeConso.boisson,conso.getTypeconso());
		
	}

	@Test
	void CheckConstraintTestNom() {
		ConsoException thrown = assertThrows(ConsoException.class, () -> {
			consoServ.save(new Conso(" ",10.0,TypeConso.boisson));
		});
		assertTrue(thrown.getMessage().contentEquals("nom obligatoire"));
	}
	
	@Test
	void CheckConstraintPrix() {
		ConsoException thrown = assertThrows(ConsoException.class, () -> {
			consoServ.save(new Conso("cocktail",(-1),TypeConso.boisson));
		});
		assertTrue(thrown.getMessage().contentEquals("prix supérieur à 0"));
	}
	
	@Test
	void CheckConstraintTestTypeConso() {
		ConsoException thrown = assertThrows(ConsoException.class, () -> {
			consoServ.save(new Conso("cocktail",10.0,null));
		});
		assertTrue(thrown.getMessage().contentEquals("Le choix de la conso est obligatoire"));
	}
	
	@Test
	void deleteTest() {
	Conso conso3 = consoServ.create("cocktail",10.0,TypeConso.boisson);
			consoServ.save(conso3);
			consoServ.delete(conso3.getId());
			assertThrows(ConsoException.class, () -> {
				consoServ.findById(conso3.getId());
			});
			
	}
	
	
	@Test
	 void deleteById() {
		 Conso conso2 = consoServ.create("cocktail",10.0,TypeConso.boisson);
			consoServ.delete(conso2.getId());
			assertThrows(ConsoException.class, () -> {
				consoServ.findById(conso2.getId());
			});
	}
	
	@Test
		void findById() {
		Conso conso2 = consoServ.create("cocktail",10.0,TypeConso.boisson);
			assertNotNull(consoServ.findById(conso2.getId()));


	}
		
		 void findAll(){
				Conso conso2 = consoServ.create("cocktail",10.0,TypeConso.boisson);
				assertEquals(1, consoServ.findAll().size());
			
		}
		
		
		void update(Conso conso) {
			
			
			
}
	}

	
