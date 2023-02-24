package bar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bar.model.TableBar;

public interface ITableRepository extends JpaRepository<TableBar, Integer>{
	
	@Query("select t.id from TableBar t")
	List<Integer> findAllId();

}
