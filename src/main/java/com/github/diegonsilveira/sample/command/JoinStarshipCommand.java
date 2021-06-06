package com.github.diegonsilveira.sample.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinStarshipCommand {

	@TargetAggregateIdentifier
	private String starshipId;
	
	private String memberName;
}
