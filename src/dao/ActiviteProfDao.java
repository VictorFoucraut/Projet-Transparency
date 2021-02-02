package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.ActiviteProf;
import entity.Declaration;

@Stateless
public class ActiviteProfDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT a FROM ActiviteProf a where a.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_ActiviteProfDao" )
	private EntityManager em;
	
	public void create(ActiviteProf activiteProf) throws DAOException {
		try
		{
			em.persist (activiteProf);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<ActiviteProf> getListOfActiviteProf(Declaration declaration) throws DAOException {

		List<ActiviteProf> listActiviteProf = new Vector<ActiviteProf>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listActiviteProf = ((List<ActiviteProf>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listActiviteProf;
	}
}