package bar.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Compte {	
	
	public Admin() {
		super();
	}
	
	public Admin(String mail, String password) {
		super(mail, password);
	}

	public Admin(Integer id, String mail, String password) {
		super(id, mail, password);
	}
	
}
