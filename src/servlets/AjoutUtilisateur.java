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
import métier.AjoutUtilisateurCheck;

@WebServlet( "/AjoutUtilisateur" )
public class AjoutUtilisateur extends HttpServlet {

	@EJB
	private UtilisateurDao utilisateurDao;
	
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/espaceAdmin.jsp";
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	public static final String ATT_ERREUR_CONNEXION = "erreurAttribute";
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		try 
		{			
			List<Utilisateur> listUtilisateur = utilisateurDao.getListOfAllUtilisateurs();		
	
			request.setAttribute( "listUtilisateur", listUtilisateur );
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/espaceAdmin.jsp" ).forward( request, response );
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
		
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		AjoutUtilisateurCheck ajoutUtilisateurCheck = new AjoutUtilisateurCheck();
		
		try 
		{
			Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			if (utilisateurConnecte.getDroit() != 3)
				throw new Exception();	
			
			ajoutUtilisateurCheck.ajoutUtilisateurCheck(request,utilisateurDao);
			if (ajoutUtilisateurCheck.getErreur().isEmpty())
			{
				doGet(request,response);
			}
			else
			{
				request.setAttribute(ATT_ERREUR_CONNEXION, ajoutUtilisateurCheck.getErreur());
				doGet(request,response);
			}
			
		} catch (Exception e)
		{
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}	
}
