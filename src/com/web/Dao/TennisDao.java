package com.web.Dao;

import java.util.List;

public interface TennisDao<T> {
	void ajouter(T val) throws DaoException;

	List<T> lister() throws DaoException;

	void modifier(T val) throws DaoException;

	void supprimer(Long id);

	T lecture(Long id);

	List<T> rechercher(String chaine);
}
