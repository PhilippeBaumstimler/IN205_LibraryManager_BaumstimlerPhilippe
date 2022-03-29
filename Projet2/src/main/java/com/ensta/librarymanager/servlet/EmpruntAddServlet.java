package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_add")
public class EmpruntAddServlet extends HttpServlet{

	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("livresDispo", this.livreService.getListDispo());
			request.setAttribute("membresDispo", this.membreService.getListMembreEmpruntPossible());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idmembre = Integer.parseInt(request.getParameter("idMembre"));
			int idlivre = Integer.parseInt(request.getParameter("idLivre"));
			Emprunt emprunt = new Emprunt(idmembre, idlivre, LocalDate.now(), null);
			this.empruntService.create(emprunt);
			response.sendRedirect("/TP3Ensta/emprunt_list");
		}catch(ServiceException e) {
			e.printStackTrace();
			this.doGet(request, response);
		}
	}
}
