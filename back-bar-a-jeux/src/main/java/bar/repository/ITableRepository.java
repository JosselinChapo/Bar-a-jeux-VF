package bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bar.model.TableBar;

public interface ITableRepository extends JpaRepository<TableBar, Integer>{
	
	@Query("select t.id from TableBar t")
	List<Integer> findAllId();
	
	@Query("select t.idTable from TableBar t WHERE t.nbPersonne=:nbPersonne")
	List<Integer> findAllIdTablebyNbPersonne(@Param("nbPersonne") int nbPersonne);

}
