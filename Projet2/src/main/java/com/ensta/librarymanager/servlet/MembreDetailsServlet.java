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
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_details")
public class MembreDetailsServlet extends HttpServlet {

	
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	LivreService livreService = LivreService.getInstance();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			String idmembre = request.getParameter("id");
			int idm = Integer.parseInt(idmembre);
			request.setAttribute("membre", this.membreService.getById(idm));
			List<Emprunt> emprunts = this.empruntService.getListCurrentByMembre(idm);
			List<Livre> livres = new ArrayList<Livre>();
			request.setAttribute("emprunts", emprunts);
			for(int i=0; i<emprunts.size();i++) {
				livres.add(this.livreService.getById(emprunts.get(i).getIdLivre()));
			}
			request.setAttribute("livres", livres);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("membre", this.membreService.getById(id));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adresse = request.getParameter("adresse");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			Abonnement abonnement = Abonnement.valueOf(request.getParameter("abonnement"));
			Membre membre = new Membre(id, nom, prenom, adresse, email, telephone, abonnement);
			this.membreService.update(membre);
			this.doGet(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
