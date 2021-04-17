package com.web.Beans;

public class Joueur {
	
	private int id;
	private String nom;
	private String prenom;
	private String sexe;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws com.web.Beans.BeanException{
		if(nom.length() > 20)
			throw new BeanException("Le nom est trop grand (20 caracteres max !)");
		else
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) throws com.web.Beans.BeanException {
		if(prenom.length() > 20)
			throw new BeanException("Le prénom est trop grand (20 caracteres max !)");
		else
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	
}
