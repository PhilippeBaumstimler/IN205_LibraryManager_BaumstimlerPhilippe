package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;

@WebServlet("/livre_delete")
public class LivreDeleteServlet extends HttpServlet{
	
	LivreService livreService = LivreService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idlivre = request.getParameter("id");
			int idl = Integer.parseInt(idlivre);
			request.setAttribute("livre", this.livreService.getById(idl));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_delete.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idlivre = request.getParameter("id");
			int idl = Integer.parseInt(idlivre);
			this.livreService.delete(this.livreService.getById(idl));
			response.sendRedirect("/TP3Ensta/livre_list");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.doGet(request, response);
		}
	}
}
