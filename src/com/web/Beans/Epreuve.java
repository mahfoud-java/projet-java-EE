package com.web.Beans;

public class Epreuve {

	private Long id;
	private Long annee;
	private String typeEpreuve;
	private Long idTournoi;
	
	private Tournoi tournoi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAnnee() {
		return annee;
	}

	public void setAnnee(Long annee) {
	
		this.annee= annee;
	}



	public Tournoi getTournoi() {
		return tournoi;
	}

	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	public Long getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(Long idTournoi) {
		this.idTournoi = idTournoi;
	}

	public String getTypeEpreuve() {
		return typeEpreuve;
	}

	public void setTypeEpreuve(String typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}



	
	
	

	
}
