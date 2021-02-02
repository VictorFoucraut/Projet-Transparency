package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Declaration;
import entity.ObservationInteret;

@Stateless
public class ObservationInteretDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT o FROM ObservationInteret o where o.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_ObservationInteretDao" )
	private EntityManager em;
	
	public void create(ObservationInteret observationInteret) throws DAOException {
		try
		{
			em.persist (observationInteret);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<ObservationInteret> getListOfObservationInteret(Declaration declaration) throws DAOException {

		List<ObservationInteret> listOfObservationInteret = new Vector<ObservationInteret>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listOfObservationInteret = ((List<ObservationInteret>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listOfObservationInteret;
	}
}