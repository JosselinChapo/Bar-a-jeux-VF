package bar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import bar.model.CommandeConso;
import bar.model.Views;
import bar.service.CommandeConsoService;

@RestController
@RequestMapping("/commandeConso")
public class CommandeConsoRestController {
	
	@Autowired
	private CommandeConsoService commandeConsoService;

	@GetMapping("")
	@JsonView(Views.ViewCommandeConso.class)
	public List<CommandeConso> findAll() {
		List<CommandeConso> commandeConsos = commandeConsoService.findAll();

		return commandeConsos;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewCommandeConso.class)
	public CommandeConso findById(@PathVariable Integer id) {
		CommandeConso commandeConso = commandeConsoService.findById(id);

		if (id!= commandeConso.getId()){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return commandeConso;
	}

	
//	@GetMapping("/{id}/CommandecommandeConsos")
//	@JsonView(Views.ViewCommandecommandeConso.class)
//	public List<CommandecommandeConso> findAll(@PathVariable Integer id) {
//		List<CommandecommandeConso> CommandecommandeConsos = CommandecommandeConsoService.findAllById(id);
//
//		return CommandecommandeConsos;
//	}

	@PostMapping("")
	@JsonView(Views.ViewCommandeConso.class)
	public CommandeConso create(@RequestBody CommandeConso commandeConso) {
		commandeConso = commandeConsoService.save(commandeConso);

		return commandeConso;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewCommandeConso.class)
	public CommandeConso update(@RequestBody CommandeConso commandeConso, @PathVariable Integer id) {
		if (id != commandeConso.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		commandeConso = commandeConsoService.save(commandeConso);
		return commandeConso;
		}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		commandeConsoService.delete(id);
	}

}
