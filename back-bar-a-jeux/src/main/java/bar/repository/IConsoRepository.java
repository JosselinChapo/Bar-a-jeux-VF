package bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bar.model.Conso;

public interface IConsoRepository extends JpaRepository<Conso, Integer> {
	
	@Query("select c from Conso c WHERE c.typeconso='boisson'")
	List<Conso> findAllByTypeconsoBoisson();
	
	@Query("select c from Conso c WHERE c.typeconso='plat'")
	List<Conso> findAllByTypeconsoPlat();

}
