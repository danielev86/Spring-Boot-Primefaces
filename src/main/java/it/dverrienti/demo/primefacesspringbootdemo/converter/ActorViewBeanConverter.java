package it.dverrienti.demo.primefacesspringbootdemo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.dverrienti.demo.primefacesspringbootdemo.backend.model.Actor;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorViewBean;

@Component
public class ActorViewBeanConverter implements Converter<Actor, ActorViewBean>{

	@Override
	public ActorViewBean convert(Actor source) {
		ActorViewBean target = new ActorViewBean();
		target.setIdActor(source.getActorId());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setLastUpdate(source.getLastUpdate());
		return target;
	}

}
