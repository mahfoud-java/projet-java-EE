package com.web.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.Beans.Joueur;

public class JoueurDaoImpl implements TennisDao<Joueur> {

	public DaoFactory daoFactory;

	public JoueurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouter(Joueur joueur) throws com.web.Dao.DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();

			statement = connexion.prepareStatement("INSERT INTO joueur (NOM,PRENOM,SEXE) VALUES (?,?,?)");
			statement.setString(1, joueur.getNom());
			statement.setString(2, joueur.getPrenom());
			statement.setString(3, joueur.getSexe());
			statement.executeUpdate();

		} catch (Exception exception) {
			throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
	}

	@Override
	public List<Joueur> lister() throws com.web.Dao.DaoException {
		List<Joueur> lj = new ArrayList<Joueur>();
		Connection connexion = null;
		Statement statement = null;
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "SELECT * FROM tennis.joueur";
			statement = connexion.createStatement();

			ResultSet rs = statement.executeQuery(strSql);

			while (rs.next()) {

				Joueur joueur = new Joueur();
				joueur.setId(rs.getInt("ID"));
				joueur.setNom(rs.getString("NOM"));
				joueur.setPrenom(rs.getString("PRENOM"));
				joueur.setSexe(rs.getString("SEXE"));
				lj.add(joueur);

			}

		} catch (Exception exception) {
			throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
		return lj;
	}

	@Override
	public void modifier(Joueur joueur) throws com.web.Dao.DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement("UPDATE joueur SET NOM=?, PRENOM = ?, SEXE = ? WHERE ID =?");
			statement.setString(1, joueur.getNom());
			statement.setString(2, joueur.getPrenom());
			statement.setString(3, joueur.getSexe());
			statement.setLong(4, joueur.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
	}

	@Override
	public void supprimer(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement("DELETE FROM joueur WHERE ID =?");

			statement.setLong(1, id);

			int a = statement.executeUpdate();
			System.out.println(a);
		} catch (SQLException e) {
			
		} finally {
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException e) {
				
			}
		}

	}

	@Override
	public Joueur lecture(Long id) {
		Connection connexion = null;
		Statement statement = null;
		Joueur joueur = null;
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "SELECT * FROM tennis.joueur WHERE ID=" + id;
			statement = connexion.createStatement();

			ResultSet rs = statement.executeQuery(strSql);

			if (rs.next()) {

				joueur = new Joueur();
				joueur.setId(rs.getInt("ID"));
				joueur.setNom(rs.getString("NOM"));
				joueur.setPrenom(rs.getString("PRENOM"));
				joueur.setSexe(rs.getString("SEXE"));

			}

		} catch (Exception exception) {
	
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
		
			}
		}
		return joueur;
	}

	public List<Joueur> rechercher(String chaine){
		List<Joueur> joueurs = new ArrayList<Joueur>();
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			String strSql = "SELECT * FROM joueur WHERE NOM LIKE ? OR PRENOM LIKE ? ";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Joueur joueur = new Joueur();
				joueur.setId(rs.getInt("ID"));
				joueur.setNom(rs.getString("NOM"));
				joueur.setPrenom(rs.getString("PRENOM"));
				joueur.setSexe(rs.getString("SEXE"));
				joueurs.add(joueur);
			}
		} catch (Exception exception) {
		}
		return joueurs;
	}

	
}