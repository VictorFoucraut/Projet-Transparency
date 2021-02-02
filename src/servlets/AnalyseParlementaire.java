package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollaborateurDao;
import dao.DeclarationDao;
import entity.Collaborateur;
import entity.Declaration;
import métier.AnalyseParlementaireCheck;

@WebServlet( "/AnalyseParlementaire" )
public class AnalyseParlementaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	public static final String  ATT_ERREUR = "erreurAttribute";
	
	@EJB
	private DeclarationDao declarationDao;
	
	@EJB
	private CollaborateurDao collaborateurDao;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String uuid = request.getParameter("uuid");
		
		Declaration declaration = declarationDao.read(uuid);
		if (declaration == null)
			throw new Exception("Le parlementaire ayant pour uuid : "+uuid+" est introuvable");
		
		List<Collaborateur> listeCollaborateurs = collaborateurDao.getListOfCollaborateurs(declaration);
		
		request.setAttribute( "declaration", declaration );
		request.setAttribute( "listeCollaborateurs", listeCollaborateurs );
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/analyseParlementaire.jsp" ).forward( request, response );
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AnalyseParlementaireCheck analyseParlementaireCheck = new AnalyseParlementaireCheck();
		try 
		{
			analyseParlementaireCheck.analyseParlementaireCheck(request,declarationDao,collaborateurDao);
			if (analyseParlementaireCheck.getErreur().isEmpty())
				response.sendRedirect("/TransparencyProject/ListeParlementaires");
			else
			{
				request.setAttribute(ATT_ERREUR, analyseParlementaireCheck.getErreur());
				doGet(request,response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}

}
