package com.github.diegonsilveira.sample.query.trip;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.github.diegonsilveira.sample.events.PassengerJoinedTripEvent;
import com.github.diegonsilveira.sample.events.PassengerLeftTripEvent;
import com.github.diegonsilveira.sample.events.TripCreatedEvent;
import com.github.diegonsilveira.sample.events.TripDeletedEvent;
import com.github.diegonsilveira.sample.query.TripByIdQuery;
import com.github.diegonsilveira.sample.query.TripQuery;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Component
public class TripProjection {

	private final TripRepository repo;

	@QueryHandler
	public List<Trip> handle(TripQuery query) {
		return repo.findAll();
	}

	@QueryHandler
	public List<Trip> handle(TripByIdQuery query) {
		return repo.findById(query.getTripId()).stream().collect(Collectors.toList());
	}

	@EventHandler
	public void on(TripCreatedEvent event) {
		log.info("Event received: TripCreatedEvent");
		repo.save(new Trip(event.getTripId(), event.getDestination(), event.getDistance()));
	}

	@EventHandler
	public void on(TripDeletedEvent event) {
		log.info("Event received: TripDeletedEvent");
		repo.deleteById(event.getTripId());
	}

	@EventHandler
	public void on(PassengerJoinedTripEvent event) {
		log.info("Event received: PassengerJoinedTripEvent");
		repo.getById(event.getTripId()).addPassenger();
	}

	@EventHandler
	public void on(PassengerLeftTripEvent event) {
		log.info("Event received: PassengerLeftTripEvent");
		repo.getById(event.getTripId()).removePassenger();
	}
}