package bar.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("Client")
public class Client extends Compte {
	
	@JsonView(Views.ViewBase.class)
	@Column(name="nom", length = 100)
	private String nom;
	@JsonView(Views.ViewBase.class)
	@Column(name="prenom", length = 100)
	private String prenom;
	@JsonView(Views.ViewBase.class)
	@Column(name="tel", length = 50)
	private String tel;
	@JsonView(Views.ViewBase.class)
	@Column(name="dateNaissance")
	private LocalDate dateNaissance;
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private List<Reservation> reservations = new ArrayList<>();
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private List<CommandeJeu> commandeJeux = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@JsonView(Views.ViewBase.class)
	private Civilite civilite;
	
	public Client() {
		super();
	}
	
	public Client(String mail, String password, String nom, String prenom, String tel, Civilite civilite) {
		super(mail, password);
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.civilite = civilite;
	}
	
	public Client(String mail, String password, String nom, String prenom, String tel, Civilite civilite, LocalDate dateNaissance) {
		super(mail, password);
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.civilite = civilite;
		this.dateNaissance = dateNaissance;
	}
	
	public Client(Integer id, String mail, String password, String nom, String prenom, String tel, Civilite civilite, LocalDate dateNaissance) {
		super(id, mail, password);
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.civilite = civilite;
		this.dateNaissance = dateNaissance;
	}

	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getTel() {
		return tel;
	}


	public LocalDate getDateNaissance() {
		return dateNaissance;
	}


	public List<Reservation> getReservations() {
		return reservations;
	}


	public List<CommandeJeu> getCommandeJeux() {
		return commandeJeux;
	}


	public Civilite getCivilite() {
		return civilite;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}


	public void setCommandeJeux(List<CommandeJeu> commandeJeux) {
		this.commandeJeux = commandeJeux;
	}


	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}
		
	
}