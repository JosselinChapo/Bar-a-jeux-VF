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

import bar.model.Conso;
import bar.model.TypeConso;
import bar.model.Views;
import bar.repository.IConsoRepository;
import bar.service.ConsoService;

@RestController
@RequestMapping("/conso")
@CrossOrigin("*")
public class ConsoRestController {
	
	@Autowired
	private ConsoService consoService;
	
	@Autowired
	IConsoRepository consoRepo;
	

	@GetMapping("")
	@JsonView(Views.ViewConso.class)
	public List<Conso> findAll() {
		List<Conso> consos = consoService.findAll();

		return consos;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewConso.class)
	public Conso findById(@PathVariable Integer id) {
		Conso conso = consoService.findById(id);

		if (id!= conso.getId()){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return conso;
	}

	
//	@GetMapping("/{id}/Consos")
//	@JsonView(Views.ViewConso.class)
//	public List<Conso> findAll(@PathVariable Integer id) {
//		List<Conso> consos = ConsoService.findAllById(id);
//
//		return consos;
//	}

	@PostMapping("")
	@JsonView(Views.ViewConso.class)
	public Conso create(@RequestBody Conso conso) {
		conso = consoService.save(conso);

		return conso;
	}
	

	@GetMapping("/boisson")
	@JsonView(Views.ViewConso.class)
	public List<Conso> findAllByTypeconsoBoisson(){
		List<Conso> consos= new ArrayList<>();
		consos = consoRepo.findAllByTypeconsoBoisson();
//		if(boisson.equals("boisson")) {
//				
//		}
		return consos;
	}
	
	@GetMapping("/plat")
	@JsonView(Views.ViewConso.class)
	public List<Conso> findAllByTypeconsoPlat(){
		List<Conso> consos= new ArrayList<>();
		consos = consoRepo.findAllByTypeconsoPlat();
//		if(plat.equals("plat")) {			
//			
//				}

		return consos;
	}
	
	

	@PutMapping("/{id}")
	@JsonView(Views.ViewConso.class)
	public Conso update(@RequestBody Conso conso, @PathVariable Integer id) {
		if (id != conso.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return conso;
		}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		consoService.delete(id);
	}

}

