package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Declaration;
import entity.ParticipationFinanciere;

@Stateless
public class ParticipationFinanciereDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT p FROM ParticipationFinanciere p where p.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_ParticipationFinanciereDao" )
	private EntityManager em;
	
	public void create(ParticipationFinanciere participationFinanciere) throws DAOException {
		try
		{
			em.persist (participationFinanciere);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<ParticipationFinanciere> getListOfParticipationFinanciere(Declaration declaration) throws DAOException {

		List<ParticipationFinanciere> listOfParticipationFinanciere = new Vector<ParticipationFinanciere>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listOfParticipationFinanciere = ((List<ParticipationFinanciere>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listOfParticipationFinanciere;
	}
}