package it.dverrienti.demo.primefacesspringbootdemo.backend;

import java.util.List;

import org.primefaces.model.SortOrder;

import it.dverrienti.demo.primefacesspringbootdemo.backend.model.Actor;

public interface IActorManager {
	
	List<Actor> findAllActor();	
	
	List<Actor> findActorByFilter(String firstName, String lastName, int first, int pageSize, String sortField, SortOrder sortOrder );
	
	Integer findTotalResult(String firstName, String lastName);
	
	void saveActor(Actor actor);

}
