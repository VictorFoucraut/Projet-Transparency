package métier;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import dao.UtilisateurDao;
import entity.Utilisateur;

public class AjoutUtilisateurCheck {

	private String erreur="";
	public static final String TEXT_INFO_MANQUANTE = "Veuillez renseigner tous les champs pour ajouter un utilisateur.";
	
	public Utilisateur ajoutUtilisateurCheck(HttpServletRequest request, UtilisateurDao utilisateurDao) throws Exception
	{
		try {
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

			
			Utilisateur utilisateur=new Utilisateur();
			
			Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateurConnecte");
			if (utilisateurConnecte.getDroit() != 3)
				throw new Exception();
			
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String mdp = request.getParameter("mdp");
			String droitString = request.getParameter("droit");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			int droit=0;
	
			if (droitString.equals("Simple utilisateur"))
				droit = 1;
			if (droitString.equals("Utilisateur avance"))
				droit = 2;
			
			if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || droit==0 )
				erreur = TEXT_INFO_MANQUANTE;
			
			ConfigurablePasswordEncryptor passwordEncryptor2 = new ConfigurablePasswordEncryptor();
		    passwordEncryptor2.setAlgorithm( "SHA-256" );
		    passwordEncryptor2.setPlainDigest( false );
		    String motDePasseChiffre = passwordEncryptor2.encryptPassword( mdp );

			
			if (erreur.isEmpty())
			{
				
				utilisateur.setNom(prenom);
				utilisateur.setPrenom(prenom);
				utilisateur.setEmail(email);
				utilisateur.setMdp(motDePasseChiffre);
				utilisateur.setDroit(droit);
				utilisateur.setDateInscription(timestamp);
	
				utilisateurDao.create(utilisateur);
			}
			return utilisateur;
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