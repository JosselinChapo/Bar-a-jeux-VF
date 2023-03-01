package bar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer>{

	public Optional<Admin> findByMailAndPassword(String mail, String password);
	
}
