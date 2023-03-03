package bar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.exception.CompteException;
import bar.exception.IdException;
import bar.exception.TableException;
import bar.model.Client;
import bar.model.TableBar;
import bar.repository.ITableRepository;

@Service
public class TableService {
	
	@Autowired
	private ITableRepository tableRepo;
	
	// creation réservation
		public TableBar create(TableBar tableBar) {
			checkNotNull(tableBar);
			checkConstraint(tableBar);
			return tableRepo.save(tableBar);
		}
		
		private void checkNotNull(TableBar tableBar) {
			if (tableBar == null) {
				throw new TableException("table obligatoire");
			}
		}
		
		private void checkConstraint(TableBar tableBar) {
			if (tableBar.getNbPersonne() == 0) {
				throw new TableException("capacité de la table obligatoire");
			}
			if (tableBar.getIdTable() == 0) {
				throw new TableException("id de la table obligatoire");
			}
		}
		
		private void checkId(Integer id) {
			if (id == null) {
				throw new IdException("id obligatoire");
			}
			if (!findAllId().contains(id)) {
				throw new IdException("id introuvable");
			}
		}

		private void checkExist(TableBar tableBar) {
			checkId(tableBar.getId());
			findById(tableBar.getId());
		}

		public void delete(TableBar tableBar) {
			checkExist(tableBar);
			tableRepo.delete(tableBar);
		}

		public void delete(Integer id) {
			delete(findById(id));
		}
		
		public TableBar update(TableBar tableBar) {
			checkNotNull(tableBar);
			checkExist(tableBar);
			checkConstraint(tableBar);
			TableBar tableEnBase = findById(tableBar.getId());
			tableEnBase.setIdTable(tableBar.getIdTable());
			tableEnBase.setNbPersonne(tableBar.getNbPersonne());
			// donner un jeu n'est pas obligatoire
//			if (tableBar.getEvenement() != null) {
//				tableEnBase.setEvenement(tableBar.getEvenement());
//			}
			return tableRepo.save(tableEnBase);
		}
		
		public TableBar findById(Integer id) {
			checkId(id);
			return tableRepo.findById(id).orElseThrow(IdException::new);
		}
		
		public List<TableBar> findAll(){
			return tableRepo.findAll();
		}
		

		public List<Integer> findAllId(){
			return tableRepo.findAllId();
		}
		
		public TableBar findByIdTable(Integer idTable) {
			
			Optional<TableBar>  newTable =  tableRepo.findByIdTable(idTable);
			
			if(newTable.isEmpty()) {
				throw new CompteException("client absent");
				
			}
			
			return  newTable.get();
		}

}
