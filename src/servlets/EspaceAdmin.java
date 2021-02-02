package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UtilisateurDao;
import entity.Utilisateur;

@WebServlet( "/EspaceAdmin" )
public class EspaceAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	
	@EJB
	private UtilisateurDao utilisateurDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		try {
			Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			if (utilisateurConnecte.getDroit() != 3)
				throw new Exception();	
			
			List<Utilisateur> listUtilisateur = utilisateurDao.getListOfAllUtilisateurs();		
			request.setAttribute( "listUtilisateur", listUtilisateur );
			this.getServletContext().getRequestDispatcher( "/WEB-INF/espaceAdmin.jsp" ).forward( request, response );
		
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}
}