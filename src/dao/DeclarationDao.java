package dao;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Declaration;

@Stateless
public class DeclarationDao {

	private static final String JPQL_SELECT_PAR_UUID = "SELECT u FROM Declaration u WHERE u.uuid=:uuid";
	private static final String JPQL_SELECT_PAR_NOMPRENOM = "SELECT u FROM Declaration u WHERE u.nom=:nom AND u.prenom=:prenom";
	private static final String JPQL_SELECT_PAR_NOMPRENOM_ACTIV = "SELECT u FROM Declaration u WHERE u.nom=:nom AND u.prenom=:prenom AND u.activite=:activite";
	private static final String JPQL_SELECT_ALL = "SELECT d FROM Declaration d";
	private static final String JPQL_SELECT_ALL_ACTIV = "SELECT d FROM Declaration d where d.activite=1";
	
	@PersistenceContext( unitName = "PU_Declaration" )
	private EntityManager em;
	
	public void create(Declaration declaration) throws DAOException {
		try
		{
			em.persist (declaration);
			
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}
	
	public Declaration read(String uuid) throws DAOException {

		Declaration declaration = null;
		try
		{
		Query requete = em.createQuery(JPQL_SELECT_PAR_UUID);
		requete.setParameter("uuid", uuid);
		
			declaration = (Declaration) requete.getSingleResult();
			
		}
		catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return declaration;		

	}
	
	public Declaration readActiv(String nom, String prenom) {

		Declaration declaration = null;
		try
		{
			Query requete = em.createQuery(JPQL_SELECT_PAR_NOMPRENOM_ACTIV);
			requete.setParameter("nom", nom);
			requete.setParameter("prenom", prenom);
			requete.setParameter("activite", true);
		
			declaration = (Declaration) requete.getSingleResult();
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return declaration;
	}
	
	public Declaration reaqsdd(String nom, String prenom) throws DAOException {

		Declaration declaration = null;
		try
		{
			Query requete = em.createQuery(JPQL_SELECT_PAR_NOMPRENOM);
			requete.setParameter("nom", nom);
			requete.setParameter("prenom", prenom);
		
			declaration = (Declaration) requete.getSingleResult();
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return declaration;
	}

	public void update(Declaration declaration) throws DAOException {
		try
		{
			em.merge (declaration);
			
		}catch (Exception e)
		{
			throw new DAOException( e );
		}
	}

	public List<Declaration> getListOfAllDeclarations () throws DAOException{
				
		List<Declaration> listDeclarations = new Vector<Declaration>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_ALL);
			listDeclarations = ((List<Declaration>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listDeclarations;
	}
	
	public List<Declaration> getListOfAllActivDeclarations() {
		
		List<Declaration> listDeclarations = new Vector<Declaration>();

		try
		{
			Query requete = em.createQuery(JPQL_SELECT_ALL_ACTIV);
			listDeclarations = ((List<Declaration>) requete.getResultList());
			
		}catch (NoResultException e)
		{
			return null;
		}catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listDeclarations;
	}

	public void delete(Declaration declaration) {
		
		try
		{
			if (!em.contains(declaration))
			{
				declaration = em.merge(declaration);
			}
			em.remove(declaration);
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
		
	}



	

	
}
