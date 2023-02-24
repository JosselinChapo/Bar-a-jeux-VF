package bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.model.CommandeJeu;

public interface ICommandeJeuRepository extends JpaRepository <CommandeJeu,Integer>{

}
