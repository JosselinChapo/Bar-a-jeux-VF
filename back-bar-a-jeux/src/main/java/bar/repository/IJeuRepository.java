package bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bar.model.Jeu;

public interface IJeuRepository extends JpaRepository<Jeu, Integer>{
	
	
	//On récupère tout les type de jeu pour le select dans la page collection/boutique
	@Query("select DISTINCT j.typeJeu from Jeu j")
	List<String> findAllTypeJeu();
	
	@Query("select typeJeu from Jeu where typeJeu LIKE '%:param%'")
	List<Jeu> findByTypeJeu(@Param("param") String typeJeu);
}


//   "select DISTINCT j.typeJeu from Jeu j where typeJeu = "%parametre%""