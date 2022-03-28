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
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/emprunt_list")
public class EmpruntListServlet extends HttpServlet {
	
	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String show = request.getParameter("show");
			List<Livre> livresEmprunt = new ArrayList<Livre>();
			List<Membre> membresEmprunt = new ArrayList<Membre>();
			if(show==null) {
				List<Emprunt> emprunts = this.empruntService.getListCurrent();
				request.setAttribute("emprunts", emprunts);
				for(int i=0; i<emprunts.size();i++) {
					livresEmprunt.add(this.livreService.getById(emprunts.get(i).getIdLivre()));
					membresEmprunt.add(this.membreService.getById(emprunts.get(i).getIdMembre()));
				}
			}
			else if(show.compareTo("all")==0) {
				List<Emprunt> emprunts = this.empruntService.getList();
				request.setAttribute("emprunts", emprunts);
				for(int i=0; i<emprunts.size();i++) {
					livresEmprunt.add(this.livreService.getById(emprunts.get(i).getIdLivre()));
					membresEmprunt.add(this.membreService.getById(emprunts.get(i).getIdMembre()));
				}
			}
			request.setAttribute("livresEmprunt", livresEmprunt);
			request.setAttribute("membresEmprunt", membresEmprunt);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
