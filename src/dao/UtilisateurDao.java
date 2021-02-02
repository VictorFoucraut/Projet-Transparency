package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Declaration;
import entity.Utilisateur;

@Stateless
public class UtilisateurDao {
	
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Utilisateur u";
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Utilisateur u WHERE u.id=:id";
	
	@PersistenceContext( unitName = "PU_Utilisateur" )
	private EntityManager em;
	
	public void create(Utilisateur utilisateur) throws DAOException {
		try
		{
			em.persist (utilisateur);
			
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public Utilisateur read(String email) throws DAOException {

		
		Utilisateur utilisateur = null;
		try
		{
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);
		requete.setParameter("email", email);
		requete.getSingleResult();
		utilisateur = (Utilisateur) requete.getSingleResult();
			
		}
		catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return utilisateur;
	}
	
	public Utilisateur read(int id) throws DAOException {

		Utilisateur utilisateur = null;
		try
		{
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter("id", id);
		requete.getSingleResult();
		utilisateur = (Utilisateur) requete.getSingleResult();
			
		}
		catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return utilisateur;
	}

	public List<Utilisateur> getListOfAllUtilisateurs() {
		List<Utilisateur> listUtilisateurs = new Vector<Utilisateur>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_ALL);
			listUtilisateurs = ((List<Utilisateur>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listUtilisateurs;
	}

	public void delete(Utilisateur utilisateurABannir) {
		try
		{
			if (!em.contains(utilisateurABannir))
			{
				utilisateurABannir = em.merge(utilisateurABannir);
			}
			em.remove(utilisateurABannir);
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
