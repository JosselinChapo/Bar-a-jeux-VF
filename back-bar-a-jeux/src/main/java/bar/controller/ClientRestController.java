package bar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import bar.model.Client;
import bar.model.Views;
import bar.repository.IClientRepository;
import bar.service.ClientService;

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientRestController {

	@Autowired
	private ClientService serviceService;
	@Autowired
	private IClientRepository clientRepo;
	
	@GetMapping("")
	@JsonView(Views.ViewClient.class)
	public List<Client> findAll() {
		List<Client> clients = serviceService.findAll();

		return clients;
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewClient.class)
	public Client findById(@PathVariable Integer id) {
		Client client = serviceService.findById(id);

		return client;
	}
	
//	@GetMapping("/{id}")
//	@JsonView(Views.ViewClient.class)
//	public Client findByIdWithReservationAndCommandesJeux(@PathVariable Integer id) {
//		Optional<Client> optClient = clientRepo.findByIdWithReservationAndCommandesJeux(id);
//		
//		if (optClient.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
//
//		return optClient.get();
//	}
	

	@PostMapping("")
	@JsonView(Views.ViewClient.class)
	public Client save(@RequestBody Client client) {
		client = serviceService.save(client);
		
		return client;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewClient.class)
	public Client update(@RequestBody Client client, @PathVariable Integer id) {

		client = serviceService.update(client);
		
		return client;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		serviceService.delete(id);
	}
}
