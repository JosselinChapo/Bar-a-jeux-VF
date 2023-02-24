package bar.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Evenement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 255)
	private String nomEvent;
	private int nbClientMax;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String nomJeu;
	@Enumerated(EnumType.STRING)
	private TypeEvent typeEvent;
	@OneToMany(mappedBy = "evenement")
	private List<TableBar> tables = new ArrayList<>();
	
	public Evenement() {
	}
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}


	public String getNomEvent() {
		return nomEvent;
	}

	public void setNomEvent(String nomEvent) {
		this.nomEvent = nomEvent;
	}

	public int getNbClientMax() {
		return nbClientMax;
	}

	public void setNbClientMax(int nbClientMax) {
		this.nbClientMax = nbClientMax;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getNomJeu() {
		return nomJeu;
	}

	public void setNomJeu(String nomJeu) {
		this.nomJeu = nomJeu;
	}

	public TypeEvent getTypeEvent() {
		return typeEvent;
	}

	public void setTypeEvent(TypeEvent typeEvent) {
		this.typeEvent = typeEvent;
	}

	public List<TableBar> getTables() {
		return tables;
	}

	public void setTables(List<TableBar> tables) {
		this.tables = tables;
	}
	
	

}
