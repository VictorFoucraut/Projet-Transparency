package entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.declaration")
public class Declaration {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	private String uuid;
	private String etat;
	private Integer nbCollabErreur;
	private String commentaire;	
	private Timestamp dateModif;
	@ManyToOne
	@JoinColumn(name = "auteurAnalyse")
	private Utilisateur auteurAnalyse;
	@ManyToOne
	@JoinColumn(name = "auteurRevision")
	private Utilisateur auteurRevision;
	private String nom;
	private String prenom;
	private String qualiteDeclarantForPdf;
	private String dateDepot;
	private String mandat;
	private String dateDebutMandat;
	private String dateFinMandat;
	private String civilite;
	private String dateNaissance;
	private boolean activite;
	private String codTypeMandatFichier;
	private String commentaireConjoint;
	private String nomConjoint;
	private String employeurConjoint;
	private String activProfConjoint;
	
	public Integer getId()
	{
		return id;
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
	public String getEtat()
	{
		return etat;
	}
	
	public Integer getNbCollabErreur()
	{
		return nbCollabErreur;
	}
	
	public String getCommentaire()
	{
		return commentaire;
	}
	
	public Timestamp getDateModif()
	{
		return dateModif;
	}
	
	public Utilisateur getAuteurAnalyse()
	{
		return auteurAnalyse;
	}
	
	public Utilisateur getAuteurRevision()
	{
		return auteurRevision;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getQualiteDeclarantForPdf()
	{
		return qualiteDeclarantForPdf;
	}
	
	public String getDateDepot()
	{
		return dateDepot;
	}
	
	public String getMandat()
	{
		return mandat;
	}
	
	public String getDateDebutMandat()
	{
		return dateDebutMandat;
	}
	
	public String getDateFinMandat()
	{
		return dateFinMandat;
	}
	
	public String getCivilite()
	{
		return civilite;
	}
	
	public String getDateNaissance()
	{
		return dateNaissance;
	}
	
	public boolean getActivite()
	{
		return activite;
	}
	
	public String getCodTypeMandatFichier()
	{
		return codTypeMandatFichier;
	}
	
	public String getCommentaireConjoint()
	{
		return commentaireConjoint;
	}
	
	public String getNomConjoint()
	{
		return nomConjoint;
	}
	
	public String getEmployeurConjoint()
	{
		return employeurConjoint;
	}
	
	public String getActivProfConjoint()
	{
		return activProfConjoint;
	}
	


	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
	
	public void setEtat(String etat)
	{
		this.etat=etat;
	}
	
	public void setNbCollabErreur(Integer nbCollabErreur)
	{
		this.nbCollabErreur=nbCollabErreur;
	}
	
	public void setCommentaire(String commentaire)
	{
		this.commentaire=commentaire;
	}
	
	public void setDateModif(Timestamp dateModif)
	{
		this.dateModif=dateModif;
	}
	
	public void setAuteurAnalyse(Utilisateur auteurAnalyse)
	{
		this.auteurAnalyse=auteurAnalyse;
	}
	
	public void setAuteurRevision(Utilisateur auteurRevision)
	{
		this.auteurRevision=auteurRevision;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public void setPrenom(String prenom)
	{
		this.prenom=prenom;
	}
	
	public void setQualiteDeclarantForPdf(String qualiteDeclarantForPdf)
	{
		this.qualiteDeclarantForPdf=qualiteDeclarantForPdf;
	}
	
	public void setDateDepot(String dateDepot)
	{
		this.dateDepot=dateDepot;
	}
	
	public void setMandat(String mandat)
	{
		this.mandat=mandat;
	}
	
	public void setDateDebutMandat(String dateDebutMandat)
	{
		this.dateDebutMandat=dateDebutMandat;
	}
	
	public void setDateFinMandat(String dateFinMandat)
	{
		this.dateFinMandat=dateFinMandat;
	}
	
	public void setCivilite(String civilite)
	{
		this.civilite=civilite;
	}
	
	public void setDateNaissance(String dateNaissance)
	{
		this.dateNaissance=dateNaissance;
	}
	
	public void setActivite(boolean activite)
	{
		this.activite=activite;
	}
	
	public void setCodTypeMandatFichier(String codTypeMandatFichier)
	{
		this.codTypeMandatFichier=codTypeMandatFichier;
	}
	
	public void setCommentaireConjoint(String commentaireConjoint)
	{
		this.commentaireConjoint=commentaireConjoint;
	}
	
	public void setNomConjoint(String nomConjoint)
	{
		this.nomConjoint=nomConjoint;
	}
	
	public void setEmployeurConjoint(String employeurConjoint)
	{
		this.employeurConjoint=employeurConjoint;
	}
	
	public void setActivProfConjoint(String activProfConjoint)
	{
		this.activProfConjoint=activProfConjoint;
	}
}
