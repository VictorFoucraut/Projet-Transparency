package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter( urlPatterns = "/*" )
public class FiltreUtilisateurLogue implements Filter {
	
	public static final String ATT_UTILISATEUR_SESSION = "utilisateurConnecte";
	public static final String ACCES_CONNEXION  = "/Login";

	
    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
    	
        // Cast des objets request et response 
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Récupération de la session depuis la requête 
        HttpSession session = request.getSession();

        
        /* Non-filtrage des ressources statiques */
        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin.equals( "/Accueil" ) ) {
            chain.doFilter( request, response );
            return;
        }
        
        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_UTILISATEUR_SESSION ) == null ) {
            // Redirection vers la page publique 
        	request.getRequestDispatcher( ACCES_CONNEXION ).forward( request, response );
        } else {
            // Affichage de la page restreinte 
            chain.doFilter( request, response );
        }
    }
    	
    

    public void destroy() {
    }
}