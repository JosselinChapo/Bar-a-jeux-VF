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

import bar.exception.IdException;
import bar.exception.TableException;
import bar.model.TableBar;
import bar.service.TableService;

@SpringBootTest
@Transactional
@Rollback
class TableServiceTest {
	
	@Autowired
	TableService tableSrv;
	
	@Test
	void injectionTableServiceTest() {
		assertNotNull(tableSrv);
	}
	
	@Test
	void creationTableTest() {
		TableBar table1 = new TableBar(4,1);
		table1=tableSrv.create(table1);
		assertEquals(table1.getId(), tableSrv.findById(table1.getId()).getId());
	}

	@Test
	void deleteTableTest() {
		TableBar table1 = new TableBar(4,1);
		tableSrv.create(table1);
		assertNotNull(tableSrv.findById(table1.getId()));
		tableSrv.delete(table1);
		IdException thrown1= assertThrows(IdException.class, () -> {
			tableSrv.findById(table1.getId());
		});
		assertTrue(thrown1.getMessage().contentEquals("id introuvable"));
	}
	
	@Test
	void deleteTableIdTest() {
		TableBar table1 = new TableBar(4,1);
		tableSrv.create(table1);
		tableSrv.delete(table1.getId());
		IdException thrown1= assertThrows(IdException.class, () -> {
			tableSrv.findById(table1.getId());
		});
		assertTrue(thrown1.getMessage().contentEquals("id introuvable"));
	}
	
	@Test
	void updateTableTest() {
		TableBar tableCreate = new TableBar(4,1);
		tableCreate=tableSrv.create(tableCreate);
		TableBar tableUpdate = new TableBar(tableCreate.getId(),6,1);
		tableUpdate=tableSrv.update(tableUpdate);
		assertEquals(tableUpdate.getNbPersonne(), tableSrv.findById(tableUpdate.getId()).getNbPersonne());
	}
	
	@Test
	void testFindAll() {
		assertTrue(tableSrv.findAll().isEmpty());
		TableBar tableCreate = new TableBar(4,1);
		tableCreate=tableSrv.create(tableCreate);
		assertEquals(1, tableSrv.findAll().size());
	}
	
	@Test
	void findByIdThrowsTest() {
		IdException thrown1= assertThrows(IdException.class, () -> {
			tableSrv.findById(null);
		});
		assertTrue(thrown1.getMessage().contentEquals("id obligatoire"));
	}

	@Test
	void checkConstraintThrowsTest() {
		TableException thrown1= assertThrows(TableException.class, () -> {
			tableSrv.create(new TableBar(0,1));
		});
		TableException thrown2= assertThrows(TableException.class, () -> {
			tableSrv.create(new TableBar(4,0));
		});
		
		assertTrue(thrown1.getMessage().contentEquals("capacité de la table obligatoire"));
		assertTrue(thrown2.getMessage().contentEquals("id de la table obligatoire"));
	}
	
	@Test
	void checkNotNullThrowsTest() {
		TableException thrown1= assertThrows(TableException.class, () -> {
			tableSrv.create(null);
		});
		assertTrue(thrown1.getMessage().contentEquals("table obligatoire"));
	}
}
