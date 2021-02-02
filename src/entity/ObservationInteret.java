package entity;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.observationinteretdto")
public class ObservationInteret {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idDeclaration")
	private Declaration declaration;
	private String commentaire;
	private String contenu;
	
	
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
	
	public void setContenu(String contenu)
	{
		this.contenu=contenu;
	}
	
	
	public Integer getId()
	{
		return id;
	}
	
	public Declaration getDeclaration()
	{
		return declaration;
	}

	public String getContenu()
	{
		return contenu;
	}
	
	public String getCommentaire()
	{
		return commentaire;
	}
}