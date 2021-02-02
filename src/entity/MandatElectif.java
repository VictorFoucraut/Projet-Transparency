package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.mandatelectifdto")
public class MandatElectif {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String commentaire;
	private String descriptionMandat;
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
	
	public void setDescriptionMandat(String descriptionMandat)
	{
		this.descriptionMandat=descriptionMandat;
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

	public String getDescriptionMandat()
	{
		return descriptionMandat;
	}
	
	public String getCommentaire()
	{
		return commentaire;
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