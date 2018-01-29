package it.dverrienti.demo.primefacesspringbootdemo.front.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorViewBean;

@ManagedBean(name="actorInsertController")
@ViewScoped
public class ActorInsertBackingBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ActorViewBean actor;
	
	@PostConstruct
	public void init() {
		actor = new ActorViewBean();
	}
	
	public void saveActor() {
		//TODO
	}

	public ActorViewBean getActor() {
		return actor;
	}

	public void setActor(ActorViewBean actor) {
		this.actor = actor;
	}

}
