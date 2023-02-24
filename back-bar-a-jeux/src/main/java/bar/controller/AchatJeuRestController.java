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
import bar.model.Jeu;
import bar.model.Views;
import bar.service.AchatJeuService;



	
@RestController
@RequestMapping("/achatJeu")
@CrossOrigin("*")
public class AchatJeuRestController {
		@Autowired
		private AchatJeuService AchatJeuService;
		

		@GetMapping("")
		@JsonView(Views.ViewAchatJeu.class)
		public List<AchatJeu> findAll() {
			List<AchatJeu> AchatJeux = AchatJeuService.findAll();

			return AchatJeux;
		}

		@GetMapping("/{id}")
		@JsonView(Views.ViewAchatJeu.class)
		public AchatJeu findById(@PathVariable Integer id) {
			
			AchatJeu AchatJeu = AchatJeuService.findById(id);
			
			return AchatJeu;
		}
		
		@PostMapping("")
		@JsonView(Views.ViewAchatJeu.class)
		public AchatJeu create(@RequestBody AchatJeu AchatJeu) {
			AchatJeu = AchatJeuService.create(AchatJeu);
			
			return AchatJeu;
		}
		
		@PutMapping("/{id}")
		@JsonView(Views.ViewAchatJeu.class)
		public AchatJeu update(@RequestBody AchatJeu achatJeu, @PathVariable Integer id) {
			
			
			if(id != achatJeu.getId()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
			try {
				AchatJeuService.checkExist(achatJeu);
			} catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
			achatJeu = AchatJeuService.update(achatJeu);
			
			return achatJeu;
		}
		
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id) {
			AchatJeuService.delete(id);
		}
		
}
	

