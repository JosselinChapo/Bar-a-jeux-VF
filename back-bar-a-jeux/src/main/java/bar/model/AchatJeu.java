package bar.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class AchatJeu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private int id;
	@JsonView(Views.ViewBase.class)
	private LocalDate dateAchat;
	@JsonView(Views.ViewBase.class)
	private int quantite;
	
	@ManyToOne
	@JoinColumn(name = "jeu_id")
	@JsonView(Views.ViewAchatJeu.class)
	private Jeu jeu;
	@ManyToOne
	@JoinColumn(name = "CommandeJeu_id")
	@JsonView(Views.ViewAchatJeu.class)
	private CommandeJeu commandeJeu;
	
	public AchatJeu() {
		super();
	}

	
	public AchatJeu(LocalDate dateAchat, int quantite, Jeu jeu, CommandeJeu commandeJeu) {
		super();
		this.dateAchat = dateAchat;
		this.quantite = quantite;
		this.jeu = jeu;
		this.commandeJeu = commandeJeu;
	}
	

	public AchatJeu(int id, LocalDate dateAchat, int quantite, Jeu jeu, CommandeJeu commandeJeu) {
		super();
		this.id = id;
		this.dateAchat = dateAchat;
		this.quantite = quantite;
		this.jeu = jeu;
		this.commandeJeu = commandeJeu;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public CommandeJeu getCommandeJeu() {
		return commandeJeu;
	}

	public void setCommandeJeu(CommandeJeu commandeJeu) {
		this.commandeJeu = commandeJeu;
	}
	
	
}
