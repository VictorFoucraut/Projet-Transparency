package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtilisateurDao;
import entity.Utilisateur;
import métier.LoginCheck;

@WebServlet( "/Login" )
public class Login extends HttpServlet {

	@EJB
	private UtilisateurDao utilisateurDao;
	
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/login.jsp";
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	public static final String REDIRECTION = "/TransparencyProject/ListeParlementaires";
	public static final String ATT_UTILISATEUR_SESSION = "utilisateurConnecte";
	public static final String ATT_ERREUR_CONNEXION = "erreurAttribute";
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		LoginCheck loginCheck = new LoginCheck();
		Utilisateur utilisateur = new Utilisateur();
		
		try 
		{
			utilisateur = loginCheck.loginCheck(request,utilisateurDao);
			if (loginCheck.getErreur().isEmpty())
			{
				HttpSession session = request.getSession();
				session.setAttribute(ATT_UTILISATEUR_SESSION, utilisateur);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/accueil.jsp" ).forward( request, response );
			}
			else
			{
				request.setAttribute(ATT_ERREUR_CONNEXION, loginCheck.getErreur());
				this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}	
}
