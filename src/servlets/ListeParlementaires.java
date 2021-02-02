package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeclarationDao;
import entity.Declaration;

@WebServlet( "/ListeParlementaires" )
public class ListeParlementaires extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	
	
	@EJB
	private DeclarationDao declarationDao;
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		try {
			List<Declaration> listDeclaration = declarationDao.getListOfAllActivDeclarations();
			
			request.setAttribute( "listDeclaration", listDeclaration );
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/listeParlementaires.jsp" ).forward( request, response );
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}
}
