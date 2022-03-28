package com.ensta.librarymanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/livre_add")
public class LivreAddServlet extends HttpServlet{
	
	LivreService livreService = LivreService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_add.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String titre = request.getParameter("titre");
			if(titre.compareTo("")==0) {
				titre=null;
			}
			String auteur = request.getParameter("auteur");
			String isbn = request.getParameter("isbn");
			Livre livre = new Livre(titre, auteur, isbn);
			int id = this.livreService.create(livre);
			response.sendRedirect("/TP3Ensta/livre_details?id=" + id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.doGet(request, response);
		}
	}
}
