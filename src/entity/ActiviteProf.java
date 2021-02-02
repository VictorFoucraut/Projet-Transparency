package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.activprofcinqdernieredto")
public class ActiviteProf {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String commentaire;
	private String description;
	private String employeur;
	private String dateDebut;
	private String dateFin;
	private String remuneration;
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
	
	public void setEmployeur(String employeur)
	{
		this.employeur=employeur;
	}
	
	public void setDescription(String description)
	{
		this.description=description;
	}
	
	public void setDateDebut(String dateDebut)
	{
		this.dateDebut=dateDebut;
	}
	
	public void setDateFin(String dateFin)
	{
		this.dateFin=dateFin;
	}
	
	public void setRemuneration(String remuneration)
	{
		this.remuneration=remuneration;
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

	public String getDescription()
	{
		return description;
	}
	
	public String getCommentaire()
	{
		return commentaire;
	}
	
	public String getEmployeur()
	{
		return employeur;
	}
	
	public String getDateDebut()
	{
		return dateDebut;
	}
	
	public String getDateFin()
	{
		return dateFin;
	}
	
	public String getRemuneration()
	{
		return remuneration;
	}
	
	public String getConservee()
	{
		return conservee;
	}

}