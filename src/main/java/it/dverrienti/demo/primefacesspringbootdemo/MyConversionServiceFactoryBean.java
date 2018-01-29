package it.dverrienti.demo.primefacesspringbootdemo;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

public class MyConversionServiceFactoryBean extends ConversionServiceFactoryBean {
	
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		ConversionService conversionService = getObject();
		ConverterRegistry registry = (ConverterRegistry) conversionService;
		//registry.addConverter(null);
	}

}
