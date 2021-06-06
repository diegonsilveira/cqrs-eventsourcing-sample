package com.github.diegonsilveira.sample.api;

import java.util.List;
import java.util.concurrent.Future;

import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.diegonsilveira.sample.query.StarshipMembersQuery;
import com.github.diegonsilveira.sample.query.StarshipQuery;
import com.github.diegonsilveira.sample.query.starship.Starship;

@RestController
public class QueryController {

	private final QueryGateway queryGateway;

	public QueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@GetMapping("starships")
	public Future<List<Starship>> listStarships() {
		return queryGateway.query(new StarshipQuery(), new MultipleInstancesResponseType<>(Starship.class));
	}

	@GetMapping("/starships/{starshipId}/members")
	public Future<List<String>> membersInStarship(@PathVariable String starshipId) {
		return queryGateway.query(new StarshipMembersQuery(starshipId),
				new MultipleInstancesResponseType<>(String.class));
	}
}
