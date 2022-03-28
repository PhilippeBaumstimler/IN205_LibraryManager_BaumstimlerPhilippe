package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_return")
public class EmpruntReturnServlet extends HttpServlet{
	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			if(id!=null) {
				request.setAttribute("id",Integer.parseInt(id));
			}
			List<Emprunt> emprunts = this.empruntService.getListCurrent();
			List<Livre> livresEmprunt = new ArrayList<Livre>();
			List<Membre> membresEmprunt = new ArrayList<Membre>();
			for(int i=0; i<emprunts.size();i++) {
				livresEmprunt.add(this.livreService.getById(emprunts.get(i).getIdLivre()));
				membresEmprunt.add(this.membreService.getById(emprunts.get(i).getIdMembre()));
			}
			request.setAttribute("emprunts", emprunts);
			request.setAttribute("livresEmprunt", livresEmprunt);
			request.setAttribute("membresEmprunt", membresEmprunt);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			int ide = Integer.parseInt(id);
			Emprunt emprunt = this.empruntService.getById(ide);
			Emprunt e = new Emprunt(emprunt.getId(), emprunt.getIdMembre(), emprunt.getIdLivre(), emprunt.getDateEmprunt(), LocalDate.now());
			System.out.println(e.getId());
			System.out.println(e.getIdMembre());
			System.out.println(e.getIdLivre());
			System.out.println(e.getDateEmprunt());
			System.out.println(e.getDateRetour());
			this.empruntService.returnBook(e);
			response.sendRedirect("/TP3Ensta/emprunt_list");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.doGet(request, response);
		}
	}
}
