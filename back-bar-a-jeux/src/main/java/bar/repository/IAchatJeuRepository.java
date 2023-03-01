package bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bar.model.AchatJeu;
import bar.model.CommandeJeu;


public interface IAchatJeuRepository extends JpaRepository<AchatJeu, Integer>{

	@Query("select a.id from AchatJeu a  where a.commandeJeu.id=:id")
	List<Integer> findAllAchatJeuIdByCommandeJeu(@Param("id") Integer id);
}
