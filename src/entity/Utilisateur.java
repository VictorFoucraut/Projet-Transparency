package entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "bddtest.utilisateur")
public class Utilisateur {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String mdp;
	private Integer droit;
	@Column( name = "date_inscription" )
	private Timestamp dateInscription;
	
	
	public void setId(Integer id)
	{
		this.id=id;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public void setPrenom(String prenom)
	{
		this.prenom=prenom;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}
	
	public void setMdp(String mdp)
	{
		this.mdp=mdp;
	}
	
	public void setDroit(Integer droit)
	{
		this.droit=droit;
	}
	
	public void setDateInscription(Timestamp dateInscription)
	{
		this.dateInscription=dateInscription;
	}
	
	
	public Integer getId()
	{
		return id;
	}

	public String getNom()
	{
		return nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getMdp()
	{
		return mdp;
	}
	
	public Integer getDroit()
	{
		return droit;
	}
	
	public Timestamp getDateInscription()
	{
		return dateInscription;
	}

}
