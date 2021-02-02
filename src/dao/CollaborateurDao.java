package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Collaborateur;
import entity.Declaration;

@Stateless
public class CollaborateurDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT c FROM Collaborateur c where c.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_CollaborateurDao" )
	private EntityManager em;
	
	public void create(Collaborateur collaborateur) throws DAOException {
		try
		{
			em.persist (collaborateur);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<Collaborateur> getListOfCollaborateurs(Declaration declaration) throws DAOException {

		List<Collaborateur> listCollaborateurs = new Vector<Collaborateur>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listCollaborateurs = ((List<Collaborateur>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listCollaborateurs;
	}
	
	public void update(Collaborateur collaborateur) throws DAOException {
		try
		{
			em.merge (collaborateur);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}

	public void delete(Collaborateur collaborateur) {
		
		try
		{
			if (!em.contains(collaborateur))
			{
				collaborateur = em.merge(collaborateur);
			}
			em.remove(collaborateur);
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
}