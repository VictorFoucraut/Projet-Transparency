package m�tier;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.CollaborateurDao;
import dao.DeclarationDao;
import entity.Collaborateur;
import entity.Declaration;
import entity.Utilisateur;

public class AnalyseParlementaireCheck {

	private String erreur="";
	public static final String CHAMP_NB_COLLAB_ERREUR = "nbCollabErreur";
	public static final String CHAMP_COMMENTAIRE = "commentaire";
	public static final String TEXT_WRONG_INPUT = "Une erreur est survenue lors de l'enregistrement de l'analyse en base de donnée, les informations n'ont pas été enregistrées. Vérifiez que tous les champs soient bien remplis";
	
	public void analyseParlementaireCheck(HttpServletRequest request, DeclarationDao declarationDao, CollaborateurDao collaborateurDao) throws Exception
	{
		try {
		String enregistrer = request.getParameter("Enregistrer");
		String valider = request.getParameter("Valider");
		String signaler = request.getParameter("Signaler");
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		String uuid =  request.getParameter("uuid");
		Declaration declarationToUpdate = declarationDao.read(uuid);
		List<Collaborateur> listeCollaborateurs = collaborateurDao.getListOfCollaborateurs(declarationToUpdate);
		

		
		if (enregistrer!=null)
		{
			String nbCollabErreur = request.getParameter(CHAMP_NB_COLLAB_ERREUR);
			String commentaire = request.getParameter(CHAMP_COMMENTAIRE);
			
			for (int i=0;i<listeCollaborateurs.size();i++)
			{
				String selectionID = "selection"+listeCollaborateurs.get(i).getId();
				String selectionMenu = request.getParameter(selectionID);
				if (selectionMenu.equals("-"))
				{
					erreur = "Veuillez choisir une valeur dans chaque menu déroulant.";
					break;
				}
				listeCollaborateurs.get(i).setEtat(selectionMenu);
			}
			
			int nbCollabErreurInt = Integer.parseInt(nbCollabErreur);
			
			declarationToUpdate.setNbCollabErreur(nbCollabErreurInt);
			declarationToUpdate.setCommentaire(commentaire);
			declarationToUpdate.setEtat("à réviser");
			declarationToUpdate.setAuteurAnalyse(utilisateur);
			declarationToUpdate.setDateModif(timestamp);
		}
		
		else if (valider != null)
		{
			declarationToUpdate.setEtat("validée");
			declarationToUpdate.setAuteurRevision(utilisateur);
			declarationToUpdate.setDateModif(timestamp);
		}
		
		else if (signaler != null)
		{
			declarationToUpdate.setCommentaire(null);
			declarationToUpdate.setEtat("à analyser");
			declarationToUpdate.setNbCollabErreur(null);
			for (int i=0;i<listeCollaborateurs.size();i++)
			{
				listeCollaborateurs.get(i).setEtat("à analyser");
			}
			declarationToUpdate.setAuteurAnalyse(null);
			declarationToUpdate.setAuteurRevision(null);
			declarationToUpdate.setDateModif(timestamp);
		}
		
		if (erreur.isEmpty())
		{
			declarationDao.update(declarationToUpdate);
			for (int i=0;i<listeCollaborateurs.size();i++)
			{
				collaborateurDao.update(listeCollaborateurs.get(i));
			}
		}
		
		}
		catch (NumberFormatException e)
		{
			erreur = "Veuillez entrez un nombre entier pour le nombre de collaborateur n'apparaissant pas dans la déclaration.";
		}
		catch (Exception e){
			throw new Exception(e);
		}
		
	}
	
	public String getErreur()
	{
		return this.erreur;
	}
}

