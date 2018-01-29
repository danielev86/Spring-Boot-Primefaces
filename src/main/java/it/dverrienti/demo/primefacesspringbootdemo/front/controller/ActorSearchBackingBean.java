package it.dverrienti.demo.primefacesspringbootdemo.front.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorFilterViewBean;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorViewBean;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorViewBeanDataModelLazy;
import it.dverrienti.demo.primefacesspringbootdemo.service.IActorService;

@ManagedBean(name="actorSearchController")
@ViewScoped
public class ActorSearchBackingBean implements Serializable {
	
	private static final Logger logger = Logger.getLogger(ActorSearchBackingBean.class);

	private static final long serialVersionUID = 1L;
	
	private List<ActorViewBean> lstActor;
	
	private LazyDataModel<ActorViewBean> actorLazyData;
	
	private ActorViewBean actorDetail;
	
	@ManagedProperty(value="#{actorService}")
	private IActorService actorService;
	
	private ActorFilterViewBean actorFilter;
	
	@PostConstruct
	public void init() {
		actorFilter = new ActorFilterViewBean();
		findActor();
	}
	
	public void findActor() {
		actorLazyData = new ActorViewBeanDataModelLazy(actorFilter.getFirstName(), actorFilter.getLastName(), actorService);
	}
	
	private void inputReset() {
		actorFilter.setFirstName(null);
		actorFilter.setLastName(null);
	}
	
	public void onRowEdit(RowEditEvent event) {
		ActorViewBean actor = (ActorViewBean) event.getObject();
		actorService.saveActor(actor);
	}

	public List<ActorViewBean> getLstActor() {
		return lstActor;
	}

	public void setLstActor(List<ActorViewBean> lstActor) {
		this.lstActor = lstActor;
	}

	public IActorService getActorService() {
		return actorService;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public ActorFilterViewBean getActorFilter() {
		return actorFilter;
	}

	public void setActorFilter(ActorFilterViewBean actorFilter) {
		this.actorFilter = actorFilter;
	}

	public LazyDataModel<ActorViewBean> getActorLazyData() {
		return actorLazyData;
	}

	public void setActorLazyData(LazyDataModel<ActorViewBean> actorLazyData) {
		this.actorLazyData = actorLazyData;
	}

	public ActorViewBean getActorDetail() {
		return actorDetail;
	}

	public void setActorDetail(ActorViewBean actorDetail) {
		this.actorDetail = actorDetail;
	}
}
