package com.github.diegonsilveira.sample.query.passenger;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.github.diegonsilveira.sample.events.PassengerJoinedTripEvent;
import com.github.diegonsilveira.sample.events.PassengerLeftTripEvent;
import com.github.diegonsilveira.sample.query.TripPassengerQuery;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Component
public class PassengerProjection {

	private final PassengerRepository repository;

	@QueryHandler
	public List<String> handle(TripPassengerQuery query) {
		return repository.findPassengerByTripId(query.getTripId()).stream().map(Passenger::getName).sorted()
				.collect(Collectors.toList());
	}

	@EventHandler
	public void on(PassengerJoinedTripEvent event) {
		log.info("Event received: PassengerJoinedTripEvent");
		repository.save(new Passenger(event.getTripId(), event.getName()));
	}

	@EventHandler
	public void on(PassengerLeftTripEvent event) {
		log.info("Event received: PassengerLeftTripEvent");
		repository.deleteByNameAndTripId(event.getName(), event.getTripId());
	}
}
