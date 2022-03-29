package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_add")
public class MembreAddServlet extends HttpServlet{

	MembreService membreService = MembreService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nom = request.getParameter("nom");
			if(nom.compareTo("")==0) {
				nom=null;
			}
			String prenom = request.getParameter("prenom");
			if(prenom.compareTo("")==0) {
				prenom=null;
			}
			String adresse = request.getParameter("adresse");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			Membre membre = new Membre(nom, prenom, adresse, email, telephone);
			int id = membreService.create(membre);
			response.sendRedirect("/TP3Ensta/membre_details?id=" + id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(request, response);
		}
	}
}

