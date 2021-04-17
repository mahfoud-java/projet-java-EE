package com.web.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.Beans.Epreuve;
import com.web.Beans.Tournoi;


public class EpreuveDaoImpl implements TennisDao<Epreuve> {

	public DaoFactory daoFactory;

	public EpreuveDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouter(Epreuve epreuve) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();

			statement = connexion.prepareStatement("INSERT INTO epreuve (ANNEE,TYPE_EPREUVE,ID_TOURNOI) VALUES (?,?,?)");
		
			statement.setLong(1, epreuve.getAnnee());
			statement.setString(2, String.valueOf(epreuve.getTypeEpreuve()));
			statement.setLong(3,epreuve.getIdTournoi());

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
	public List<Epreuve> lister() {
		List<Epreuve> lj = new ArrayList<Epreuve>();
		Connection connexion = null;
		Statement statement = null;
		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.createStatement();
			String query = "Select * from epreuve\r\n" + "Inner join tournoi\r\n"
					+ "on tournoi.ID = epreuve.ID_TOURNOI";
			

			ResultSet rs = statement.executeQuery(query);
		
			
			while (rs.next()) {

				Epreuve epreuve = new Epreuve();
				System.out.println("try dans while");
				epreuve.setId(rs.getLong("ID"));
				epreuve.setAnnee(rs.getLong("ANNEE"));
				epreuve.setTypeEpreuve(rs.getString("TYPE_EPREUVE"));
				Tournoi tournoi = new Tournoi();
				tournoi.setId(rs.getLong("tournoi.ID"));
				tournoi.setNom(rs.getString("NOM"));
				tournoi.setCode(rs.getString("CODE"));
				epreuve.setTournoi(tournoi);
				
				lj.add(epreuve);

				
			}

		} catch (Exception exception) {
			System.out.println("catch");
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
	public void modifier(Epreuve epreuve) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement("UPDATE epreuve SET ANNEE = ?, TYPE_EPREUVE = ?, ID_TOURNOI = ? WHERE ID = ? ");
			
			statement.setLong(1, epreuve.getAnnee());
			statement.setString(2, epreuve.getTypeEpreuve());
			statement.setLong(3, epreuve.getIdTournoi());
			statement.setLong(4, epreuve.getId());

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
			statement = connexion.prepareStatement("DELETE FROM epreuve_tennis WHERE ID =?");

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
	public Epreuve lecture(Long id) {
		Connection connexion = null;
		Statement statement = null;
		Epreuve epreuve = null;
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "SELECT * FROM tennis.epreuve WHERE ID=" + id;
			statement = connexion.createStatement();

			ResultSet rs = statement.executeQuery(strSql);

			if (rs.next()) {

				epreuve = new Epreuve();
				epreuve.setId(rs.getLong("ID"));
				epreuve.setIdTournoi(rs.getLong("ID_TOURNOI"));
				epreuve.setAnnee(rs.getLong("ANNEE"));
				epreuve.setTypeEpreuve(rs.getString("TYPE_EPREUVE"));

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
		return epreuve;
	}

	public List<Epreuve> rechercher(String chaine) {
		List<Epreuve> epreuves = new ArrayList<Epreuve>();
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			String strSql = "SELECT * FROM epreuve WHERE NOM LIKE ? OR CODE LIKE ? ";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Epreuve epreuve = new Epreuve();
				epreuve.setId(rs.getLong("ID"));
				epreuve.setIdTournoi(rs.getLong("ID_TOURNOI"));
				epreuve.setAnnee(rs.getLong("ANNEE"));
				epreuve.setTypeEpreuve(rs.getString("TYPE_EPREUVE"));
				
				

				epreuves.add(epreuve);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return epreuves;
	}
	
	public Long indexer(Epreuve epreuve) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement("Select ID from epreuve Where ANNEE = ? AND TYPE_EPREUVE = ? AND ID_TOURNOI = ?");
			statement.setLong(1, epreuve.getAnnee());
			statement.setString(2, String.valueOf(epreuve.getTypeEpreuve()));
			statement.setLong(3, epreuve.getIdTournoi());
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				
				return rs.getLong("ID");
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
		return null;
		
	}
	
	
}