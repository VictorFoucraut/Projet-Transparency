package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.fonctionbenevoledto")
public class FonctionBenevole {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String commentaire;
	private String descriptionActivite;
	private String nomStructure;
	private String conservee;
	
	
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public void setDeclaration(Declaration declaration)
	{
		this.declaration=declaration;
	}
	
	public void setCommentaire(String commentaire)
	{
		this.commentaire=commentaire;
	}
	
	public void setNomStructure(String nomStructure)
	{
		this.nomStructure=nomStructure;
	}
	
	public void setDescriptionActivite(String descriptionActivite)
	{
		this.descriptionActivite=descriptionActivite;
	}
	
	public void setConservee(String conservee)
	{
		this.conservee=conservee;
	}
	
	
	public Integer getId()
	{
		return id;
	}
	
	public Declaration getDeclaration()
	{
		return declaration;
	}

	public String getDescriptionActivite()
	{
		return descriptionActivite;
	}
	
	public String getCommentaire()
	{
		return commentaire;
	}
	
	public String getNomStructure()
	{
		return nomStructure;
	}
	
	public String getConservee()
	{
		return conservee;
	}

}