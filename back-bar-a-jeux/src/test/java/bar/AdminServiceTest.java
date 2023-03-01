package bar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import bar.exception.AdminException;
import bar.exception.IdException;
import bar.model.Admin;
import bar.service.AdminService;

@SpringBootTest
@Transactional
@Rollback
public class AdminServiceTest {

	@Autowired 
	AdminService adminSrv;


	@Test
	void injectionAdminServiceTest() {
		assertNotNull(adminSrv);
	}

	@Test
	void saveAdminTest() {
		Admin admin2 = new Admin("admin2@test.fr","admin2","admin");
		adminSrv.save(admin2);
		assertNotNull(adminSrv.findById(admin2.getId()));
	}

	@Test
	void creationAdminTest() {
		Admin admin3 = adminSrv.create("admin3@test.fr","admin3","admin");
		assertEquals("admin3@test.fr", admin3.getMail());
		assertEquals("admin3", admin3.getPassword());
	}

	@Test
	void checkNotNullTest() {
		Admin admin4 = null;
		AdminException thrown = assertThrows(AdminException.class, () -> {
			adminSrv.checkNotNull(admin4);
		});
		assertTrue(thrown.getMessage().contentEquals("compte admin obligatoire"));
	}

	@Test
	void checkConstraintMailTest() {
		AdminException thrown1 = assertThrows(AdminException.class, () -> {
			adminSrv.save(new Admin(null, "admin5","admin"));
		});
		assertTrue(thrown1.getMessage().contentEquals("mail obligatoire"));
		AdminException thrown2 = assertThrows(AdminException.class, () -> {
			adminSrv.save(new Admin(" ", "admin5","admin"));
		});
		assertTrue(thrown2.getMessage().contentEquals("mail obligatoire"));
	}

	@Test
	void checkConstraintPasswordTest() {
		AdminException thrown = assertThrows(AdminException.class, () -> {
			adminSrv.save(new Admin("admin6@test.fr", null,"admin"));
		});
		assertTrue(thrown.getMessage().contentEquals("mot de passe obligatoire"));
		AdminException thrown2 = assertThrows(AdminException.class, () -> {
			adminSrv.save(new Admin("admin6@test.fr", " ","admin"));
		});
		assertTrue(thrown2.getMessage().contentEquals("mot de passe obligatoire"));
	}

	@Test
	void findByIdThrowsTest() {
		IdException thrown1= assertThrows(IdException.class, () -> {
			adminSrv.findById(null);
		});
		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
	}
	
	@Test
	void deleteAdminTest() {
		Admin admin7 = new Admin("admin7@test.fr","admin7","admin");
		adminSrv.save(admin7);
		adminSrv.delete(admin7);	
		assertThrows(AdminException.class, () -> {
			adminSrv.findById(admin7.getId());
		});	
	}

	@Test
	void deleteByIdTest() {
		Admin admin8 = new Admin("admin8@test.fr","admin8","admin");
		adminSrv.save(admin8);
		adminSrv.delete(admin8.getId());	
		assertThrows(AdminException.class, () -> {
			adminSrv.findById(admin8.getId());
		});	
	}

	@Test
	void findByIdTest() {
		Admin admin9 = new Admin("admin9@test.fr","admin9","admin");
		adminSrv.save(admin9);
		assertNotNull(adminSrv.findById(admin9.getId()));
	}

	@Test 
	void findAllTest() {
		List<Admin> admins = new ArrayList<>();	
		Admin admin0 = new Admin("admin0@test.fr","admin0","admin");
		admins.add(admin0);
		adminSrv.save(admin0);
		//vérif renvoie bien une liste
		assertEquals(admins.getClass(),adminSrv.findAll().getClass());	
		//vérif la class des objets dans la liste
		assertEquals(admin0.getClass(),adminSrv.findAll().get(0).getClass());		
	}

	@Test
	void updateMailTest() {
		Admin admin10 = new Admin("admin10@test.fr","admin10","admin");
		adminSrv.save(admin10);
		Admin adminUpdate = new Admin(admin10.getId(),"adminUpdate@test.fr","admin10","admin");
		adminSrv.update(adminUpdate);
		assertEquals("adminUpdate@test.fr",adminUpdate.getMail());
	}

	@Test
	void updatePasswordTest() {
		Admin admin11 = new Admin("admin11@test.fr","admin11","admin");
		adminSrv.save(admin11);
		Admin adminUpdate = new Admin(admin11.getId(),"admin11@test.fr","adminUpdate","admin");
		adminSrv.update(adminUpdate);
		assertEquals("adminUpdate",adminUpdate.getPassword());
	}
	
	

}
