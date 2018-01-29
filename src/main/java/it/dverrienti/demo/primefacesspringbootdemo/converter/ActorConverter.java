package it.dverrienti.demo.primefacesspringbootdemo.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import it.dverrienti.demo.primefacesspringbootdemo.backend.model.Actor;
import it.dverrienti.demo.primefacesspringbootdemo.front.model.ActorViewBean;

public class ActorConverter implements Converter<ActorViewBean, Actor>{

	@Override
	public Actor convert(ActorViewBean source) {
		Actor target = new Actor();
		target.setActorId(source.getIdActor());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setLastUpdate(new Date());
		return target;
	}

}
