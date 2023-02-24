package bar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;
@Entity
public class Conso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private int id;
	@Column
	@JsonView(Views.ViewBase.class)
	private double prix;
	@JsonView(Views.ViewBase.class)
	@Column
	private int qtité;

	@Column
	@JsonView(Views.ViewBase.class)
	private String nom;
	@Column(name="type_conso")
	@Enumerated(EnumType.STRING)
	@JsonView(Views.ViewBase.class)
	private TypeConso typeconso;
	@ManyToOne
	@JoinColumn (name= "commandeConso_id")
	private CommandeConso commandeConso;
	
	
	
	public Conso() {
		super();
	}
	
	
	public Conso(double prix, int qtité, String nom, TypeConso typeconso) {
		super();
		this.prix = prix;
		this.qtité = qtité;
		this.nom = nom;
		this.typeconso = typeconso;
	}


	public int getQtité() {
		return qtité;
	}

	public void setQtité(int qtité) {
		this.qtité = qtité;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public TypeConso getTypeconso() {
		return typeconso;
	}

	public void setTypeconso(TypeConso typeconso) {
		this.typeconso = typeconso;
	}

	public CommandeConso getCommandeConso() {
		return commandeConso;
	}
	public void setCommandeConso(CommandeConso commandeConso) {
		this.commandeConso = commandeConso;
	}
	
	
	
	

}
