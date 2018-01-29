package it.dverrienti.demo.primefacesspringbootdemo.front.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import it.dverrienti.demo.primefacesspringbootdemo.service.IActorService;

public class ActorViewBeanDataModelLazy extends LazyDataModel<ActorViewBean> {
	
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	
	private String lastName;
	
	private IActorService actorService;
	
	private List<ActorViewBean> lstActor;
	
	public ActorViewBeanDataModelLazy(String firstName, String lastName, IActorService actorService) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.actorService = actorService;
		lstActor = new ArrayList<>();
	}
	
	public Object getRowKey(ActorViewBean actor) {
		return actor.getIdActor();
	}
	
	public ActorViewBean getRowData(String idActor) {
		Long id = Long.parseLong(idActor);
		for (ActorViewBean actor : getLstActor()) {
			if (id.equals(actor.getIdActor()))
				return actor;
		}
		return null;
	}
	
	public List<ActorViewBean> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters){
		ActorFilterViewBean filterBean = new ActorFilterViewBean();
		filterBean.setFirstName(firstName);
		filterBean.setLastName(lastName);
		filterBean.setFirstPage(first);
		filterBean.setItemPerPage(pageSize);
		filterBean.setSortField(sortField);
		filterBean.setSortOrder(sortOrder);
		setRowCount(actorService.getNumberActorRow(firstName, lastName));
		WrapViewBean<Collection<ActorViewBean>> wrapBean = actorService.getActorByFilterLazy(filterBean);
		setPageSize(pageSize);
		setRowIndex(0);
		List<ActorViewBean> lstResult = (List<ActorViewBean>) wrapBean.getWrapped();
		setLstActor(lstResult);
		
		return lstResult;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public IActorService getActorService() {
		return actorService;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public List<ActorViewBean> getLstActor() {
		return lstActor;
	}

	public void setLstActor(List<ActorViewBean> lstActor) {
		this.lstActor = lstActor;
	}
	
}
