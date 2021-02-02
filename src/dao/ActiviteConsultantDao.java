package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.ActiviteConsultant;
import entity.Declaration;

@Stateless
public class ActiviteConsultantDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT a FROM ActiviteConsultant a where a.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_ActiviteConsultantDao" )
	private EntityManager em;
	
	public void create(ActiviteConsultant activiteConsultant) throws DAOException {
		try
		{
			em.persist (activiteConsultant);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<ActiviteConsultant> getListOfActiviteConsultant(Declaration declaration) throws DAOException {

		List<ActiviteConsultant> listActiviteConsultant = new Vector<ActiviteConsultant>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listActiviteConsultant = ((List<ActiviteConsultant>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listActiviteConsultant;
	}
}