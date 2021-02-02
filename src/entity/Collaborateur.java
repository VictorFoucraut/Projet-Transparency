package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.collaborateur")
public class Collaborateur {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String etat;
	private String commentaireUtilisateur;
	private String commentaire;
	private String nom;
	private String employeur;
	private String descriptionActivite;
	
	public Integer getId()
	{
		return id;
	}
	
	public Declaration getDeclaration()
	{
		return declaration;
	}
	
	public String getEtat()
	{
		return etat;
	}
	
	public String getCommentaireUtilisateur()
	{
		return commentaireUtilisateur;
	}
	
	public String getCommentaire()
	{
		return commentaire;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getEmployeur()
	{
		return employeur;
	}
	
	public String getDescriptionActivite()
	{
		return descriptionActivite;
	}
	
	
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public void setDeclaration(Declaration declaration)
	{
		this.declaration = declaration;
	}
	
	public void setEtat(String etat)
	{
		this.etat = etat;
	}
	
	public void setCommentaireUtilisateur(String commentaireUtilisateur)
	{
		this.commentaireUtilisateur = commentaireUtilisateur;
	}
	
	public void setCommentaire(String commentaire)
	{
		this.commentaire = commentaire;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public void setEmployeur(String employeur)
	{
		this.employeur = employeur;
	}
	
	public void setDescriptionActivite(String descriptionActivite)
	{
		this.descriptionActivite = descriptionActivite;
	}
}