package bar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.Conso;
import bar.model.TypeConso;

public interface IConsoRepository extends JpaRepository<Conso, Integer> {
	
	List<Conso> findByTypeconso(TypeConso typeconso);

}
