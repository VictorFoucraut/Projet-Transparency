package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Declaration;
import entity.MandatElectif;

@Stateless
public class MandatElectifDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT m FROM MandatElectif m where m.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_MandatElectifDao" )
	private EntityManager em;
	
	public void create(MandatElectif mandatElectif) throws DAOException {
		try
		{
			em.persist (mandatElectif);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<MandatElectif> getListOfMandatElectif(Declaration declaration) throws DAOException {

		List<MandatElectif> listOfMandatElectif = new Vector<MandatElectif>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listOfMandatElectif = ((List<MandatElectif>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listOfMandatElectif;
	}
}