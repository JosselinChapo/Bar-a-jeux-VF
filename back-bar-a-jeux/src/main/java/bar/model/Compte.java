package bar.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "mail" }))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_personne", discriminatorType = DiscriminatorType.STRING)
public abstract class Compte {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.ViewBase.class)
	private Integer id;
	@Column(name="mail", length = 100)
	@JsonView(Views.ViewBase.class)
	private String mail;
	@Column(name="password", length = 100)
	@JsonView(Views.ViewBase.class)
	private String password;
	@Column(name="type", length=20)
	@JsonView(Views.ViewBase.class)
	private String type;
	
	public Compte() {
		super();
	}

	public Compte(Integer id, String mail, String password, String type) {
		super();
		this.id = id;
		this.mail = mail;
		this.password = password;
		this.type = type;
	}
	
	public Compte(String mail, String password, String type) {
		super();
		this.mail = mail;
		this.password = password;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
