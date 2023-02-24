package bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Integer>{

}
