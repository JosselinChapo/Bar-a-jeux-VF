package bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.Evenement;

public interface IEvenementRepository extends JpaRepository<Evenement, Integer>{

}
