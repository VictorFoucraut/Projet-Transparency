package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Declaration;
import entity.ParticipationDirigeant;

@Stateless
public class ParticipationDirigeantDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT p FROM ParticipationDirigeant p where p.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_ParticipationDirigeantDao" )
	private EntityManager em;
	
	public void create(ParticipationDirigeant participationDirigeant) throws DAOException {
		try
		{
			em.persist (participationDirigeant);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<ParticipationDirigeant> getListOfParticipationDirigeant(Declaration declaration) throws DAOException {

		List<ParticipationDirigeant> listOfParticipationDirigeant = new Vector<ParticipationDirigeant>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listOfParticipationDirigeant = ((List<ParticipationDirigeant>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listOfParticipationDirigeant;
	}
}