package it.dverrienti.demo.primefacesspringbootdemo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import it.dverrienti.demo.primefacesspringbootdemo.converter.ActorConverter;
import it.dverrienti.demo.primefacesspringbootdemo.converter.ActorViewBeanConverter;

@Configuration
public class ConversionServiceConfig {
	
	
	@Bean
	public ConversionService conversionService() {
		MyConversionServiceFactoryBean bean = new MyConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();
        ConversionService object = bean.getObject();
        return object;
	}
	
	private Set<Converter> getConverters(){
		Set<Converter> converter = new HashSet<>();
		converter.add(new ActorViewBeanConverter());
		converter.add(new ActorConverter());
		return converter;
	}

}
