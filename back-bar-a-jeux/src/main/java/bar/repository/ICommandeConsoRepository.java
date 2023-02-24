package bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.CommandeConso;

public interface ICommandeConsoRepository extends JpaRepository<CommandeConso, Integer> {

}
