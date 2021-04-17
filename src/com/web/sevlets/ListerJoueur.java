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

import com.web.Beans.Joueur;
import com.web.Dao.DaoException;
import com.web.Dao.DaoFactory;
import com.web.Dao.JoueurDaoImpl;

@WebServlet("/listerjoueur")
public class ListerJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDaoImpl joueurimpldao;
    public ListerJoueur() {
    	
    }

    public void init() {
    	joueurimpldao = new JoueurDaoImpl(DaoFactory.getInstance());
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login") == null) {
			response.sendRedirect("/tpWebBd6/login");
			return;
		}
		
		
		
		List<Joueur> listeJ = new ArrayList<>();
		try {
			listeJ = joueurimpldao.lister();
		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		}
		
		request.setAttribute("listeJoueurs", listeJ);
		request.getServletContext().getRequestDispatcher("/WEB-INF/listerjoueur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Joueur> listeJ = new ArrayList<>();
		String parameter = request.getParameter("action1");
		HttpSession session = request.getSession();
		
		if(parameter.equals("Rechercher")) {
			listeJ = joueurimpldao.rechercher(request.getParameter("txtsearch"));
			
			if(listeJ.size() == 0) {
				request.setAttribute("occurence", 0);
			}
			
			request.setAttribute("listeJoueurs", listeJ);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listerjoueur.jsp").forward(request, response);
		}
		else if(parameter.equals("Deconnexion")) {
			
			session.invalidate();
			response.sendRedirect("/tpWebBd6/login");
		}
		
	}

}
