package it.dverrienti.demo.primefacesspringbootdemo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="upperCaseConverter")
public class UpperCaseConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent input, String value) {
		return value.toUpperCase();
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent input, Object objItem) {
		return objItem.toString();
	}

}
