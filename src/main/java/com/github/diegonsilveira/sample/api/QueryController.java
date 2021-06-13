package com.github.diegonsilveira.sample.api;

import java.util.List;
import java.util.concurrent.Future;

import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.diegonsilveira.sample.query.TripPassengerQuery;
import com.github.diegonsilveira.sample.query.TripQuery;
import com.github.diegonsilveira.sample.query.trip.Trip;

@RestController
public class QueryController {

	private final QueryGateway queryGateway;

	public QueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@GetMapping("travels")
	public Future<List<Trip>> listTravels() {
		return queryGateway.query(new TripQuery(), new MultipleInstancesResponseType<>(Trip.class));
	}

	@GetMapping("/travels/{tripId}/passengers")
	public Future<List<String>> passengersInTravel(@PathVariable String tripId) {
		return queryGateway.query(new TripPassengerQuery(tripId),
				new MultipleInstancesResponseType<>(String.class));
	}
}
