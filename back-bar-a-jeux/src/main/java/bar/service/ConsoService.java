package bar.service;


	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.exception.CommandeConsoException;
import bar.exception.ConsoException;
import bar.exception.IdException;
import bar.model.Conso;
import bar.model.TypeConso;
import bar.repository.IConsoRepository;

	@Service
	public class ConsoService {
		
		@Autowired
		private IConsoRepository consoRepo;
		
		// creation conso
			public Conso create(double prix, int qtité, String nom,TypeConso typeconso) {
				Conso conso= new Conso(prix,qtité,nom,typeconso);
				checkConstraint(conso);
				checkNotNull(conso);
				return save(conso);
				
			}
			
			
			private void checkNotNull(Conso Conso) {
				if (Conso == null) {
					throw new ConsoException("Conso obligatoire");
				}
			}
			
			public Conso save(Conso conso) {
				checkNotNull(conso);
				checkConstraint(conso);
				return consoRepo.save(conso);
			}
			
			private void checkConstraint(Conso conso) {
				if (conso.getNom() == null || conso.getNom().isBlank()) {
					throw new ConsoException("nom obligatoire");
				}
				if (conso.getPrix()<0 ) {
					throw new ConsoException("prix supérieur à 0");
				}
				if (conso.getTypeconso() == null ) {
					throw new ConsoException("Le choix de la conso est obligatoire");
				}
				if (conso.getQtité()<0) {
					throw new CommandeConsoException("la quantité est incorrecte");
				}
				
			}
			
			private void checkId(Integer id) {
				if (id == null) {
					throw new IdException();
				}
			}
			
			private void checkExist(Conso conso) {
				checkId(conso.getId());
				findById(conso.getId());
			}
			
			public void delete(Conso conso) {
				checkExist(conso);
				consoRepo.delete(conso);
			}
			
			public void delete(Integer id) {
				consoRepo.delete(findById(id));
			}
			
			public Conso findById(Integer id) {
				checkId(id);
				return consoRepo.findById(id).orElseThrow(ConsoException::new);
			}
			
			public List<Conso> findAll(){
				return consoRepo.findAll();
			}
			
			
			public Conso update(Conso conso) {
				
				checkExist(conso);
				checkConstraint(conso);
				Conso consoEnBase = findById(conso.getId());
				consoEnBase.setNom(conso.getNom());
				consoEnBase.setPrix(conso.getPrix());
				consoEnBase.setTypeconso(conso.getTypeconso());
				return consoRepo.save(consoEnBase);
				
				
				
	}




			



}
