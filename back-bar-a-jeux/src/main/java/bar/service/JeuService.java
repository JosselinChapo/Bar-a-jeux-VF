package bar.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.exception.IdException;
import bar.exception.JeuException;
import bar.model.Jeu;
import bar.repository.IJeuRepository;

@Service
public class JeuService {
	
	@Autowired
	private IJeuRepository jeuRepo;
	
	// creation jeu
		public Jeu create(Jeu jeu) {
			checkNotNull(jeu);
			checkConstraint(jeu);
			return jeuRepo.save(jeu);
		}
		
		private void checkNotNull(Jeu jeu) {
			if (jeu == null) {
				throw new JeuException("jeu obligatoire");
			}
		}
		
		private void checkConstraint(Jeu jeu) {
			if (jeu.getNom() == null || jeu.getNom().isBlank()) {
				throw new JeuException("nom obligatoire");
			}
			if (jeu.getNbJoueurMin() <= 0) {
				throw new JeuException("saisissez un nombre de joueur minimum supérieur à zéro");
			}
			if (jeu.getNbJoueurMax() <= 0) {
				throw new JeuException("saisissez un nombre de joueur maximum supérieur à zéro");
			}
			if (jeu.getAgeMin() <= 0) {
				throw new JeuException("saisissez un age minimum supérieur à zéro");
			}
			if (jeu.getDuree() <= 0) {
				throw new JeuException("saisissez une durée supérieur à zéro");
			}
			if (jeu.getEditeur() == null || jeu.getEditeur().isBlank()) {
				throw new JeuException("editeur obligatoire");
			}
			if (jeu.getAnnee() == null || jeu.getAnnee().isBlank()) {
				throw new JeuException("annee obligatoire");
			}
			if (jeu.getPrix() < 0) {
				throw new JeuException("prix doit être supérieur à zéro");
			}
			if (jeu.getImage() == null || jeu.getImage().isBlank()) {
				throw new JeuException("chemin d'image obligatoire");
			}
			if (jeu.getTypeJeu() == null || jeu.getTypeJeu().isBlank()) {
				throw new JeuException("typeJeu obligatoire");
			}
			if (jeu.getStock() < 0) {
				throw new JeuException("saisissez un stock positif");
			}
			if (jeu.getDescription() == null || jeu.getDescription().isBlank()) {
				throw new JeuException("description obligatoire");
			}
			if (jeu.getDescription().length() > 2000) {
				throw new JeuException("description trop grande maximum 2000 caractères");
			}
		}
		
		private void checkId(Integer id) {
			if (id == null) {
				throw new IdException();
			}
		}
		
		public void checkExist(Jeu jeu) {
			checkId(jeu.getId());
			findById(jeu.getId());
		}
		
		public void delete(Jeu jeu) {
			checkExist(jeu);
			jeuRepo.delete(jeu);
		}
		
		public void delete(Integer id) {
			delete(findById(id));
		}
		
		public Jeu findById(Integer id) {
			checkId(id);
			return jeuRepo.findById(id).orElseThrow(IdException::new);
		}
		
		public List<Jeu> findAll(){
			return jeuRepo.findAll();
		}
		
		
		public Jeu update(Jeu jeu) {
			checkNotNull(jeu);
			checkExist(jeu);
			checkConstraint(jeu);
			Jeu jeuEnBase = findById(jeu.getId());
			jeuEnBase.setNom(jeu.getNom());
			jeuEnBase.setNbJoueurMin(jeu.getNbJoueurMin());
			jeuEnBase.setNbJoueurMax(jeu.getNbJoueurMax());
			jeuEnBase.setAgeMin(jeu.getAgeMin());
			jeuEnBase.setDuree(jeu.getDuree());
			jeuEnBase.setEditeur(jeu.getEditeur());
			jeuEnBase.setAnnee(jeu.getAnnee());
			jeuEnBase.setPrix(jeu.getPrix());
			jeuEnBase.setImage(jeu.getImage());
			jeuEnBase.setTypeJeu(jeu.getTypeJeu());
			jeuEnBase.setStock(jeu.getStock());
			jeuEnBase.setDescription(jeu.getDescription());
			// reservation pas obligatoire
			if (jeu.getReservation() != null) {
				jeuEnBase.setReservation(jeu.getReservation());
			}
			// reservation pas obligatoire
			if (jeu.getAchatJeux() != null) {
				jeuEnBase.setAchatJeux(jeu.getAchatJeux());
			}
			return jeuRepo.save(jeuEnBase);
		}
		
		//Fonction retounant tout les type de jeu existant
		public List<String> findAllTypeJeu() {
			
			List<String> typejeux = jeuRepo.findAllTypeJeu();
			String typeJeuCommaSeparated = typejeux.stream().collect(Collectors.joining(","));
			List<String> listTypeJeu = Stream.of(typeJeuCommaSeparated.split(",")).distinct().collect(Collectors.toList());
			return listTypeJeu;	
		}
}
