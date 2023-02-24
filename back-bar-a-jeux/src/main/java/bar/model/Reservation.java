package bar.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewReservation.class)
	private Integer id;
	@JsonView(Views.ViewReservation.class)
	@Column(name = "dateRes")
	private LocalDate dateRes;
	@JsonView(Views.ViewReservation.class)
	@Column(name = "heureRes")
	private LocalTime heureRes;
	@JsonView(Views.ViewReservation.class)
	@Column(name = "nbPersonne")
	private Integer nbPersonne;
	@ManyToOne
	@JoinColumn(name = "tableBar_id")
	private TableBar tableBar;
	@OneToMany(mappedBy = "reservation")
	private List<CommandeConso> commandeConsos = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@OneToOne
	@JoinColumn(name = "jeu_id")
	private Jeu jeu;

	public Reservation() {
	}

	public Reservation(LocalDate dateRes, LocalTime heureRes, Integer nbPersonne, TableBar tableBar, Client client,
			Jeu jeu) {
		this.dateRes = dateRes;
		this.heureRes = heureRes;
		this.nbPersonne = nbPersonne;
		this.tableBar = tableBar;
		this.client = client;
		this.jeu = jeu;
	}

	public Reservation(LocalDate dateRes, LocalTime heureRes, Integer nbPersonne, TableBar tableBar, Client client) {
		this.dateRes = dateRes;
		this.heureRes = heureRes;
		this.nbPersonne = nbPersonne;
		this.tableBar = tableBar;
		this.client = client;
		;
	}
	
	public Reservation(Integer id, LocalDate dateRes, LocalTime heureRes, Integer nbPersonne, TableBar tableBar, Client client) {
		this.id =id;
		this.dateRes = dateRes;
		this.heureRes = heureRes;
		this.nbPersonne = nbPersonne;
		this.tableBar = tableBar;
		this.client = client;
		;
	}

	public Reservation(Integer id, LocalDate dateRes, LocalTime heureRes, Integer nbPersonne, TableBar tableBar, Client client, Jeu jeu) {
		this.id =id;
		this.dateRes = dateRes;
		this.heureRes = heureRes;
		this.nbPersonne = nbPersonne;
		this.tableBar = tableBar;
		this.client = client;
		this.jeu = jeu;
		;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TableBar getTable() {
		return tableBar;
	}

	public void setTable(TableBar tableBar) {
		this.tableBar = tableBar;
	}

	public List<CommandeConso> getCommandeConsos() {
		return commandeConsos;
	}

	public void setCommandeConsos(List<CommandeConso> commandeConsos) {
		this.commandeConsos = commandeConsos;
	}

	public LocalDate getDateRes() {
		return dateRes;
	}

	public void setDateRes(LocalDate dateRes) {
		this.dateRes = dateRes;
	}

	public LocalTime getHeureRes() {
		return heureRes;
	}

	public void setHeureRes(LocalTime heureRes) {
		this.heureRes = heureRes;
	}

	public Integer getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(Integer nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

}
