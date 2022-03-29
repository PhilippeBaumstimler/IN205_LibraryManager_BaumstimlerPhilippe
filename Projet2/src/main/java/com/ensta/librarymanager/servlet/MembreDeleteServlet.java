package com.ensta.librarymanager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.MembreService;

@WebServlet("/membre_delete")
public class MembreDeleteServlet extends HttpServlet{

	MembreService membreService = MembreService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	
			String idmembre = request.getParameter("id");
			int idm = Integer.parseInt(idmembre);
			request.setAttribute("membre", this.membreService.getById(idm));
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_delete.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idmembre = request.getParameter("id");
			int idm = Integer.parseInt(idmembre);
			this.membreService.delete(membreService.getById(idm));
			response.sendRedirect("/TP3Ensta/membre_list");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.doGet(request, response);
		}
	}
}
