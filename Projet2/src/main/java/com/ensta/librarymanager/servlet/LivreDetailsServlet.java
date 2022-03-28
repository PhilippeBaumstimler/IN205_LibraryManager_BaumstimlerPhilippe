package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/livre_details")
public class LivreDetailsServlet extends HttpServlet{
	
	LivreService livreService = LivreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	MembreService membreService = MembreService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			String idlivre = request.getParameter("id");
			int idl = Integer.parseInt(idlivre);
			request.setAttribute("livre", this.livreService.getById(idl));
			List<Emprunt> emprunts = this.empruntService.getListCurrentByLivre(idl);
			List<Membre> membres = new ArrayList<Membre>();
			request.setAttribute("emprunts", emprunts);
			for(int i=0; i<emprunts.size();i++) {
				membres.add(this.membreService.getById(emprunts.get(i).getIdMembre()));
			}
			request.setAttribute("membres", membres);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_details.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
