package com.github.diegonsilveira.sample.api;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.diegonsilveira.sample.command.CreateTripCommand;
import com.github.diegonsilveira.sample.command.DeleteTripCommand;
import com.github.diegonsilveira.sample.command.JoinPassengerCommand;
import com.github.diegonsilveira.sample.command.LeavePassengerCommand;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class CommandController {

	private final CommandGateway commandGateway;

	public CommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping("/travels")
	public CompletableFuture<String> createTrip(@RequestBody @Valid TripDTO trip) {
		Assert.notNull(trip.getDestination(), "destination is mandatory for travel!");
		String id = trip.getId() == null ? UUID.randomUUID().toString() : trip.getId();

		return commandGateway.send(new CreateTripCommand(id, trip.getDestination(), trip.getDistance()));
	}

	@DeleteMapping("/travels/{tripId}")
	public CompletableFuture<String> deleteTrip(@PathVariable String tripId) {
		Assert.notNull(tripId, "tripId is mandatory for delete a travel!");

		return commandGateway.send(new DeleteTripCommand(tripId));
	}

	@PostMapping("/travels/{tripId}/passengers")
	public CompletableFuture<Void> joinTrip(@PathVariable String tripId, @RequestBody @Valid PassengerDTO passenger) {
		Assert.notNull(passenger.getName(), "name is mandatory for a new passenger!");

		return commandGateway.send(new JoinPassengerCommand(tripId, passenger.getName()));
	}

	@DeleteMapping("/travels/{tripId}/passengers")
	public CompletableFuture<Void> leaveTrip(@PathVariable String tripId, @RequestBody @Valid PassengerDTO passenger) {
		return commandGateway.send(new LeavePassengerCommand(tripId, passenger.getName()));
	}

	@Getter
	@Setter
	public static class TripDTO {
		private String id;

		@NotEmpty
		private String destination;

		@NotEmpty
		private int distance;
	}

	@Getter
	@Setter
	public static class PassengerDTO {
		@NotEmpty
		private String name;
	}

}
