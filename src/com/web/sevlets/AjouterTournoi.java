package com.web.sevlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.Beans.BeanException;
import com.web.Beans.Tournoi;
import com.web.Dao.DaoFactory;
import com.web.Dao.TournoiDaoImpl;

@WebServlet("/ajoutertournoi")
public class AjouterTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TournoiDaoImpl tournoidaoimpl;
    public AjouterTournoi() {
        super();
       
    }
    
    public void init() {
    	 tournoidaoimpl = new TournoiDaoImpl(DaoFactory.getInstance());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login") == null) {
			response.sendRedirect("/tpWebBd6/login");
			return;
		}
		request.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String nom = request.getParameter("txtNom");
				String code = request.getParameter("txtCode");
				Tournoi tournoi = new Tournoi();
				try {
					tournoi.setNom(nom);
					tournoi.setCode(code);
					tournoidaoimpl.ajouter(tournoi);
					response.sendRedirect("/tpWebBd6/listertournoi");
				} catch (BeanException e) {
					request.setAttribute("erreur", e.getMessage());
					request.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
				}
				
				
	}

}
