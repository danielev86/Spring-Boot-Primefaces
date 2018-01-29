package it.dverrienti.demo.primefacesspringbootdemo.backend.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import it.dverrienti.demo.primefacesspringbootdemo.backend.IActorManager;
import it.dverrienti.demo.primefacesspringbootdemo.backend.model.Actor;
import it.dverrienti.demo.primefacesspringbootdemo.constants.IConstants;

@Repository
public class ActorManagerImpl extends GenericManager implements IActorManager {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<Actor> findAllActor(){
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(" SELECT ac ")
		.append(" FROM Actor ac ");
		addOrderSelection(hqlQuery, IConstants.LASTNAME_FIELD_ORDER);
		return (List<Actor>) findGenericResult(hqlQuery);
	}
	
	@SuppressWarnings("unchecked")
	public List<Actor> findActorByFilter(String firstName, String lastName, int first, int pageSize, String sortField, SortOrder sortOrder ){
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(" SELECT ac ")
		.append(" FROM Actor ac ");
		
		List cond = new ArrayList();
		List parameter = new ArrayList();
		List objects = new ArrayList();
		
		
		buildFirstNameCondition(firstName, cond, parameter, objects);
		
		buildLastNameCondition(lastName, cond, parameter, objects);
		
		return (List<Actor>) findListLazyByParameter(hqlQuery, first, pageSize, cond, parameter, objects, sortField, sortOrder);
		
	}
	
	public Integer findTotalResult(String firstName, String lastName) {
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(" SELECT count(*) ")
		.append(" FROM Actor ac ");
		
		List cond = new ArrayList();
		List parameter = new ArrayList();
		List objects = new ArrayList();
		
		
		buildFirstNameCondition(firstName, cond, parameter, objects);
		
		buildLastNameCondition(lastName, cond, parameter, objects);
		
		return findTotalResult(hqlQuery, cond, parameter, objects);
	}
	
	public Integer findTotalResult(StringBuilder hqlQuery, List<?> conds, List<?> parameters, List<?> objects) {
		
		Query query = buildCommonQueryResult(hqlQuery, conds, parameters, objects, null, null);
		return ((Long) query.getResultList().get(0)).intValue();
		
	}
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveActor(Actor actor) {
		super.save(actor);
	}
	
	private void addOrderSelection(StringBuilder hqlQuery, String orderField) {
		
		switch (orderField) {
		case IConstants.FIRSTNAME_FIELD_ORDER:
			hqlQuery.append(" ORDER BY ac.firstName ");
			break;
		case IConstants.LASTNAME_FIELD_ORDER:
			hqlQuery.append(" ORDER BY ac.lastName ");
			break;

		default:
			break;
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<?> findListByParameter(StringBuilder hqlQuery, List<?> conds, List<?> parameters,
			List<?> objects) {
		Query query = buildCommonQueryResult(hqlQuery, conds, parameters, objects, null, null);
		return (List<Object>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<?> findListLazyByParameter(StringBuilder hqlQuery, int first, int pageSize, List<?> conds,
			List<?> parameters, List<?> objects, String sortField, SortOrder sortOrder) {
		Query query = buildCommonQueryResult(hqlQuery, conds, parameters, objects, sortField, sortOrder);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return (List<Object>) query.getResultList();
	}
	
	private Query buildCommonQueryResult(StringBuilder hqlQuery, List<?> conds,
			List<?> parameters, List<?> objects, String sortField, SortOrder sortOrder) {
		
		Query query = null;
		if ( CollectionUtils.isNotEmpty(conds) ) {
			for (int i=0;i<conds.size();i++) {
				if (i==0) {
					hqlQuery
					.append(" WHERE ")
					.append(conds.get(i));
				}else {
					hqlQuery.append( " AND ").append(conds.get(i));
				}
			}
			
			if (StringUtils.isNotEmpty(sortField)) {
				addOrderField(hqlQuery, sortField, sortOrder);
			}
			
			query = entityManager.createQuery(hqlQuery.toString());
			
			//Add parameter
			for (int i=0;i<parameters.size();i++) {
				query.setParameter(parameters.get(i).toString(), objects.get(i));
			}
			
			
		}else {
			
			if (StringUtils.isNotEmpty(sortField)) {
				addOrderField(hqlQuery, sortField, sortOrder);
			}
			
			query = entityManager.createQuery(hqlQuery.toString());
		}
		return query;
	}
	
	private void buildFirstNameCondition(String firstName, List cond, List parameter, List objects) {
		if (StringUtils.isNotEmpty(firstName)) {
			cond.add(" lower(ac.firstName) LIKE :firstName");
			parameter.add("firstName");
			objects.add("%" + firstName.toLowerCase() + "%");
		}
	}

	private void buildLastNameCondition(String lastName, List cond, List parameter, List objects) {
		if (StringUtils.isNotEmpty(lastName)) {
			cond.add(" lower(ac.lastName) LIKE :lastName ");
			parameter.add("lastName");
			objects.add("%" +lastName.toLowerCase() +"%");
		}
	}

}
