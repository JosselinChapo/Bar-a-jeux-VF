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

import bar.dto.AuthDTO;
import bar.model.Client;
import bar.model.Views;
import bar.service.ClientService;

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientRestController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("")
	@JsonView(Views.ViewClient.class)
	public List<Client> findAll() {
		List<Client> clients = clientService.findAll();

		return clients;
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewClient.class)
	public Client findById(@PathVariable Integer id) {
		Client client = clientService.findById(id);

		return client;
	}
	
	@PostMapping("")
	@JsonView(Views.ViewClient.class)
	public Client save(@RequestBody Client client) {
		client = clientService.save(client);
		
		return client;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewClient.class)
	public Client update(@RequestBody Client client, @PathVariable Integer id) {

		client = clientService.update(client);
		
		return client;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		clientService.delete(id);
	}
	
	@PostMapping("/auth")
	@JsonView(Views.ViewClient.class)
	public Client auth(@RequestBody AuthDTO authentification) {
		
		return clientService.findByMailAndPassword(authentification.getMail(), authentification.getPassword());
		
	}
}
