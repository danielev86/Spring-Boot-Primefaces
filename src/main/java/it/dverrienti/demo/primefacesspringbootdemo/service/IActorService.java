package it.dverrienti.demo.primefacesspringbootdemo.service;

import java.util.Collection;
import java.util.List;

import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorFilterViewBean;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorViewBean;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.WrapViewBean;

public interface IActorService {
	
	WrapViewBean<Collection<ActorViewBean>> getAllActor();
		
	WrapViewBean<Collection<ActorViewBean>> getActorByFilterLazy(ActorFilterViewBean filterBean);
	
	Integer getNumberActorRow(String firstName, String lastName);
	
	void saveActor(ActorViewBean actorVB);

}
