package com.web.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.Beans.Epreuve;
import com.web.Beans.Match;
import com.web.Beans.Tournoi;

public class MatchDaoImpl implements TennisDao<Match> {

	public DaoFactory daoFactory;

	public MatchDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouter(Match match) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();

			statement = connexion
					.prepareStatement("INSERT INTO match_tennis (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) VALUES (?,?,?)");
			statement.setLong(1, match.getIdEpreuve());
			statement.setLong(2, match.getIdVainqueur());
			statement.setLong(3, match.getIdFinaliste());

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
	public List<Match> lister() {
		List<Match> lj = new ArrayList<Match>();
		Connection connexion = null;
		Statement statement = null;
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "select * from match_tennis \r\n" + "inner join epreuve\r\n"
					+ "on match_tennis.ID_EPREUVE = epreuve.ID\r\n" + "inner join tournoi\r\n"
					+ "on epreuve.ID_TOURNOI = tournoi.ID\r\n";

			statement = connexion.createStatement();

			ResultSet rs = statement.executeQuery(strSql);

			while (rs.next()) {

				Match match = new Match();
				match.setId(rs.getLong("ID"));
				match.setIdEpreuve(rs.getLong("ID_EPREUVE"));
				match.setIdVainqueur(rs.getLong("ID_VAINQUEUR"));
				match.setIdFinaliste(rs.getLong("ID_FINALISTE"));
				Epreuve epreuve = new Epreuve();
				epreuve.setId(rs.getLong("epreuve.ID"));
				epreuve.setAnnee(rs.getLong("ANNEE"));
				epreuve.setTypeEpreuve(rs.getString("TYPE_EPREUVE"));
				Tournoi tournoi = new Tournoi();
				tournoi.setId(rs.getLong("tournoi.ID"));
				tournoi.setNom(rs.getString("tournoi.NOM"));
				tournoi.setCode(rs.getString("CODE"));

				epreuve.setTournoi(tournoi);
				match.setEpreuve(epreuve);

				lj.add(match);

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
	public void modifier(Match match) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement(
					"UPDATE match_tennis SET ID_FINALISTE = ?, ID_VAINQUEUR = ?, ID_EPREUVE = ? WHERE ID = ? ");

			statement.setLong(1, match.getIdFinaliste());
			statement.setLong(2, match.getIdVainqueur());
			statement.setLong(3, match.getIdEpreuve());
			statement.setLong(4, match.getId());

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
			statement = connexion.prepareStatement("DELETE FROM match_tennis WHERE ID =?");

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
	public Match lecture(Long id) {
		Connection connexion = null;
		Statement statement = null;
		Match match = null;
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "SELECT * FROM tennis.match_tennis WHERE ID=" + id;
			statement = connexion.createStatement();

			ResultSet rs = statement.executeQuery(strSql);

			if (rs.next()) {

				match = new Match();
				match.setId(rs.getLong("ID"));
				match.setIdEpreuve(rs.getLong("ID_EPREUVE"));
				match.setIdVainqueur(rs.getLong("ID_VAINQUEUR"));
				match.setIdFinaliste(rs.getLong("ID_FINALISTE"));

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
		return match;
	}

	public List<Match> rechercher(String chaine) {
		List<Match> matchs = new ArrayList<Match>();
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			String strSql = "SELECT * FROM match WHERE NOM LIKE ? OR CODE LIKE ? ";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Match match = new Match();
				match.setId(rs.getLong("ID"));
				match.setIdEpreuve(rs.getLong("ID_EPREUVE"));
				match.setIdVainqueur(rs.getLong("ID_VAINQUEUR"));
				match.setIdFinaliste(rs.getLong("ID_FINALISTE"));

				matchs.add(match);
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return matchs;
	}

}