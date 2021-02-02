package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Declaration;
import entity.FonctionBenevole;

@Stateless
public class FonctionBenevoleDao {

	private static final String JPQL_SELECT_LIST_PAR_ID_DECLARATION = "SELECT f FROM FonctionBenevole f where f.declaration=:declaration";
	
	@PersistenceContext( unitName = "PU_FonctionBenevoleDao" )
	private EntityManager em;
	
	public void create(FonctionBenevole fonctionBenevole) throws DAOException {
		try
		{
			em.persist (fonctionBenevole);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public List<FonctionBenevole> getListOfFonctionBenevole(Declaration declaration) throws DAOException {

		List<FonctionBenevole> listFonctionBenevole = new Vector<FonctionBenevole>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_LIST_PAR_ID_DECLARATION);
			requete.setParameter("declaration", declaration);
			listFonctionBenevole = ((List<FonctionBenevole>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listFonctionBenevole;
	}
}