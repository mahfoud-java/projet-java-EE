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

import com.web.Beans.Epreuve;
import com.web.Beans.Joueur;
import com.web.Beans.Match;
import com.web.Beans.Tournoi;
import com.web.Dao.DaoException;
import com.web.Dao.DaoFactory;
import com.web.Dao.EpreuveDaoImpl;
import com.web.Dao.JoueurDaoImpl;
import com.web.Dao.MatchDaoImpl;
import com.web.Dao.TournoiDaoImpl;

@WebServlet("/modifiermatch")
public class ModifierMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MatchDaoImpl matchdaoimpl;
	JoueurDaoImpl joueurDaoImpl;
	EpreuveDaoImpl epreuveDaoImpl;
	private TournoiDaoImpl tournoiimpldao;
	Match match;
	Epreuve epreuve;
    public ModifierMatch() {
        super();
       
    }
    
    public void init() {
    	 matchdaoimpl = new MatchDaoImpl(DaoFactory.getInstance());
         joueurDaoImpl = new JoueurDaoImpl(DaoFactory.getInstance());
         epreuveDaoImpl = new EpreuveDaoImpl(DaoFactory.getInstance());
         tournoiimpldao = new TournoiDaoImpl(DaoFactory.getInstance());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login") == null) {
			response.sendRedirect("/tpWebBd6/login");
			return;
		}
		
		
		List<Tournoi> listeTournois = new ArrayList<>();
		listeTournois = tournoiimpldao.lister();
		
		request.setAttribute("listeTournois", listeTournois);
		
		
		
		List<Joueur> listeJoueurs = new ArrayList<>();
		try {
			listeJoueurs = joueurDaoImpl.lister();
		} catch (DaoException e) {
			
		}
		request.setAttribute("listeJoueurs", listeJoueurs);
		
		String id = request.getParameter("idmatch");
		
		match = matchdaoimpl.lecture(Long.parseLong(id));
		Joueur jF = joueurDaoImpl.lecture(match.getIdFinaliste());
		match.setJoueurF(jF);
		Joueur jV = joueurDaoImpl.lecture(match.getIdVainqueur());
		match.setJoueurV(jV);
		epreuve = epreuveDaoImpl.lecture(match.getIdEpreuve());
		Tournoi tournoi = tournoiimpldao.lecture(epreuve.getIdTournoi());
		epreuve.setTournoi(tournoi);
		match.setEpreuve(epreuve);
		

		if (request.getParameter("action").equals("modifier")) {
			request.setAttribute("match", match);
			request.getServletContext().getRequestDispatcher("/WEB-INF/modifiermatch.jsp").forward(request, response);
		} else {
			matchdaoimpl.supprimer(Long.parseLong(id));
			response.sendRedirect("/tpWebBd6/listermatch");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		match.setIdFinaliste(Long.valueOf(request.getParameter("idJoueurF")));
		match.setIdVainqueur(Long.valueOf(request.getParameter("idJoueurV")));

			epreuve.setAnnee(Long.valueOf(request.getParameter("txtAnnee")));
		
		epreuve.setTypeEpreuve(request.getParameter("txtType"));
		epreuve.setIdTournoi(Long.valueOf(request.getParameter("idTournoi")));
		epreuveDaoImpl.modifier(epreuve);
		
		matchdaoimpl.modifier(match);
		response.sendRedirect("/tpWebBd6/listermatch");
	}

}
