package bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bar.model.CommandeJeu;

public interface ICommandeJeuRepository extends JpaRepository <CommandeJeu,Integer>{
	
	@Query("select c from CommandeJeu c left join fetch c.achatJeux where c.client.id=:id ")
	List<CommandeJeu> findAllByClientIdWithAchatJeu(@Param("id") Integer id);
}
