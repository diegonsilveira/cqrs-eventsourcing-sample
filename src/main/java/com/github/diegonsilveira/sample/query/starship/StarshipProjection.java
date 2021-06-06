package com.github.diegonsilveira.sample.query.starship;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.github.diegonsilveira.sample.events.MemberJoinedStarshipEvent;
import com.github.diegonsilveira.sample.events.MemberLeftStarshipEvent;
import com.github.diegonsilveira.sample.events.StarshipCreatedEvent;
import com.github.diegonsilveira.sample.query.StarshipQuery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StarshipProjection {

	private final StarshipRepository repo;

	@QueryHandler
	public List<Starship> handle(StarshipQuery query) {
		return repo.findAll();
	}

	@EventHandler
	public void on(StarshipCreatedEvent event) {
		repo.save(new Starship(event.getStarshipId(), event.getName()));
	}

	@EventHandler
	public void on(MemberJoinedStarshipEvent event) {
		repo.getById(event.getStarshipId()).addMember();
	}

	@EventHandler
	public void on(MemberLeftStarshipEvent event) {
		repo.getById(event.getStarshipId()).removeMember();
	}
}