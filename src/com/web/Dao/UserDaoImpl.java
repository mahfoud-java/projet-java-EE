package com.web.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.web.Beans.User;

public class UserDaoImpl {
	private DaoFactory daoFactory;

	public UserDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public User isValideLogin(String login, String password) {
		Connection connexion = null;
		PreparedStatement statement = null;
		password = HashClass.sha1(password);
		try {
			connexion = daoFactory.getConnexion();

			String strSql = "SELECT * FROM tennis.connexion WHERE login=? AND password=?";
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {

				User user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setProfil(rs.getInt("profil"));
				return user;
			} else {
				return null;
			}

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
			}
		}

	}

}
