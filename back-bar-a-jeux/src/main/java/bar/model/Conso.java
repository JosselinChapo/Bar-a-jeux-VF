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
	private String nom;
	@JsonView(Views.ViewBase.class)
	private double prix;
	
	@Column(name="type_conso")
	@Enumerated(EnumType.STRING)
	@JsonView(Views.ViewBase.class)
	private TypeConso typeconso;
	
	public Conso() {
		super();
	}
	
	
	
	public Conso( String nom, double prix, TypeConso typeconso) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.typeconso = typeconso;
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

	
	
	
	

}
