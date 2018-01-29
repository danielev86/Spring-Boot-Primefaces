package it.dverrienti.demo.primefacesspringbootdemo.front.model;

import java.io.Serializable;
import java.util.Date;

public class ActorViewBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idActor;
	
	private String firstName;
	
	private String lastName;
	
	private Date lastUpdate;

	public Long getIdActor() {
		return idActor;
	}

	public void setIdActor(Long idActor) {
		this.idActor = idActor;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
