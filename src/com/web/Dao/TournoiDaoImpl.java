package com.web.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.Beans.Tournoi;

public class TournoiDaoImpl implements TennisDao<Tournoi> {

	public DaoFactory daoFactory;

	public TournoiDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouter(Tournoi tournoi) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();

			statement = connexion.prepareStatement("INSERT INTO tournoi (NOM,CODE) VALUES (?,?)");
			statement.setString(1, tournoi.getNom());
			statement.setString(2, tournoi.getCode());

			statement.executeUpdate();

		} catch (Exception exception) {

		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {

			}
		}
	}

	@Override
	public List<Tournoi> lister() {
		List<Tournoi> lj = new ArrayList<Tournoi>();
		Connection connexion = null;
		Statement statement = null;
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "SELECT * FROM tennis.tournoi";
			statement = connexion.createStatement();

			ResultSet rs = statement.executeQuery(strSql);

			while (rs.next()) {

				Tournoi tournoi = new Tournoi();
				tournoi.setId(rs.getLong("ID"));
				tournoi.setNom(rs.getString("NOM"));
				tournoi.setCode(rs.getString("CODE"));

				lj.add(tournoi);

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
		return lj;
	}

	@Override
	public void modifier(Tournoi tournoi) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement("UPDATE tournoi SET NOM=?, CODE = ? WHERE ID =?");
			statement.setString(1, tournoi.getNom());
			statement.setString(2, tournoi.getCode());
			statement.setLong(3, tournoi.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void supprimer(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement("DELETE FROM tournoi WHERE ID =?");

			statement.setLong(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Tournoi lecture(Long id) {
		Connection connexion = null;
		Statement statement = null;
		Tournoi tournoi = null;
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "SELECT * FROM tennis.tournoi WHERE ID=" + id;
			statement = connexion.createStatement();

			ResultSet rs = statement.executeQuery(strSql);

			if (rs.next()) {

				tournoi = new Tournoi();
				tournoi.setId(rs.getLong("ID"));
				tournoi.setNom(rs.getString("NOM"));
				tournoi.setCode(rs.getString("CODE"));

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
		return tournoi;
	}

	public List<Tournoi> rechercher(String chaine) {
		List<Tournoi> tournois = new ArrayList<Tournoi>();
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			String strSql = "SELECT * FROM tournoi WHERE NOM LIKE ? OR CODE LIKE ? ";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Tournoi tournoi = new Tournoi();
				tournoi.setId(rs.getLong("ID"));
				tournoi.setNom(rs.getString("NOM"));
				tournoi.setCode(rs.getString("CODE"));

				tournois.add(tournoi);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return tournois;
	}

}