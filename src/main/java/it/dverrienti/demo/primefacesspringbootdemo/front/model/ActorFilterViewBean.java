package it.dverrienti.demo.primefacesspringbootdemo.front.model;

import java.io.Serializable;

import org.primefaces.model.SortOrder;

public class ActorFilterViewBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String firstName;
	
	private String lastName;
	
	private int firstPage;
	
	private int itemPerPage;
	
	private String sortField;
	
	private SortOrder sortOrder;

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

	public int getFirstPage() {
		return firstPage;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public String getSortField() {
		return sortField;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
