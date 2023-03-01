package bar.dto;

public class FilterDTO {
	private int nbJoueur;
	private int dureeMin;
	private int dureeMax;
	private String typeJeu;
	private double prixMin;
	private double prixMax;
	
	
	public FilterDTO() {
		super();
	}
	
	public int getNbJoueur() {
		return nbJoueur;
	}
	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
	public int getDureeMin() {
		return dureeMin;
	}
	public void setDureeMin(int dureeMin) {
		this.dureeMin = dureeMin;
	}
	public int getDureeMax() {
		return dureeMax;
	}
	public void setDureeMax(int dureeMax) {
		this.dureeMax = dureeMax;
	}
	public String getTypeJeu() {
		return typeJeu;
	}
	public void setTypeJeu(String typeJeu) {
		this.typeJeu = typeJeu;
	}

	public double getPrixMin() {
		return prixMin;
	}

	public void setPrixMin(double prixMin) {
		this.prixMin = prixMin;
	}

	public double getPrixMax() {
		return prixMax;
	}

	public void setPrixMax(double prixMax) {
		this.prixMax = prixMax;
	}

	
}
