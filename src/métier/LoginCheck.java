package métier;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

import dao.UtilisateurDao;
import entity.Utilisateur;

public class LoginCheck {
	

	
	private String erreur="";
	public static final String CHAMP_EMAIL = "email";
	public static final String CHAMP_PASSWORD = "password";
	public static final String ATT_UTILISATEUR_SESSION = "utilisateurConnecte";
	public static final String TEXT_WRONG_INPUT = "Nom d'utilisateur ou mot de passe incorrect, veuillez rÃ©essayer";
	
	public Utilisateur loginCheck(HttpServletRequest request, UtilisateurDao utilisateurDao) throws Exception
	{
		ConfigurablePasswordEncryptor passwordEncryptor2 = new ConfigurablePasswordEncryptor();
	    passwordEncryptor2.setAlgorithm( "SHA-256" );
	    passwordEncryptor2.setPlainDigest( false );
		
		String email = request.getParameter(CHAMP_EMAIL);
		String password = request.getParameter(CHAMP_PASSWORD);
		
		Utilisateur utilisateur=new Utilisateur();
		
		try
		{
			utilisateur = utilisateurDao.read(email);
			if (passwordEncryptor2.checkPassword(password,utilisateur.getMdp())) {
				return utilisateur;
				} else {
					erreur = TEXT_WRONG_INPUT;
				}

		}
		catch (Exception e)
		{
			throw new Exception(e);
		}

		return utilisateur;
	}
	
	public String getErreur()
	{
		return this.erreur;
	}
}
