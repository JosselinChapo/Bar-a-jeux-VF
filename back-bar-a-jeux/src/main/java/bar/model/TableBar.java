package bar.model;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "table_bdd")
public class TableBar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Evenement evenement;
	@JsonView(Views.ViewBase.class)
	@Column(name = "nbPersonne")
	private int nbPersonne;
	@JsonView(Views.ViewReservation.class)
	@Column(name = "idTable")
	private int idTable;
	@JsonIgnore
	@OneToMany(mappedBy = "tableBar")
	private List<Reservation> reservations = new ArrayList<>();
	// à voir si cette liste est necessaire à un moment
	
	public TableBar() {
	}
	
	


	public TableBar(int nbPersonne, int idTable) {
		this.nbPersonne = nbPersonne;
		this.idTable = idTable;
	}

	


	public TableBar(int id, int nbPersonne, int idTable) {
		this.id = id;
		this.nbPersonne = nbPersonne;
		this.idTable = idTable;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Evenement getEvenement() {
		return evenement;
	}


	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}


	public int getNbPersonne() {
		return nbPersonne;
	}


	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}


	public int getIdTable() {
		return idTable;
	}


	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}


	public List<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
