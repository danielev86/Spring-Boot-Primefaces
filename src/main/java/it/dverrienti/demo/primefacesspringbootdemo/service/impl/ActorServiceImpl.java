package it.dverrienti.demo.primefacesspringbootdemo.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import it.dverrienti.demo.primefacesspringbootdemo.backend.IActorManager;
import it.dverrienti.demo.primefacesspringbootdemo.backend.model.Actor;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorFilterViewBean;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorViewBean;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.WrapViewBean;
import it.dverrienti.demo.primefacesspringbootdemo.service.IActorService;
import it.dverrienti.demo.primefacesspringbootdemo.utility.ConverterUtility;

@Service(value="actorService")
public class ActorServiceImpl  implements IActorService {
	
	private static final Logger logger = LoggerFactory.getLogger(ActorServiceImpl.class);
	
	@Autowired
	private IActorManager actorManager;
	
	@Autowired
	@Qualifier(value="conversionService")
	private ConversionService conversion;
	
	@SuppressWarnings("unchecked")
	public WrapViewBean<Collection<ActorViewBean>> getAllActor(){
		WrapViewBean< Collection<ActorViewBean>> wrapBean = new WrapViewBean<>();
		
		Collection<ActorViewBean> lstResult = new ArrayList<>();
		List<Actor> lstActor = null;
		
		try {
			lstActor = actorManager.findAllActor();
			lstResult = (Collection<ActorViewBean>) new ConverterUtility<List<Actor>>().convertCollection(conversion, lstActor, Collection.class, ActorViewBean.class, List.class);
			wrapBean.setCheckValidation(Boolean.TRUE);
			wrapBean.setMessages(new ArrayList<>());
		}catch(Exception e) {
			logger.error("Cannot retrieve data from ACTOR table");
			wrapBean.setCheckValidation(Boolean.FALSE);
			wrapBean.setMessages(new ArrayList<>());
		}
		wrapBean.setWrapped(lstResult);
		return wrapBean;
	}
	
	@SuppressWarnings("unchecked")
	public WrapViewBean<Collection<ActorViewBean>> getActorByFilterLazy(ActorFilterViewBean filterBean){
		WrapViewBean<Collection<ActorViewBean>> wrapBean = new WrapViewBean<>();
		
		Collection<ActorViewBean> lstResult = new ArrayList<>();
		List<Actor> lstActor = null;
		
		
		try {
			lstActor = actorManager.findActorByFilter(filterBean.getFirstName(), filterBean.getLastName(), filterBean.getFirstPage(), filterBean.getItemPerPage(), filterBean.getSortField(), filterBean.getSortOrder());
			lstResult = (Collection<ActorViewBean>) new ConverterUtility<List<Actor>>().convertCollection(conversion, lstActor, Collection.class, ActorViewBean.class, List.class);
			wrapBean.setCheckValidation(Boolean.TRUE);
			wrapBean.setMessages(new ArrayList<>());
			
		}catch (Exception e) {
			logger.error("Cannot retrieve data from ACTOR table");
			wrapBean.setCheckValidation(Boolean.FALSE);
			wrapBean.setMessages(new ArrayList<>());
		}
		
		wrapBean.setWrapped(lstResult);
		
		return wrapBean;
	}
	
	public Integer getNumberActorRow(String firstName, String lastName) {
		return actorManager.findTotalResult(firstName, lastName);
	}
	
	public void saveActor(ActorViewBean actorVB) {
		Actor actor = conversion.convert(actorVB, Actor.class);
		actorManager.saveActor(actor);
	}

}
