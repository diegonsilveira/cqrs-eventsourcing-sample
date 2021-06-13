package com.github.diegonsilveira.sample.aggregate;

import java.util.HashSet;
import java.util.Set;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.github.diegonsilveira.sample.command.CreateTripCommand;
import com.github.diegonsilveira.sample.command.DeleteTripCommand;
import com.github.diegonsilveira.sample.command.JoinPassengerCommand;
import com.github.diegonsilveira.sample.command.LeavePassengerCommand;
import com.github.diegonsilveira.sample.events.PassengerJoinedTripEvent;
import com.github.diegonsilveira.sample.events.PassengerLeftTripEvent;
import com.github.diegonsilveira.sample.events.TripCreatedEvent;
import com.github.diegonsilveira.sample.events.TripDeletedEvent;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@Aggregate
public class TripAggregate {

	@AggregateIdentifier
	private String tripId;
	private String destination;
	private int distance;
	private Set<String> passengers;

	@CommandHandler
	public TripAggregate(CreateTripCommand command) {
		log.info("Send command: {}", command.getClass());

		AggregateLifecycle
				.apply(new TripCreatedEvent(command.getTripId(), command.getDestination(), command.getDistance()));
	}

	@CommandHandler
	public void handle(DeleteTripCommand command) {
		log.info("Send command: {}", command.getClass());

		AggregateLifecycle.apply(new TripDeletedEvent(command.getTripId()));
	}

	@CommandHandler
	public void handle(JoinPassengerCommand command) {
		if (!passengers.contains(command.getName())) {
			log.info("Send command: {}", command.getClass());

			AggregateLifecycle.apply(new PassengerJoinedTripEvent(tripId, command.getName()));
		}
	}

	@CommandHandler
	public void handle(LeavePassengerCommand command) {
		if (passengers.contains(command.getName())) {
			log.info("Send command: {}", command.getClass());

			AggregateLifecycle.apply(new PassengerLeftTripEvent(tripId, command.getName()));
		}
	}

	@EventSourcingHandler
	protected void on(TripCreatedEvent event) {
		log.info("Send event: {}", event.getClass());

		this.tripId = event.getTripId();
		this.destination = event.getDestination();
		this.distance = event.getDistance();
		this.passengers = new HashSet<>();
	}

	@EventSourcingHandler
	protected void on(PassengerJoinedTripEvent event) {
		log.info("Send event: {}", event.getClass());

		this.passengers.add(event.getName());
	}

	@EventSourcingHandler
	protected void on(PassengerLeftTripEvent event) {
		log.info("Send event: {}", event.getClass());

		this.passengers.remove(event.getName());
	}

	@EventSourcingHandler
	protected void on(TripDeletedEvent event) {
		log.info("Send event: {}", event.getClass());
	}
}
