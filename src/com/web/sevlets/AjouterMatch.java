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

@WebServlet("/ajoutermatch")
public class AjouterMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDaoImpl joueurimpldao;
	private EpreuveDaoImpl epreuveimpldao;
	private TournoiDaoImpl tournoiimpldao;
	private MatchDaoImpl matchimpldao;
    public AjouterMatch() {
    	super();
    }
    
    public void init() {
    	joueurimpldao = new JoueurDaoImpl(DaoFactory.getInstance());
    	epreuveimpldao = new EpreuveDaoImpl(DaoFactory.getInstance());
    	tournoiimpldao = new TournoiDaoImpl(DaoFactory.getInstance());
    	matchimpldao = new MatchDaoImpl(DaoFactory.getInstance());
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
		
		
		
		
		try {
			List<Joueur> listeJoueurs = new ArrayList<>();
			listeJoueurs = joueurimpldao.lister();
			request.setAttribute("listeJoueurs", listeJoueurs);
		} catch (DaoException e) {
			
		}
		
		
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/ajoutermatch.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Epreuve epreuve = new Epreuve();
		Match match = new Match();
	
			epreuve.setAnnee(Long.valueOf(request.getParameter("txtAnnee")));
		
		epreuve.setTypeEpreuve(request.getParameter("txtType"));
		epreuve.setIdTournoi(Long.valueOf(request.getParameter("idTournoi")));
		epreuveimpldao.ajouter(epreuve);
		
		match.setIdEpreuve(epreuveimpldao.indexer(epreuve));

		
	
		match.setIdFinaliste(Long.valueOf(request.getParameter("idFinaliste")));
		match.setIdVainqueur(Long.valueOf(request.getParameter("idVainqueur")));
		matchimpldao.ajouter(match);
	
		
		response.sendRedirect("/tpWebBd6/listermatch");
	}

}
