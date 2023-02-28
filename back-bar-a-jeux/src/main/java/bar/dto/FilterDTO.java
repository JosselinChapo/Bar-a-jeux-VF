package bar.dto;

public class FilterDTO {
	private int nbJoueur;
	private int dureeMin;
	private int dureeMax;
	private String typeJeu;
	
	
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
	
	
}
