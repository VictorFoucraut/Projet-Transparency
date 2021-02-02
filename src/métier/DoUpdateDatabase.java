package m�tier;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.ActiviteConsultantDao;
import dao.ActiviteProfDao;
import dao.CollaborateurDao;
import dao.DAOException;
import dao.DeclarationDao;
import dao.FonctionBenevoleDao;
import dao.MandatElectifDao;
import dao.ObservationInteretDao;
import dao.ParticipationDirigeantDao;
import dao.ParticipationFinanciereDao;
import entity.ActiviteConsultant;
import entity.ActiviteProf;
import entity.Collaborateur;
import entity.Declaration;
import entity.FonctionBenevole;
import entity.MandatElectif;
import entity.ObservationInteret;
import entity.ParticipationDirigeant;
import entity.ParticipationFinanciere;

public class DoUpdateDatabase {

	private static final String XPATH_DECLARATION = "/declarations/declaration";
	private static final String XPATH_NOM = "general/declarant/nom";
	private static final String XPATH_PRENOM = "general/declarant/prenom";
	private static final String XPATH_UUID = "uuid";
	private static final String XPATH_POSTE = "general/qualiteDeclarantForPDF";
	private static final String XPATH_DATE_DEPOT = "dateDepot";
	private static final String XPATH_MANDAT = "general/mandat/label";
	private static final String XPATH_DATE_DEBUT_MANDAT = "general/dateDebutMandat";
	private static final String XPATH__DATE_FIN_MANDAT = "general/dateFinMandat";
	private static final String XPATH_CIVILITE = "general/declarant/civilite";
	private static final String XPATH_DATE_NAISSANCE = "general/declarant/dateNaissance";
	private static final String XPATH_COLLABORATEUR = "activCollaborateursDto/items/items";
	private static final String XPATH_COMMENTAIRE = "commentaire";
	private static final String XPATH_NOM_COLLABORATEUR = "nom";
	private static final String XPATH_EMPLOYEUR = "employeur";
	private static final String XPATH_DESCR_ACTIVITE = "descriptionActivite";
	private static final String XPATH_DATE_COD_TYPE_MANDAT = "general/qualiteMandat/codTypeMandatFichier";
	
	@EJB
	private DeclarationDao declarationDao;
	@EJB
	private CollaborateurDao collaborateurDao;
	@EJB
	private ActiviteConsultantDao activiteConsultantDao;
	@EJB
	private ActiviteProfDao activiteProfDao;
	@EJB
	private FonctionBenevoleDao fonctionBenevoleDao;
	@EJB
	private MandatElectifDao mandatElectifDao;
	@EJB
	private ObservationInteretDao observationInteretDao;
	@EJB
	private ParticipationDirigeantDao participationDirigeantDao;
	@EJB
	private ParticipationFinanciereDao participationFinanciereDao;
	
	public DoUpdateDatabase (DeclarationDao declarationDao, CollaborateurDao collaborateurDao, ActiviteConsultantDao activiteConsultantDao, ActiviteProfDao activiteProfDao, FonctionBenevoleDao fonctionBenevoleDao, MandatElectifDao mandatElectifDao, ObservationInteretDao observationInteretDao, ParticipationDirigeantDao participationDirigeantDao, ParticipationFinanciereDao participationFinanciereDao)
	{
		this.declarationDao = declarationDao;
		this.collaborateurDao = collaborateurDao;
		this.activiteConsultantDao = activiteConsultantDao;
		this.activiteProfDao = activiteProfDao;
		this.fonctionBenevoleDao = fonctionBenevoleDao;
		this.mandatElectifDao = mandatElectifDao;
		this.observationInteretDao = observationInteretDao;
		this.participationDirigeantDao = participationDirigeantDao;
		this.participationFinanciereDao = participationFinanciereDao;
	}
	
	public void doUpdateDatabase() throws DAOException
	{
		try {
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		File fileXML = new File("C:/Users/Victor Foucraut/eclipse-workspace/TransparencyProject/WebContent/declarations.xml");
		Document xml = documentBuilder.parse(fileXML);
		
		Element root = xml.getDocumentElement();
		XPathFactory xpf = XPathFactory.newInstance();
		XPath path = xpf.newXPath();
		
		NodeList listeDeclarations = (NodeList)path.evaluate(XPATH_DECLARATION, root,XPathConstants.NODESET);
		int nbDeclarations = listeDeclarations.getLength();
		
		
		for (int i=0;i<nbDeclarations;i++)
		{			
			Node n = listeDeclarations.item(i);
			n.getParentNode().removeChild(n);
			String uuid = path.evaluate(XPATH_UUID,n);
			String nom = path.evaluate(XPATH_NOM,n);
			String prenom = path.evaluate(XPATH_PRENOM,n);
			String dateDepotString = path.evaluate(XPATH_DATE_DEPOT,n);
			String mandat = path.evaluate(XPATH_MANDAT,n);
			Timestamp dateDepot = convertStringDate2Timestamp(dateDepotString);
			
			if (!mandat.equals("Député ou sénateur"))
				continue;
			
			Declaration declarationTemp2 = declarationDao.read(uuid);
			if (declarationTemp2 != null)
			{
				continue;
			}
			
			Declaration declarationTemp = declarationDao.readActiv(nom,prenom);
			if (declarationTemp != null)
			{
				if (dateDepot.after(convertStringDate2Timestamp(declarationTemp.getDateDepot())))
				{
					//on vire l'ancienne déclaration
					/*List<Collaborateur> listeCollabToDelete = collaborateurDao.getListOfCollaborateurs(declarationTemp);
					for (int ii=0;ii<listeCollabToDelete.size();ii++)
					{
						collaborateurDao.delete(listeCollabToDelete.get(ii));
					}
					declarationDao.delete(declarationTemp);*/
					
					//on vire l'ancienne déclaration
					declarationTemp.setActivite(false);
					declarationDao.update(declarationTemp);
				}
				else
				{
					//on crée une nouvelle déclaration avec non active
					createDeclaration(n, path, false);
					continue;
				}
			}
			//on crée une nouvelle déclaration			
			createDeclaration(n, path, true);
			/*
			String poste = path.evaluate(XPATH_POSTE,n);
			String dateDebutMandat = path.evaluate(XPATH_DATE_DEBUT_MANDAT,n);
			String dateFinMandat = path.evaluate(XPATH__DATE_FIN_MANDAT,n);
			String civilite = path.evaluate(XPATH_CIVILITE,n);
			String dateNaissance = path.evaluate(XPATH_DATE_NAISSANCE,n);
			
			Declaration newDeclaration = new Declaration();
			newDeclaration.setUuid(uuid);
			newDeclaration.setEtat("à analyser");
			newDeclaration.setNbCollabErreur(null);
			newDeclaration.setCommentaire(null);
			newDeclaration.setDateModif(null);
			newDeclaration.setAuteurAnalyse(null);
			newDeclaration.setAuteurRevision(null);
			newDeclaration.setNom(nom);
			newDeclaration.setPrenom(prenom);
			newDeclaration.setPoste(poste);
			newDeclaration.setDateDepot(dateDepotString);
			newDeclaration.setMandat(mandat);
			newDeclaration.setDateDebutMandat(dateDebutMandat);
			newDeclaration.setDateFinMandat(dateFinMandat);
			newDeclaration.setCivilite(civilite);
			newDeclaration.setDateNaissance(dateNaissance);
			newDeclaration.setActivite(dateNaissance);
			
			NodeList listeCollaborateurs = (NodeList)path.evaluate(XPATH_COLLABORATEUR, n,XPathConstants.NODESET);
			ArrayList<Collaborateur> listeCollaborateurEntity = new ArrayList<Collaborateur>();
			
			int nbCollaborateurs=listeCollaborateurs.getLength();
			for (int ii=0;ii<nbCollaborateurs;ii++)
			{
				Node nn = listeCollaborateurs.item(ii);
				Collaborateur collab = new Collaborateur();
				String commentaire = path.evaluate(XPATH_COMMENTAIRE, nn);
				String nomCollab = path.evaluate(XPATH_NOM_COLLABORATEUR, nn);
				String employeur = path.evaluate(XPATH_EMPLOYEUR, nn);
				String descriptionActivite = path.evaluate(XPATH_DESCR_ACTIVITE, nn);
				
				collab.setCommentaire(commentaire);
				collab.setNom(nomCollab);
				collab.setEmployeur(employeur);
				collab.setDescriptionActivite(descriptionActivite);
				collab.setEtat("à analyser");
				
				listeCollaborateurEntity.add(collab);
			}
			
			declarationDao.create(newDeclaration);
			
			for (int ii=0;ii<listeCollaborateurEntity.size();ii++)
			{
				listeCollaborateurEntity.get(ii).setDeclaration(newDeclaration);
				collaborateurDao.create(listeCollaborateurEntity.get(ii));
			}*/
		}
		}
		catch (ParserConfigurationException e) {
			throw new DAOException(e);
		}
		catch (IOException e) {
			throw new DAOException(e);
		}
		catch (SAXException e) {
			throw new DAOException(e);
		}
		catch (XPathExpressionException e) {
			throw new DAOException(e);
		}
		catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private Timestamp convertStringDate2Timestamp(String date) throws Exception
	{
		//Timestamp timestamp = new Timestamp(0, 0, 0, 0, 0, 0, 0);
		try {
		int jour = Integer.parseInt(date.substring(0, 2));
		int mois = Integer.parseInt(date.substring(3, 5));
		int annee = Integer.parseInt(date.substring(6, 10));
		
		int heure = Integer.parseInt(date.substring(11, 13));
		int minute = Integer.parseInt(date.substring(14, 16));
		int seconde = Integer.parseInt(date.substring(17, 19 ));
		
		Timestamp timestamp = new Timestamp(annee-1900, mois-1, jour, heure, minute, seconde, 0);
		
		return timestamp;
		}catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	private void createDeclaration(Node n, XPath path, boolean activite) throws XPathExpressionException
	{
		try {
			String uuid = path.evaluate(XPATH_UUID,n);
			String nom = path.evaluate(XPATH_NOM,n);
			String prenom = path.evaluate(XPATH_PRENOM,n);
			String dateDepotString = path.evaluate(XPATH_DATE_DEPOT,n);
			String mandat = path.evaluate(XPATH_MANDAT,n);
			String qualiteDeclarantForPdf = path.evaluate(XPATH_POSTE,n);
			String dateDebutMandat = path.evaluate(XPATH_DATE_DEBUT_MANDAT,n);
			String dateFinMandat = path.evaluate(XPATH__DATE_FIN_MANDAT,n);
			String civilite = path.evaluate(XPATH_CIVILITE,n);
			String dateNaissance = path.evaluate(XPATH_DATE_NAISSANCE,n);
			String codTypeMandat = path.evaluate(XPATH_DATE_COD_TYPE_MANDAT,n);
			String commentaireConjoint = path.evaluate("activProfConjointDto/items/items/commentaire",n);
			String nomConjoint = path.evaluate("activProfConjointDto/items/items/nomConjoint",n);
			String employeurConjoint = path.evaluate("activProfConjointDto/items/items/employeurConjoint",n);
			String activProfConjoint = path.evaluate("activProfConjointDto/items/items/activiteProf",n);
			
			Declaration newDeclaration = new Declaration();
			newDeclaration.setUuid(uuid);
			newDeclaration.setEtat("à analyser");
			newDeclaration.setNbCollabErreur(null);
			newDeclaration.setCommentaire(null);
			newDeclaration.setDateModif(null);
			newDeclaration.setAuteurAnalyse(null);
			newDeclaration.setAuteurRevision(null);
			newDeclaration.setNom(nom);
			newDeclaration.setPrenom(prenom);
			newDeclaration.setQualiteDeclarantForPdf(qualiteDeclarantForPdf);
			newDeclaration.setDateDepot(dateDepotString);
			newDeclaration.setMandat(mandat);
			newDeclaration.setDateDebutMandat(dateDebutMandat);
			newDeclaration.setDateFinMandat(dateFinMandat);
			newDeclaration.setCivilite(civilite);
			newDeclaration.setDateNaissance(dateNaissance);
			newDeclaration.setActivite(activite);
			newDeclaration.setCodTypeMandatFichier(codTypeMandat);
			newDeclaration.setCommentaireConjoint(commentaireConjoint);
			newDeclaration.setNomConjoint(nomConjoint);
			newDeclaration.setEmployeurConjoint(employeurConjoint);
			newDeclaration.setActivProfConjoint(activProfConjoint);
			
			// ajout des collaborateurs
			NodeList listeCollaborateurs = (NodeList)path.evaluate(XPATH_COLLABORATEUR, n,XPathConstants.NODESET);
			ArrayList<Collaborateur> listeCollaborateurEntity = new ArrayList<Collaborateur>();
			
			int nbCollaborateurs=listeCollaborateurs.getLength();
			for (int ii=0;ii<nbCollaborateurs;ii++)
			{
				Node nn = listeCollaborateurs.item(ii);
				Collaborateur collab = new Collaborateur();
				String commentaire = path.evaluate(XPATH_COMMENTAIRE, nn);
				String nomCollab = path.evaluate(XPATH_NOM_COLLABORATEUR, nn);
				String employeur = path.evaluate(XPATH_EMPLOYEUR, nn);
				String descriptionActivite = path.evaluate(XPATH_DESCR_ACTIVITE, nn);
				
				collab.setCommentaire(commentaire);
				collab.setNom(nomCollab);
				collab.setEmployeur(employeur);
				collab.setDescriptionActivite(descriptionActivite);
				collab.setEtat("à analyser");
				
				listeCollaborateurEntity.add(collab);
			}
			
			// ajout des activités consultant
			NodeList listeActiviteConsultant = (NodeList)path.evaluate("activConsultantDto/items/items", n,XPathConstants.NODESET);
			ArrayList<ActiviteConsultant> listeActiviteConsultantEntity = new ArrayList<ActiviteConsultant>();
			
			int nbActivConsultant=listeActiviteConsultant.getLength();
			for (int ii=0;ii<nbActivConsultant;ii++)
			{
				Node nn = listeActiviteConsultant.item(ii);
				ActiviteConsultant activConsult = new ActiviteConsultant();
				String commentaire = path.evaluate("commentaire", nn);
				String conservee = path.evaluate("conservee", nn);
				String nomEmployeur = path.evaluate("nomEmployeur", nn);
				String description = path.evaluate("description", nn);
				String dateDebut = path.evaluate("dateDebut", nn);
				String dateFin = path.evaluate("dateFin", nn);
				String remuneration = convertRemunerationToString(nn,path);
				
				activConsult.setCommentaire(commentaire);
				activConsult.setConservee(conservee);
				activConsult.setNomEmployeur(nomEmployeur);
				activConsult.setDescription(description);
				activConsult.setDateDebut(dateDebut);
				activConsult.setDateFin(dateFin);
				activConsult.setRemuneration(remuneration);
				
				listeActiviteConsultantEntity.add(activConsult);
			}
			
			// ajout des activités professionnelles
			NodeList listeActiviteProf = (NodeList)path.evaluate("activProfCinqDerniereDto/items/items", n,XPathConstants.NODESET);
			ArrayList<ActiviteProf> listeActiviteProfEntity = new ArrayList<ActiviteProf>();
			
			int nbActivProf=listeActiviteProf.getLength();
			for (int ii=0;ii<nbActivProf;ii++)
			{
				Node nn = listeActiviteProf.item(ii);
				ActiviteProf activProf = new ActiviteProf();
				String commentaire = path.evaluate("commentaire", nn);
				String conservee = path.evaluate("conservee", nn);
				String employeur = path.evaluate("employeur", nn);
				String dateDebut = path.evaluate("dateDebut", nn);
				String dateFin = path.evaluate("dateFin", nn);
				String remuneration = convertRemunerationToString(nn,path);
				
				activProf.setCommentaire(commentaire);
				activProf.setConservee(conservee);
				activProf.setEmployeur(employeur);
				activProf.setDateDebut(dateDebut);
				activProf.setDateFin(dateFin);
				activProf.setRemuneration(remuneration);
				
				listeActiviteProfEntity.add(activProf);
			}
			
			// ajout des activités bénévoles
			NodeList listeFctB = (NodeList)path.evaluate("fonctionBenevoleDto/items/items", n,XPathConstants.NODESET);
			ArrayList<FonctionBenevole> listeFctBEntity = new ArrayList<FonctionBenevole>();
			
			int nbFctB=listeFctB.getLength();
			for (int ii=0;ii<nbFctB;ii++)
			{
				Node nn = listeFctB.item(ii);
				FonctionBenevole fctB = new FonctionBenevole();
				String commentaire = path.evaluate("commentaire", nn);
				String conservee = path.evaluate("conservee", nn);
				String nomStructure = path.evaluate("nomStructure", nn);
				String descriptionActivite = path.evaluate("descriptionActivite", nn);
				
				fctB.setCommentaire(commentaire);
				fctB.setConservee(conservee);
				fctB.setNomStructure(nomStructure);
				fctB.setDescriptionActivite(descriptionActivite);
				
				listeFctBEntity.add(fctB);
			}
			
			// ajout des mandats electifs
			NodeList listeME = (NodeList)path.evaluate("mandatElectifDto/items/items", n,XPathConstants.NODESET);
			ArrayList<MandatElectif> listeMandatElectif = new ArrayList<MandatElectif>();
			
			int nbME=listeME.getLength();
			for (int ii=0;ii<nbME;ii++)
			{
				Node nn = listeME.item(ii);
				MandatElectif ME = new MandatElectif();
				String commentaire = path.evaluate("commentaire", nn);
				String conservee = path.evaluate("conservee", nn);
				String descriptionMandat = path.evaluate("descriptionMandat", nn);
				String dateDebut = path.evaluate("dateDebut", nn);
				String dateFin = path.evaluate("dateFin", nn);
				String remuneration = convertRemunerationToString(nn,path);
				
				ME.setCommentaire(commentaire);
				ME.setConservee(conservee);
				ME.setDescriptionMandat(descriptionMandat);
				ME.setDateDebut(dateDebut);
				ME.setDateFin(dateFin);
				ME.setRemuneration(remuneration);
				
				listeMandatElectif.add(ME);
			}
			
			// ajout des observations d'interet
			NodeList listeOI = (NodeList)path.evaluate("observationInteretDto/items/items", n,XPathConstants.NODESET);
			ArrayList<ObservationInteret> listeObservationInteret = new ArrayList<ObservationInteret>();
			
			int nbOI=listeOI.getLength();
			for (int ii=0;ii<nbOI;ii++)
			{
				Node nn = listeOI.item(ii);
				ObservationInteret OI = new ObservationInteret();
				String commentaire = path.evaluate("commentaire", nn);
				String contenu = path.evaluate("contenu", nn);
				
				OI.setCommentaire(commentaire);
				OI.setContenu(contenu);
				
				listeObservationInteret.add(OI);
			}
			
			// ajout des mandats electifs
			NodeList listePD = (NodeList)path.evaluate("participationDirigeantDto/items/items", n,XPathConstants.NODESET);
			ArrayList<ParticipationDirigeant> listeParticipationDirigeant = new ArrayList<ParticipationDirigeant>();
			
			int nbPD=listePD.getLength();
			for (int ii=0;ii<nbPD;ii++)
			{
				Node nn = listePD.item(ii);
				ParticipationDirigeant PD = new ParticipationDirigeant();
				String commentaire = path.evaluate("commentaire", nn);
				String conservee = path.evaluate("conservee", nn);
				String nomSociete = path.evaluate("nomSociete", nn);
				String activite2 = path.evaluate("activite", nn);
				String dateDebut = path.evaluate("dateDebut", nn);
				String dateFin = path.evaluate("dateFin", nn);
				String remuneration = convertRemunerationToString(nn,path);
				
				PD.setCommentaire(commentaire);
				PD.setConservee(conservee);
				PD.setNomSociete(nomSociete);
				PD.setActivite(activite2);
				PD.setDateDebut(dateDebut);
				PD.setDateFin(dateFin);
				PD.setRemuneration(remuneration);
				
				listeParticipationDirigeant.add(PD);
			}
			
			// ajout des mandats electifs
			NodeList listePF = (NodeList)path.evaluate("participationFinanciereDto/items/items", n,XPathConstants.NODESET);
			ArrayList<ParticipationFinanciere> listeParticipationFinanciere = new ArrayList<ParticipationFinanciere>();
			
			int nbPF=listePF.getLength();
			for (int ii=0;ii<nbPF;ii++)
			{
				Node nn = listePF.item(ii);
				ParticipationFinanciere PF = new ParticipationFinanciere();
				String commentaire = path.evaluate("commentaire", nn);
				String nomSociete = path.evaluate("nomSociete", nn);
				String evaluation = path.evaluate("evaluation", nn);
				String remuneration = path.evaluate("remuneration", nn);
				String capitalDetenu = path.evaluate("capitalDetenu", nn);
				String nombreParts = path.evaluate("nombreParts", nn);
				
				PF.setCommentaire(commentaire);
				PF.setNomSociete(nomSociete);
				PF.setEvaluation(evaluation);
				PF.setRemuneration(remuneration);
				PF.setCapitalDetenu(capitalDetenu);
				PF.setNombreParts(nombreParts);
				
				listeParticipationFinanciere.add(PF);
			}
			
			declarationDao.create(newDeclaration);
			
			for (int ii=0;ii<listeCollaborateurEntity.size();ii++)
			{
				listeCollaborateurEntity.get(ii).setDeclaration(newDeclaration);
				collaborateurDao.create(listeCollaborateurEntity.get(ii));
			}


			for (int ii=0;ii<listeActiviteConsultantEntity.size();ii++)
			{
				listeActiviteConsultantEntity.get(ii).setDeclaration(newDeclaration);
				activiteConsultantDao.create(listeActiviteConsultantEntity.get(ii));
			}
			
			for (int ii=0;ii<listeActiviteProfEntity.size();ii++)
			{
				listeActiviteProfEntity.get(ii).setDeclaration(newDeclaration);
				activiteProfDao.create(listeActiviteProfEntity.get(ii));
			}
			
			for (int ii=0;ii<listeFctBEntity.size();ii++)
			{
				listeFctBEntity.get(ii).setDeclaration(newDeclaration);
				fonctionBenevoleDao.create(listeFctBEntity.get(ii));
			}
			
			for (int ii=0;ii<listeMandatElectif.size();ii++)
			{
				listeMandatElectif.get(ii).setDeclaration(newDeclaration);
				mandatElectifDao.create(listeMandatElectif.get(ii));
			}
			
			for (int ii=0;ii<listeObservationInteret.size();ii++)
			{
				listeObservationInteret.get(ii).setDeclaration(newDeclaration);
				observationInteretDao.create(listeObservationInteret.get(ii));
			}
			
			for (int ii=0;ii<listeParticipationDirigeant.size();ii++)
			{
				listeParticipationDirigeant.get(ii).setDeclaration(newDeclaration);
				participationDirigeantDao.create(listeParticipationDirigeant.get(ii));
			}
			
			for (int ii=0;ii<listeParticipationFinanciere.size();ii++)
			{
				listeParticipationFinanciere.get(ii).setDeclaration(newDeclaration);
				participationFinanciereDao.create(listeParticipationFinanciere.get(ii));
			}
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			throw new XPathExpressionException(e);
		}

	}
	
	private String convertRemunerationToString (Node n, XPath path) throws XPathExpressionException
	{
		String anneeMontant = "";
		try {
			NodeList listeRemuneration = (NodeList)path.evaluate("remuneration/montant/montant", n,XPathConstants.NODESET);
			anneeMontant += path.evaluate("remuneration/brutNet", n) + " ";
			int nbRemuneration=listeRemuneration.getLength();
			
			for (int i=0;i<nbRemuneration;i++)
			{
				Node nn = listeRemuneration.item(i);			
				anneeMontant += path.evaluate("annee", nn) + ":" + path.evaluate("montant", nn) + " ";
			}
		}
		catch (XPathExpressionException e) {
			e.printStackTrace();
			throw new XPathExpressionException(e);
		}
		return anneeMontant;
		
	}
}
