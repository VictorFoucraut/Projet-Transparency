package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollaborateurDao;
import dao.DeclarationDao;
import métier.PrepareDashboard;

@WebServlet( "/Dashboard" )
public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	
	
	@EJB
	private DeclarationDao declarationDao;
	@EJB
	private CollaborateurDao collaborateurDao;	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		PrepareDashboard prepareDB = new PrepareDashboard();
		try {
			prepareDB.prepare(request, declarationDao, collaborateurDao);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/dashboard.jsp" ).forward( request, response );
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
		
		
	}
}
