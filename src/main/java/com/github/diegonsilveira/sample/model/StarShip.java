package com.github.diegonsilveira.sample.model;

import java.util.HashSet;
import java.util.Set;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.github.diegonsilveira.sample.command.CreateStarshipCommand;
import com.github.diegonsilveira.sample.command.JoinStarshipCommand;
import com.github.diegonsilveira.sample.command.LeaveStarshipCommand;
import com.github.diegonsilveira.sample.events.MemberJoinedStarshipEvent;
import com.github.diegonsilveira.sample.events.MemberLeftStarshipEvent;
import com.github.diegonsilveira.sample.events.StarshipCreatedEvent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Aggregate
public class StarShip {

	@AggregateIdentifier
	private String starShipId;
	private String name;
	private Set<String> members;

	@CommandHandler
	public StarShip(CreateStarshipCommand command) {
		AggregateLifecycle.apply(new StarshipCreatedEvent(command.getStarshipId(), command.getName()));
	}

	@CommandHandler
	public void handle(JoinStarshipCommand command) {
		if (!members.contains(command.getMemberName()))
			AggregateLifecycle.apply(new MemberJoinedStarshipEvent(starShipId, command.getMemberName()));
	}

	@CommandHandler
	public void handle(LeaveStarshipCommand command) {
		if (members.contains(command.getMemberName()))
			AggregateLifecycle.apply(new MemberLeftStarshipEvent(starShipId, command.getMemberName()));
	}

	@EventSourcingHandler
	protected void on(StarshipCreatedEvent event) {
		this.starShipId = event.getStarshipId();
		this.name = event.getName();
		this.members = new HashSet<>();
	}

	@EventSourcingHandler
	protected void on(MemberJoinedStarshipEvent event) {
		this.members.add(event.getMemberName());
	}

	@EventSourcingHandler
	protected void on(MemberLeftStarshipEvent event) {
		this.members.remove(event.getMemberName());
	}
}
