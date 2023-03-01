package bar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bar.exception.ClientException;
import bar.exception.CompteException;
import bar.exception.IdException;
import bar.model.Admin;
import bar.model.Civilite;
import bar.model.Client;
import bar.repository.IClientRepository;

@Service
public class ClientService {

	@Autowired 
	private IClientRepository clientRepo;
	
	public Client createLessBirthday(String mail, String password, String type, String nom, String prenom, String tel, Civilite civilite) {
		Client compteClient = new Client(mail, password, type, nom, prenom, tel, civilite);
		return save(compteClient);
	}
	
	public Client createWithBirthday(String mail, String password, String type, String nom, String prenom, String tel, Civilite civilite, LocalDate dateNaissance) {
		Client compteClient = new Client(mail, password, type, nom, prenom, tel, civilite, dateNaissance);
		return save(compteClient);
	}
	
	public Client save(Client compteClient) {
		checkNotNull(compteClient);
		checkConstraint(compteClient);
		return clientRepo.save(compteClient);
	}
	
	public void checkNotNull(Client compteClient) {
		if (compteClient == null) {
			throw new ClientException("compte client obligatoire");
		}
	}
	
	private void checkConstraint(Client compteClient) {
		if (compteClient.getMail() == null || compteClient.getMail().isBlank()) {
			throw new ClientException("mail obligatoire");
		}
		if (compteClient.getPassword() == null || compteClient.getPassword().isBlank()) {
			throw new ClientException("mot de passe obligatoire");
		}
		if (compteClient.getNom() == null || compteClient.getNom().isBlank()) {
			throw new ClientException("nom obligatoire");
		}
		if (compteClient.getPrenom() == null || compteClient.getPrenom().isBlank()) {
			throw new ClientException("prenom obligatoire");
		}
		if (compteClient.getTel() == null || compteClient.getTel().isBlank()) {
			throw new ClientException("téléphone obligatoire");
		}
		if (compteClient.getCivilite() == null) {
			throw new ClientException("civilite obligatoire");
		}
	}
	
	private void checkId(Integer id) {
		if (id == null) {
			throw new IdException();
		}
	}
	
	private void checkExist(Client compteClient) {
		checkId(compteClient.getId());
		findById(compteClient.getId());
	}
	
	public void delete(Client compteClient) {
		checkExist(compteClient);
		clientRepo.delete(compteClient);
	}
	
	public void delete(Integer id) {
		delete(findById(id));
	}
	
	public Client findById(Integer id) {
		checkId(id);
		return clientRepo.findById(id).orElseThrow(ClientException::new);
	}

	public List<Client> findAll(){	
		return clientRepo.findAll();
	}
	
	public Client update(Client compteClient) {
		checkNotNull(compteClient);
		checkExist(compteClient);
		checkConstraint(compteClient);
		Client clientEnBase = findById(compteClient.getId());
		clientEnBase.setMail(compteClient.getMail());
		clientEnBase.setPassword(compteClient.getPassword());
		clientEnBase.setCivilite(compteClient.getCivilite());
		clientEnBase.setNom(compteClient.getNom());
		clientEnBase.setPrenom(compteClient.getPrenom());
		clientEnBase.setTel(compteClient.getTel());
		clientEnBase.setDateNaissance(compteClient.getDateNaissance());
		return clientRepo.save(clientEnBase);
	}
	
	public Client findByMailAndPassword(String mail, String password) {
		
		Optional<Client>  newClient =  clientRepo.findByMailAndPassword(mail, password);
		
		if(newClient.isEmpty()) {
			throw new CompteException("client absent");
			
		}
		
		return  newClient.get();
	}
}
