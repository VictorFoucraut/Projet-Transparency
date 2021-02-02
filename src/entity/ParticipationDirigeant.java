package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.participationdirigeantdto")
public class ParticipationDirigeant {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String commentaire;
	private String conservee;
	private String nomSociete;
	private String activite;
	private String dateDebut;
	private String dateFin;
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
	
	public void setConservee(String conservee)
	{
		this.conservee=conservee;
	}
	
	public void setNomSociete(String nomSociete)
	{
		this.nomSociete=nomSociete;
	}
	
	public void setActivite(String activite)
	{
		this.activite=activite;
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
	
	
	public Integer getId()
	{
		return id;
	}
	
	public Declaration getDeclaration()
	{
		return declaration;
	}

	public String getConservee()
	{
		return conservee;
	}
	
	public String getNomSociete()
	{
		return nomSociete;
	}
	
	public String getActivite()
	{
		return activite;
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
}