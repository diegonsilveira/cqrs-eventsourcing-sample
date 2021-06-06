package com.github.diegonsilveira.sample.api;

import java.util.UUID;
import java.util.concurrent.Future;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.diegonsilveira.sample.command.CreateStarshipCommand;
import com.github.diegonsilveira.sample.command.JoinStarshipCommand;
import com.github.diegonsilveira.sample.command.LeaveStarshipCommand;

import lombok.Getter;
import lombok.Setter;

@RestController
public class CommandController {

	private final CommandGateway commandGateway;

	public CommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping("/starships")
	public Future<String> createStarship(@RequestBody @Valid Starship starship) {
		Assert.notNull(starship.getName(), "name is mandatory for starship!");

		String starshipId = starship.getStarshipId() == null ? UUID.randomUUID().toString() : starship.getStarshipId();
		return commandGateway.send(new CreateStarshipCommand(starshipId, starship.getName()));
	}

	@PostMapping("/starships/{starshipId}/members")
	public Future<Void> joinStarship(@PathVariable String starshipId, @RequestBody @Valid Member member) {
		Assert.notNull(member.getMemberName(), "name is mandatory for a member!");
		
		return commandGateway.send(new JoinStarshipCommand(starshipId, member.getMemberName()));
	}

	@DeleteMapping("/starships/{starshipId}/members")
	public Future<Void> leaveStarship(@PathVariable String starshipId, @RequestBody @Valid Member member) {
		Assert.notNull(member.getMemberName(), "name is mandatory to left starship!");
		
		return commandGateway.send(new LeaveStarshipCommand(starshipId, member.getMemberName()));
	}

	@Getter
	@Setter
	public static class Starship {
		private String starshipId;

		@NotEmpty
		private String name;
	}

	@Getter
	@Setter
	public static class Member {
		@NotEmpty
		private String memberName;
	}

}
