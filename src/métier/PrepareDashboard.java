package métier;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.CollaborateurDao;
import dao.DeclarationDao;
import entity.Collaborateur;
import entity.Declaration;
import entity.Utilisateur;

public class PrepareDashboard {
	
	public void prepare(HttpServletRequest request, DeclarationDao declarationDao, CollaborateurDao collaborateurDao) throws Exception
	{
		List<Declaration> listDeclaration = declarationDao.getListOfAllActivDeclarations();
		HashMap<Utilisateur, Integer> utilisateurAnalyse = new HashMap<Utilisateur, Integer>();
		HashMap<Utilisateur, Integer> utilisateurRevision = new HashMap<Utilisateur, Integer>();
		
		int nbAAnalyser = 0;
		int nbAReviser = 0;
		int nbValidee = 0;
		
		int nbDeclarationAJour=0;
		
		int nbDeclaration = listDeclaration.size();
		
		for (int i=0;i<nbDeclaration;i++)
		{
			if (listDeclaration.get(i).getEtat().equals("à  analyser"))
				nbAAnalyser++;
			if (listDeclaration.get(i).getEtat().equals("à réviser"))
			{
				Utilisateur ut = listDeclaration.get(i).getAuteurAnalyse();
				if (ut!=null)
				{
					if (utilisateurAnalyse.get(ut) != null)
						utilisateurAnalyse.put(ut, utilisateurAnalyse.get(ut)+1);
					else
						utilisateurAnalyse.put(ut, 1);
				}
					
				nbAReviser++;
			}
			if (listDeclaration.get(i).getEtat().equals("validée"))
			{
				Utilisateur ut = listDeclaration.get(i).getAuteurRevision();
				if (ut!=null)
				{
					if (utilisateurRevision.get(ut) != null)
						utilisateurRevision.put(ut, utilisateurRevision.get(ut)+1);
					else
						utilisateurRevision.put(ut, 1);
				}
				
				nbValidee++;	
				boolean Ajour=true;
				if ( !(listDeclaration.get(i).getNbCollabErreur() == 0) )
					Ajour=false;
				
				List<Collaborateur> listCollaborateur = collaborateurDao.getListOfCollaborateurs(listDeclaration.get(i));
				for (int ii=0;ii<listCollaborateur.size();ii++)
				{
					if (!listCollaborateur.get(ii).getEtat().equals("à  jour"))
						Ajour=false;
				}
				
				if (Ajour)
					nbDeclarationAJour++;
			}
		}
		
		request.setAttribute( "nbAAnalyser", nbAAnalyser );
		request.setAttribute( "nbAReviser", nbAReviser );
		request.setAttribute( "nbValidee", nbValidee );
		request.setAttribute( "nbDeclaration", nbDeclaration );
		
		request.setAttribute( "nbDeclarationAJour", nbDeclarationAJour );
		
		request.setAttribute( "utilisateurAnalyse", utilisateurAnalyse );
		request.setAttribute( "utilisateurRevision", utilisateurRevision );
	}
}