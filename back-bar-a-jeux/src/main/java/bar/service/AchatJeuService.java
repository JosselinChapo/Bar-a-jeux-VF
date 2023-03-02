package bar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import bar.exception.AchatJeuException;
import bar.exception.IdException;
import bar.model.AchatJeu;
import bar.repository.IAchatJeuRepository;

@Service
public class AchatJeuService {

	@Autowired
	private IAchatJeuRepository achatJeuRepo;
	
	// creation achat jeu
		public AchatJeu create(AchatJeu achatJeu) {
			checkNotNull(achatJeu);
			checkConstraint(achatJeu);
			return achatJeuRepo.save(achatJeu);
		}
		
		private void checkNotNull(AchatJeu achatJeu) {
			if (achatJeu == null) {
				throw new AchatJeuException("achatJeu obligatoire");
			}
		}
		
		private void checkConstraint(AchatJeu achatJeu) {
			if (achatJeu.getDateAchat() == null) {
				throw new AchatJeuException("date d'achat obligatoire");
			}
			if (achatJeu.getQuantite() < 0) {
				throw new AchatJeuException("saisir quantité positive");
			}
			if (achatJeu.getJeu() == null) {
				throw new AchatJeuException("saisir un objet jeu");
			}
			if (achatJeu.getCommandeJeu() == null) {
				throw new AchatJeuException("saisir un objet commandeJeu");
			}
		}
		
		private void checkId(Integer id) {
			if (id == null) {
				throw new IdException();
			}
		}
		
		public void checkExist(AchatJeu achatJeu) {
			checkId(achatJeu.getId());
			findById(achatJeu.getId());
		}
		
		public void delete(AchatJeu achatJeu) {
			checkExist(achatJeu);
			achatJeuRepo.delete(achatJeu);
		}
		
		public void delete(Integer id) {
			delete(findById(id));
		}
		
		public AchatJeu findById(Integer id) {
			checkId(id);
			return achatJeuRepo.findById(id).orElseThrow(IdException::new);
		}
		
		
		
		public List<AchatJeu> findAll(){
			return achatJeuRepo.findAll();
		}
		
		public List<Integer> findAllIdByCommande(Integer id){
			return achatJeuRepo.findAllAchatJeuIdByCommandeJeu(id);
		}
		
		
		public AchatJeu update(AchatJeu achatJeu) {
			checkNotNull(achatJeu);
			checkExist(achatJeu);
			checkConstraint(achatJeu);
			AchatJeu achatJeuEnBase = findById(achatJeu.getId());
			achatJeuEnBase.setDateAchat(achatJeu.getDateAchat());
			achatJeuEnBase.setQuantite(achatJeu.getQuantite());
			achatJeuEnBase.setJeu(achatJeu.getJeu());
			achatJeuEnBase.setCommandeJeu(achatJeu.getCommandeJeu());
			
			return achatJeuRepo.save(achatJeuEnBase);
		}
	
}
