package com.github.diegonsilveira.sample.query.member;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.github.diegonsilveira.sample.events.MemberJoinedStarshipEvent;
import com.github.diegonsilveira.sample.events.MemberLeftStarshipEvent;
import com.github.diegonsilveira.sample.query.StarshipMembersQuery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StarshipMemberProjection {

	private final StarshipMemberRepository repository;

	@QueryHandler
	public List<String> handle(StarshipMembersQuery query) {
		return repository.findStarshipMembersByStarshipId(query.getStarshipId()).stream()
				.map(StarshipMember::getMemberName).sorted().collect(Collectors.toList());
	}

	@EventHandler
	public void on(MemberJoinedStarshipEvent event) {
		repository.save(new StarshipMember(event.getStarshipId(), event.getMemberName()));
	}

	@EventHandler
	public void on(MemberLeftStarshipEvent event) {
		repository.deleteByMemberNameAndStarshipId(event.getMemberName(), event.getStarshipId());
	}
}
