package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.participationfinancieredto")
public class ParticipationFinanciere {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String commentaire;
	private String nomSociete;
	private String capitalDetenu;
	private String evaluation;
	private String nombreParts;
	private String remuneration;
	
	
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
	
	public void setNomSociete(String nomSociete)
	{
		this.nomSociete=nomSociete;
	}
	
	public void setCapitalDetenu(String capitalDetenu)
	{
		this.capitalDetenu=capitalDetenu;
	}
	
	public void setEvaluation(String evaluation)
	{
		this.evaluation=evaluation;
	}
	
	public void setNombreParts(String nombreParts)
	{
		this.nombreParts=nombreParts;
	}
	
	public void setRemuneration(String remuneration)
	{
		this.remuneration=remuneration;
	}
	
	
	public Integer getId()
	{
		return id;
	}
	
	public Declaration getDeclaration()
	{
		return declaration;
	}
	
	public String getNomSociete()
	{
		return nomSociete;
	}
	
	public String getCapitalDetenu()
	{
		return capitalDetenu;
	}
	
	public String getCommentaire()
	{
		return commentaire;
	}
	
	public String getEvaluation()
	{
		return evaluation;
	}
	
	public String getNombreParts()
	{
		return nombreParts;
	}
	
	public String getRemuneration()
	{
		return remuneration;
	}
}