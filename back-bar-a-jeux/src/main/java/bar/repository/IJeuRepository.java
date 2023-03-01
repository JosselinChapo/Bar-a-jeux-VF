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
	
	@Query("select j from Jeu j where j.typeJeu LIKE %:param%")
	List<Jeu> findByTypeJeu(@Param("param") String typeJeu);
	
	@Query("select j from Jeu j where j.nbJoueurMax>=:paramNbj and j.nbJoueurMin<=:paramNbj and j.typeJeu LIKE %:paramtp%")
	List<Jeu> findByTypeJeuNbj(@Param("paramNbj") Integer nombreJoueur,@Param("paramtp") String typeJeu);
	
	@Query("select j from Jeu j where j.nbJoueurMax>=:paramNbj and j.nbJoueurMin<=:paramNbj and j.typeJeu LIKE %:paramtp% and j.duree>=:dureeMin and j.duree<=:dureeMax and j.prix>=:prixMin and j.prix<=:prixMax")
	List<Jeu> findByFilter(@Param("paramNbj") Integer nombreJoueur,@Param("paramtp") String typeJeu,@Param("dureeMin") Integer dureeMin,@Param("dureeMax") Integer dureeMax,@Param("prixMin") double prixMin,@Param("prixMax") double prixMax );
	
	@Query("select j from Jeu j where j.duree>=:dureeMin and j.duree<=:dureeMax and j.typeJeu LIKE %:paramtp%")
	List<Jeu> findByTypeJeuDuree(@Param("dureeMin") Integer dureeMin,@Param("dureeMax") Integer dureeMax,@Param("paramtp") String typeJeu);
}


//   "select DISTINCT j.typeJeu from Jeu j where typeJeu = "%parametre%""