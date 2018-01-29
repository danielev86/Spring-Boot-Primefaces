package it.dverrienti.demo.primefacesspringbootdemo.front.model;

import java.io.Serializable;
import java.util.List;

public class WrapViewBean<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private boolean checkValidation;
	
	private List<String> messages;
	
	private T wrapped;

	public boolean isCheckValidation() {
		return checkValidation;
	}

	public void setCheckValidation(boolean checkValidation) {
		this.checkValidation = checkValidation;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public T getWrapped() {
		return wrapped;
	}

	public void setWrapped(T wrapped) {
		this.wrapped = wrapped;
	}
}
