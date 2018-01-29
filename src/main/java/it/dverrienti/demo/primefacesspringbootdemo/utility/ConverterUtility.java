package it.dverrienti.demo.primefacesspringbootdemo.utility;

import java.util.Collection;

import org.springframework.core.convert.ConversionService;
import static org.springframework.core.convert.TypeDescriptor.collection;
import static org.springframework.core.convert.TypeDescriptor.valueOf;;


public class ConverterUtility<S> {
	
	public Collection<? extends Object> convertCollection(ConversionService conversion
			, S source
			, Class<?> sourceCollClass
			, Class<?> destClass
			, Class<?> destCollClass) {
		return (Collection<?>)
				conversion.convert(source
						, collection(sourceCollClass, valueOf(source.getClass()))
						, collection(destCollClass, valueOf(destClass)));
		
	}

}
