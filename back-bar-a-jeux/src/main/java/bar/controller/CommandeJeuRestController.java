package bar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import bar.model.AchatJeu;
import bar.model.CommandeJeu;
import bar.model.Views;
import bar.service.AchatJeuService;
import bar.service.CommandeJeuService;



	
@RestController
@RequestMapping("/commandeJeu")
@CrossOrigin("*")
public class CommandeJeuRestController {
		@Autowired
		private CommandeJeuService commandeJeuService;
		@Autowired
		private AchatJeuService achatJeuService;

		@GetMapping("")
		@JsonView(Views.ViewCommandeJeu.class)
		public List<CommandeJeu> findAll() {
			List<CommandeJeu> CommandeJeux = commandeJeuService.findAll();

			return CommandeJeux;
		}

		@GetMapping("/{id}")
		@JsonView(Views.ViewCommandeJeu.class)
		public CommandeJeu findById(@PathVariable Integer id) {
			
			CommandeJeu CommandeJeu = commandeJeuService.findById(id);
			
			return CommandeJeu;
		}
		
		@GetMapping("client/{id}")
		@JsonView(Views.ViewCommandeJeu.class)
		public  List<CommandeJeu> findByIdClient(@PathVariable Integer id) {
			
			List<CommandeJeu> CommandeJeux = commandeJeuService.findAllByClientId(id);
			
			return CommandeJeux;
		}
		
		@GetMapping("client/panier/{id}")
		@JsonView(Views.ViewCommandeJeu.class)
		public  List<CommandeJeu> findByIdClientPanier(@PathVariable Integer id) {
			
			List<CommandeJeu> CommandeJeux = commandeJeuService.findAllByClientIdPanier(id);
			
			return CommandeJeux;
		}
		
		@PostMapping("")
		@JsonView(Views.ViewCommandeJeu.class)
		public CommandeJeu create(@RequestBody CommandeJeu CommandeJeu) {
			CommandeJeu = commandeJeuService.create(CommandeJeu);
			
			return CommandeJeu;
		}
		
		
		
		@PutMapping("/{id}")
		@JsonView(Views.ViewCommandeJeu.class)
		public CommandeJeu update(@RequestBody CommandeJeu commandeJeu, @PathVariable Integer id) {
			
			
			if(id != commandeJeu.getId()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
			try {
				commandeJeuService.checkExist(commandeJeu);
			} catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
			commandeJeu = commandeJeuService.update(commandeJeu);
			
			return commandeJeu;
		}
		
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id) {
			List<Integer> idsAchat = achatJeuService.findAllIdByCommande(id);
			for(Integer idAchat : idsAchat) {
				
				achatJeuService.delete(idAchat);
			
			}
			commandeJeuService.delete(id);
		}
		
}
	

