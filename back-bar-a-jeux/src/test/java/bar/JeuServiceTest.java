package bar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import bar.exception.IdException;
import bar.exception.JeuException;
import bar.model.Jeu;
import bar.repository.IJeuRepository;
import bar.service.JeuService;

@SpringBootTest
@Transactional
@Rollback
public class JeuServiceTest {
	
	@Autowired
	JeuService jeuSrv;
	@Autowired
	IJeuRepository jeuRepo;
	
//	@Test
//	void injectionJeuServiceTest() {
//		assertNotNull(jeuSrv);
//	}
//	
//	@Test
//	void creationJeu() {
//	Jeu jeuTest = new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//	jeuSrv.create(jeuTest);
//	assertNotNull(jeuSrv.findById(jeuTest.getId()));
//	} 
//	
//	@Test
//	void checkConstraintThrowsTest() {
//		
//		JeuException thrown1 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu(null,2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown2 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",-1,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown3 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,0,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown4 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,-2,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown5 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,-15,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown6 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,null,"2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown7 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production",null,22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown8 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",-5,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown9 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,null,"action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown10 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png",null,1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown11 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",-5,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown12 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.,De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles."));
//		});
//		JeuException thrown13 = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,null));
//		});
//		
//		assertTrue(thrown1.getMessage().contentEquals("nom obligatoire"));
//		assertTrue(thrown2.getMessage().contentEquals("saisissez un nombre de joueur minimum supérieur à zéro"));
//		assertTrue(thrown3.getMessage().contentEquals("saisissez un nombre de joueur maximum supérieur à zéro"));
//		assertTrue(thrown4.getMessage().contentEquals("saisissez un age minimum supérieur à zéro"));
//		assertTrue(thrown5.getMessage().contentEquals("saisissez une durée supérieur à zéro"));
//		assertTrue(thrown6.getMessage().contentEquals("editeur obligatoire"));
//		assertTrue(thrown7.getMessage().contentEquals("annee obligatoire"));
//		assertTrue(thrown8.getMessage().contentEquals("prix doit être supérieur à zéro"));
//		assertTrue(thrown9.getMessage().contentEquals("chemin d'image obligatoire"));
//		assertTrue(thrown10.getMessage().contentEquals("typeJeu obligatoire"));
//		assertTrue(thrown11.getMessage().contentEquals("saisissez un stock positif"));
//		assertTrue(thrown12.getMessage().contentEquals("description trop grande maximum 2000 caractères"));
//		assertTrue(thrown13.getMessage().contentEquals("description obligatoire"));
//	}
//	
//	@Test
//	void findByIdThrowsTest() {
//		IdException thrown1 = assertThrows(IdException.class, () -> {
//			jeuSrv.findById(100);
//		});
//		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
//	}
//	
//	@Test
//	void findByIdThrowsNullTest() {
//		IdException thrown1 = assertThrows(IdException.class, () -> {
//			jeuSrv.findById(null);
//		});
//		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
//	}
//	
//	@Test
//	void findByIdTest() {
//		Jeu jeuTest = new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//		jeuSrv.create(jeuTest);
//		assertNotNull(jeuSrv.findById(jeuTest.getId()));
//	}
//	
//	@Test
//	void updateNomTest() {
//		Jeu jeuTest = new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//		jeuTest = jeuSrv.create(jeuTest);
//		Jeu jeuUpdate = new Jeu(jeuTest.getId(),"7 Wonders Duelité",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//		jeuUpdate = jeuSrv.update(jeuUpdate);
//		assertEquals("7 Wonders Duelité",jeuUpdate.getNom());
//	}
//	
//	@Test
//	void updateNbJMinTest() {
//		Jeu jeuTest = new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//		jeuTest = jeuSrv.create(jeuTest);
//		Jeu jeuUpdate = new Jeu(jeuTest.getId(),"7 Wonders Duel",3,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//		jeuUpdate = jeuSrv.update(jeuUpdate);
//		assertEquals(3,jeuUpdate.getNbJoueurMin());
//	}
//	
//	@Test
//	void checkNotNullTest() {
//		Jeu jeuTest = null;
//		JeuException thrown = assertThrows(JeuException.class, () -> {
//			jeuSrv.create(jeuTest);
//		});
//		assertTrue(thrown.getMessage().contentEquals("jeu obligatoire"));
//	}
//	
//	@Test
//	void deleteByIdTest() {
//		Jeu jeuTest = new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//		jeuSrv.create(jeuTest);
//		jeuSrv.delete(jeuTest.getId());	
//		IdException thrown1 = assertThrows(IdException.class, () -> {
//			jeuSrv.findById(jeuTest.getId());
//		});	
//		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
//	}
//	
//	@Test
//	void deleteByObjectTest() {
//		Jeu jeuTest = new Jeu("7 Wonders Duel",2,2,10,30,"Repos Production","2015",22.95,"\\Projet_Final\\bdd\\image_jeu\\concept.png","action,carte",1,"De bien des façons, 7 Wonders Duel ressemble à son grand-frère 7 Wonders car les joueurs acquièrent des cartes au cours de trois âges, ces dernières fournissant des ressources ou faisant progresser leur développement militaire ou scientifique afin de développer leur civilisation ou bâtir des merveilles.");
//		jeuSrv.create(jeuTest);
//		jeuSrv.delete(jeuTest);	
//		IdException thrown1 = assertThrows(IdException.class, () -> {
//			jeuSrv.findById(jeuTest.getId());
//		});	
//		assertTrue(thrown1.getMessage().contentEquals("id inconnu"));
//	}
	
	@Test
	void filtreJeu() {
		List<Jeu> testJeu = jeuSrv.findByFilter(3,"",15,30);
		System.out.println(testJeu);
		for(Jeu j : testJeu) {
		System.out.println(j.getNom());
		}
	}
	
	@Test
	void filtreJeuNbjTypeJeu() {
		List<Jeu> testJeu = jeuSrv.findByTypeJeuNbj(3,"logique");
		System.out.println(testJeu);
		for(Jeu j : testJeu) {
		System.out.println(j.getNom());
		}
	}
	
	@Test
	void filtreJeuDureeTypeJeu() {
		List<Jeu> testJeu = jeuSrv.findByTypeJeuDuree(15,30,"logique");
		System.out.println(testJeu);
		for(Jeu j : testJeu) {
		System.out.println(j.getNom());
		}
	}
	
}
