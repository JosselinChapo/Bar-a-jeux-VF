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

import bar.exception.IdException;
import bar.model.TableBar;
import bar.model.Views;
import bar.repository.ITableRepository;
import bar.service.TableService;

@RestController
@RequestMapping("/tableBar")
@CrossOrigin("*")
public class TableBarRestController {
	@Autowired
	private TableService tableSrv;
	@Autowired
	private ITableRepository tableRep;

	@GetMapping("")
	@JsonView(Views.ViewTableBar.class)
	public List<TableBar> findAll() {
		List<TableBar> tables = tableSrv.findAll();

		return tables;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewTableBar.class)
	public TableBar findById(@PathVariable Integer id) {
		TableBar tableBar = new TableBar();
		try {
			tableBar = tableSrv.findById(id);
			
		} catch (IdException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id inconnu");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return tableBar;
	}
	
	@PostMapping("")
	@JsonView(Views.ViewTableBar.class)
	public TableBar create(@RequestBody TableBar formateur) {
		formateur = tableSrv.create(formateur);
		
		return formateur;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewTableBar.class)
	public TableBar update(@RequestBody TableBar formateur, @PathVariable Integer id) {
		if(id != formateur.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if(!tableRep.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		formateur = tableSrv.update(formateur);
		
		return formateur;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		tableSrv.delete(id);
	}
	
}
