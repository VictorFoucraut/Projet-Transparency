package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActiviteConsultantDao;
import dao.ActiviteProfDao;
import dao.CollaborateurDao;
import dao.DeclarationDao;
import dao.FonctionBenevoleDao;
import dao.MandatElectifDao;
import dao.ObservationInteretDao;
import dao.ParticipationDirigeantDao;
import dao.ParticipationFinanciereDao;
import entity.Utilisateur;
import métier.DoUpdateDatabase;


@WebServlet( "/UpdateDatabase" )
public class UpdateDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_EXCEPTION = "/WEB-INF/exception.jsp";
	
	@EJB
	private DeclarationDao declarationDao;
	@EJB
	private CollaborateurDao collaborateurDao;
	@EJB
	private ActiviteConsultantDao activiteConsultantDao;
	@EJB
	private ActiviteProfDao activiteProfDao;
	@EJB
	private FonctionBenevoleDao fonctionBenevoleDao;
	@EJB
	private MandatElectifDao mandatElectifDao;
	@EJB
	private ObservationInteretDao observationInteretDao;
	@EJB
	private ParticipationDirigeantDao participationDirigeantDao;
	@EJB
	private ParticipationFinanciereDao participationFinanciereDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
		try {
		if (utilisateurConnecte.getDroit() != 3)
			throw new Exception();
		DoUpdateDatabase updateDatabase = new DoUpdateDatabase(declarationDao,collaborateurDao, activiteConsultantDao, activiteProfDao, fonctionBenevoleDao, mandatElectifDao, observationInteretDao, participationDirigeantDao, participationFinanciereDao);
		updateDatabase.doUpdateDatabase();
		}
		catch (Exception e){
			e.printStackTrace();
			request.setAttribute("e", e);
			this.getServletContext().getRequestDispatcher( VUE_EXCEPTION ).forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
