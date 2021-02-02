package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UtilisateurDao;
import entity.Utilisateur;


@WebServlet( "/Bannissement" )
public class Bannissement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	public static final String  ATT_ERREUR = "erreurAttr";
	
	@EJB
	private UtilisateurDao utilisateurDao;	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
		if (utilisateurConnecte.getDroit() != 3)
			throw new Exception();
		String idBan = request.getParameter("id");
		
		Utilisateur utilisateurABannir = utilisateurDao.read(Integer.parseInt(idBan));
		utilisateurDao.delete(utilisateurABannir);
		
		response.sendRedirect("/TransparencyProject/EspaceAdmin");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}

}