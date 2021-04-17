package com.web.sevlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.Beans.Match;
import com.web.Dao.DaoFactory;
import com.web.Dao.JoueurDaoImpl;
import com.web.Dao.MatchDaoImpl;

@WebServlet("/listermatch")
public class ListerMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchDaoImpl matchimpldao;
	private JoueurDaoImpl joueurimpldao;

	public ListerMatch() {
		super();

	}
	
	public void init() {
		matchimpldao = new MatchDaoImpl(DaoFactory.getInstance());
		joueurimpldao = new JoueurDaoImpl(DaoFactory.getInstance());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login") == null) {
			response.sendRedirect("/tpWebBd6/login");
			return;
		}
		
		List<Match> listeM = new ArrayList<>();

		listeM = matchimpldao.lister();

		for (Match match : listeM) {

			match.setJoueurF(joueurimpldao.lecture(match.getIdFinaliste()));
			match.setJoueurV(joueurimpldao.lecture(match.getIdVainqueur()));

		}

		request.setAttribute("listeMatchs", listeM);

		request.getServletContext().getRequestDispatcher("/WEB-INF/listermatch.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
