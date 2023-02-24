package bar.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class CommandeJeu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private int id;
	@Enumerated(EnumType.STRING)
	@JsonView(Views.ViewBase.class)
	private Statut statut;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@JsonView(Views.ViewCommandeJeu.class)
	private Client client;
	
	@OneToMany(mappedBy = "commandeJeu")
	@JsonView(Views.ViewCommandeJeu.class)
	private List<AchatJeu> achatJeux = new ArrayList<>();

	public CommandeJeu() {
		super();
	}

	public CommandeJeu(Statut statut, Client client) {
		super();
		this.statut = statut;
		this.client = client;
	}



	public CommandeJeu(int id, Statut statut, Client client) {
		super();
		this.id = id;
		this.statut = statut;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<AchatJeu> getAchatJeux() {
		return achatJeux;
	}

	public void setAchatJeux(List<AchatJeu> achatJeux) {
		this.achatJeux = achatJeux;
	}


	public Statut getStatut() {
		return statut;
	}


	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	
}
