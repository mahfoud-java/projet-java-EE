package com.web.sevlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.Beans.Epreuve;
import com.web.Dao.DaoFactory;
import com.web.Dao.EpreuveDaoImpl;

@WebServlet("/ListerEpreuve")
public class ListerEpreuve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EpreuveDaoImpl epreuveDaoImpl; 
    public ListerEpreuve() {
    	super();
    }
    
    public void init() {
    	epreuveDaoImpl = new EpreuveDaoImpl(DaoFactory.getInstance());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Epreuve> listeE = new ArrayList<>();
		listeE = epreuveDaoImpl.lister();
		request.setAttribute("listeEpreuves", listeE);
		request.getServletContext().getRequestDispatcher("/WEB-INF/listerepreuve.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
