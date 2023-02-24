package bar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.exception.CommandeJeuException;
import bar.exception.IdException;
import bar.model.CommandeJeu;
import bar.repository.ICommandeJeuRepository;
@Service
public class CommandeJeuService {

	@Autowired
	private ICommandeJeuRepository commandeJeuRepo;
	
	// creation commande
		public CommandeJeu create(CommandeJeu commandeJeu) {
			checkNotNull(commandeJeu);
			checkConstraint(commandeJeu);
			return commandeJeuRepo.save(commandeJeu);
		}
		
		private void checkNotNull(CommandeJeu commandeJeu) {
			if (commandeJeu == null) {
				throw new CommandeJeuException("commandeJeu obligatoire");
			}
		}
		
		private void checkConstraint(CommandeJeu commandeJeu) {
			// Statut a faire valider
			if (commandeJeu.getStatut() == null) { //|| !(commandeJeu.getStatut().equals(Statut.EnCours) || commandeJeu.getStatut().equals(Statut.Validee) || commandeJeu.getStatut().equals(Statut.Livree))) {
				throw new CommandeJeuException("statut obligatoire");
			}
			if (commandeJeu.getClient() == null) {
				throw new CommandeJeuException("client obligatoire");
			}
		}
		
		private void checkId(Integer id) {
			if (id == null) {
				throw new IdException();
			}
		}
		
		public void checkExist(CommandeJeu commandeJeu) {
			checkId(commandeJeu.getId());
			findById(commandeJeu.getId());
		}
		
		public void delete(CommandeJeu commandeJeu) {
			checkExist(commandeJeu);
			commandeJeuRepo.delete(commandeJeu);
		}
		
		public void delete(Integer id) {
			delete(findById(id));
		}
		
		public CommandeJeu findById(Integer id) {
			checkId(id);
			return commandeJeuRepo.findById(id).orElseThrow(IdException::new);
		}
		
		public List<CommandeJeu> findAll(){
			return commandeJeuRepo.findAll();
		}
		
		
		public CommandeJeu update(CommandeJeu commandeJeu) {
			checkNotNull(commandeJeu);
			checkExist(commandeJeu);
			checkConstraint(commandeJeu);
			CommandeJeu commandeJeuEnBase = findById(commandeJeu.getId());
			commandeJeuEnBase.setStatut(commandeJeu.getStatut());
			commandeJeuEnBase.setClient(commandeJeu.getClient());
			if(!commandeJeu.getAchatJeux().isEmpty()) {
			commandeJeuEnBase.setAchatJeux(commandeJeuEnBase.getAchatJeux());
			}
			return commandeJeuRepo.save(commandeJeuEnBase);
		}
	
}
