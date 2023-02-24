package bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.Conso;

public interface IConsoRepository extends JpaRepository<Conso, Integer> {

}
