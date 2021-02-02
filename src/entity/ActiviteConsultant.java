package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.activconsultantdto")
public class ActiviteConsultant {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String commentaire;
	private String nomEmployeur;
	private String description;
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
	
	public void setNomEmployeur(String nomEmployeur)
	{
		this.nomEmployeur=nomEmployeur;
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
	
	public String getCommentaire()
	{
		return commentaire;
	}
	
	public String getNomEmployeur()
	{
		return nomEmployeur;
	}
	
	public String getDescription()
	{
		return description;
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