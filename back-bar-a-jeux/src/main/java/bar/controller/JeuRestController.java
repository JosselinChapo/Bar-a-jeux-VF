package bar.controller;

import java.util.ArrayList;
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

import bar.dto.FilterDTO;
import bar.model.Jeu;
import bar.model.Views;
import bar.repository.IJeuRepository;
import bar.service.JeuService;



	
@RestController
@RequestMapping("/jeu")
@CrossOrigin("*")
public class JeuRestController {
		@Autowired
		private JeuService jeuService;
		
		@Autowired
		private IJeuRepository jeuRepo;

		@GetMapping("")
		@JsonView(Views.ViewJeu.class)
		public List<Jeu> findAll() {
			List<Jeu> jeux = jeuService.findAll();

			return jeux;
		}

		@GetMapping("/{id}")
		@JsonView(Views.ViewJeu.class)
		public Jeu findById(@PathVariable Integer id) {
			
			Jeu jeu = jeuService.findById(id);
			
			return jeu;
		}
		
		@PostMapping("")
		@JsonView(Views.ViewJeu.class)
		public Jeu create(@RequestBody Jeu jeu) {
			jeu = jeuService.create(jeu);
			
			return jeu;
		}
		
		@GetMapping("/typeJeu")
		@JsonView(Views.ViewJeu.class)
		public List<String> allTypeJeu() {
			List<String> typeJeux = jeuService.findAllTypeJeu();
			
			return typeJeux;
		}
		
		
		@PostMapping("/filtre")
		@JsonView(Views.ViewJeu.class)
		public List<Jeu> findByfilter(@RequestBody FilterDTO filterDTO) {
			List<Jeu> jeux = new ArrayList();
			if(filterDTO.getNbJoueur()==0) {
				jeux = jeuService.findByTypeJeuDuree(filterDTO.getDureeMin(),filterDTO.getDureeMax(),filterDTO.getTypeJeu());
			}
			else {
				jeux = jeuService.findByFilter(filterDTO.getNbJoueur(),filterDTO.getTypeJeu(),filterDTO.getDureeMin(),filterDTO.getDureeMax());
			}
			
			for (Jeu j : jeux) {
				System.out.println(j.getNom());
			}
			return jeux;
		}
		
		
		@PutMapping("/{id}")
		@JsonView(Views.ViewJeu.class)
		public Jeu update(@RequestBody Jeu jeu, @PathVariable Integer id) {
			
			
			if(id != jeu.getId()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
			try {
				jeuService.checkExist(jeu);
			} catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			
			jeu = jeuService.update(jeu);
			
			return jeu;
		}
		
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id) {
			jeuService.delete(id);
		}
		
}
	

