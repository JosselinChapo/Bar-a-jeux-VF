package bar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.Client;

public interface IClientRepository extends JpaRepository<Client, Integer>{

	
	public Optional<Client> findByMailAndPassword(String mail, String password);
	
//	@Query("select distinct c from Client c left join fetch c.reservations where c.id=:id")
//	Optional<Client> findByIdWithReservation(@Param("id") Integer id);
//	
//	@Query("select distinct c from Client c left join fetch c.commandeJeux where c.id=:id")
//	Optional<Client> findByIdWithCommandesJeux(@Param("id") Integer id);
	
	
//	@Query("select distinct f from Filiere f left join fetch f.matieres left join fetch f.stagiaires where f.id=:id")
//	Optional<Filiere> findByIdWithMatieresAndStagiaire(@Param("id") Integer id);
//
//	@Query("select distinct f from Filiere f left join fetch f.referent left join fetch f.matieres where f.id=:id")
//	Optional<Filiere> findByIdWithReferentAndMatieres(@Param("id") Integer id);
	
}
