package com.web.sevlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.Beans.User;
import com.web.Dao.DaoFactory;
import com.web.Dao.UserDaoImpl;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userdaoimpl;
  
    public Login() {
       
    	super();
    }

    public void init() {
    	userdaoimpl = new UserDaoImpl(DaoFactory.getInstance());
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies(); if(cookies != null) { 
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("login")) {
					request.setAttribute("login", cookie.getValue()); 
				}
				if(cookie.getName().equals("pass")) {
					request.setAttribute("pass", cookie.getValue()); 
				}
			} 
		}

		
		request.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userdaoimpl.isValideLogin(request.getParameter("txtLogin"), request.getParameter("txtPassword"));
		
		
		
		
		String login = request.getParameter("txtLogin");
		String pass = request.getParameter("txtPassword");
		  Cookie cookie1 = new Cookie("login", login);
		  cookie1.setMaxAge(60*60*24*30);
		  response.addCookie(cookie1);
		  
		  Cookie cookie2 = new Cookie("pass", pass);
		  cookie2.setMaxAge(60*60*24*30);
		  response.addCookie(cookie2);
		 
		
		if(user != null) {
			HttpSession session = request.getSession(true);
			
			session.setAttribute("login", login);
			response.sendRedirect("/tpWebBd6/listerjoueur");
			return;
		}
		this.doGet(request, response);
	}

}
