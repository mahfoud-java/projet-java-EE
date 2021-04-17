package com.web.Beans;

public class Tournoi {
	
	private Long id;
	private String nom;
	private String code;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws BeanException {
		if(nom.length() > 20)
			throw new BeanException("Le nom du tournoi est trop grand (20 caracteres max !)");
		else
		this.nom = nom;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) throws BeanException {
		if(code.length() > 2)
			throw new BeanException("Le code tournoi ne peut contenir que 2 caractères maximum ! ");
		else
		this.code= code;
	}

}
