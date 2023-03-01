package bar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import bar.model.Civilite;
import bar.model.Conso;
import bar.model.TypeConso;
import bar.repository.IConsoRepository;

@RestController
@CrossOrigin("*")
public class CommonRestController {
	
	@Autowired
	IConsoRepository consoRepo;
	
	@GetMapping("/civilites")
	public Civilite[] getCivilites() {
		return Civilite.values();
	}
	

	@GetMapping("/typeconsos")
	public TypeConso[] getTypeConso() {
		return TypeConso.values();
	}
	
	@PostMapping("")
	public List<Conso> findByTypeconso(TypeConso typeconso){
		List<Conso> consos = consoRepo.findByTypeconso(typeconso);

		return consos;
	}


}

