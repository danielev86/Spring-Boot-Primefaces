package it.dverrienti.demo.primefacesspringbootdemo.backend.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericManager {
	
	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<?> findAll(Class<?> classz){
		StringBuilder hqlQuery = new StringBuilder();
		hqlQuery.append(" SELECT item ")
		.append(" FROM ")
		.append( classz.getName() )
		.append(" item ");
		
		return (List<?>) getEntityManager().createQuery(hqlQuery.toString()).getResultList();
	}
	
	public List<?> findGenericResult(StringBuilder hqlQuery){
		return (List<?>) getEntityManager().createQuery(hqlQuery.toString()).getResultList();
	}
	
	public abstract Integer findTotalResult(StringBuilder hqlQuery, List<?> conds, List<?> parameters, List<?> objects);
	
	public abstract List<?> findListByParameter(StringBuilder hqlQuery, List<?> conds, List<?> parameters, List<?> objects);
	
	public abstract List<?> findListLazyByParameter(StringBuilder hqlQuery, int first, int pageSize, List<?> conds, List<?> parameters, List<?> objects, String sortField, SortOrder sortOrder );

	public void addOrderField(StringBuilder hqlQuery, String sortField, SortOrder sortOrder) {
		String sortOrderCode = sortOrder == SortOrder.ASCENDING ? "ASC" : "DESC";
		hqlQuery.append(" ORDER BY ")
		.append(sortField)
		.append(" ")
		.append(sortOrderCode);
	}
	
	public <T> void save(T objItem) {
		getEntityManager().merge(objItem);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
