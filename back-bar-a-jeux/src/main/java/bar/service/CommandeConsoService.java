package bar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.exception.CommandeConsoException;
import bar.exception.CommandeJeuException;
import bar.exception.IdException;
import bar.model.CommandeConso;
import bar.model.Conso;
import bar.model.Reservation;
import bar.repository.ICommandeConsoRepository;

@Service
public class CommandeConsoService {

	@Autowired
	private ICommandeConsoRepository commandeConsoRepo;
	
	// creation commande
		public CommandeConso create(List<Conso> consos ,Reservation reservation) {
			CommandeConso commandeConso= new CommandeConso(consos,reservation);
			checkConstraint(commandeConso);
			checkNotNull(commandeConso);
			return commandeConsoRepo.save(commandeConso);
		}
		

		public CommandeConso save(CommandeConso commandeConso) {
			checkNotNull(commandeConso);
			checkConstraint(commandeConso);
			return commandeConsoRepo.save(commandeConso);
		}
		
		private void checkNotNull(CommandeConso commandeConso) {
			if (commandeConso == null) {
				throw new CommandeConsoException("commandeConso obligatoire");
			}
		}
		
		private void checkConstraint(CommandeConso commandeConso) {
			if (commandeConso.getConsos().isEmpty()) {
				throw new CommandeConsoException("la commande est vide");
			}
			
			
			if (commandeConso.getReservation()==null ) {
				throw new CommandeConsoException("Aucune réservation n'est affectée à la commande");
			}
			
		}
		
		
		private void checkId(Integer id) {
			if (id == null) {
				throw new IdException();
			}
		}
		
		private void checkExist(CommandeConso commandeConso) {
			checkId(commandeConso.getId());
			findById(commandeConso.getId());
		}
		
		public void delete(CommandeConso commandeConso) {
			checkExist(commandeConso);
			commandeConsoRepo.delete(commandeConso);
		}
		
		public void delete(Integer id) {
			delete(findById(id));
		}
		
		public CommandeConso findById(Integer id) {
			checkId(id);
			return commandeConsoRepo.findById(id).orElseThrow(CommandeJeuException::new);
		}
		
		public List<CommandeConso> findAll(){
			return commandeConsoRepo.findAll();
		}
		
		
		public CommandeConso update(CommandeConso commandeConso) {
			checkNotNull(commandeConso);
			checkExist(commandeConso);
			CommandeConso commandeConsoEnBase = findById(commandeConso.getId());
			
		
			return commandeConsoRepo.save(commandeConsoEnBase);
		}
		

		public void existById(Integer id) {
			findById(id);
			if (id==null) {
				throw new IdException("Id introuvable");
			}
		}
	
}
