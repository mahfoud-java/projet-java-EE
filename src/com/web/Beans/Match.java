package com.web.Beans;

public class Match {
	
	private Long id;
	private Long idEpreuve;
	private Long idFinaliste;
	private Long idVainqueur;
	private Epreuve epreuve;
	private Joueur joueurV;
	private Joueur joueurF;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdEpreuve() {
		return idEpreuve;
	}
	public void setIdEpreuve(Long idEpreuve) {
		this.idEpreuve = idEpreuve;
	}
	public Long getIdFinaliste() {
		return idFinaliste;
	}
	public void setIdFinaliste(Long idFinaliste) {
		this.idFinaliste = idFinaliste;
	}
	public Long getIdVainqueur() {
		return idVainqueur;
	}
	public void setIdVainqueur(Long idVainqueur) {
		this.idVainqueur = idVainqueur;
	}
	public Epreuve getEpreuve() {
		return epreuve;
	}
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	public Joueur getJoueurV() {
		return joueurV;
	}
	public void setJoueurV(Joueur joueurV) {
		this.joueurV = joueurV;
	}
	public Joueur getJoueurF() {
		return joueurF;
	}
	public void setJoueurF(Joueur joueurF) {
		this.joueurF = joueurF;
	}

}