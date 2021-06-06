package com.github.diegonsilveira.sample.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLeftStarshipEvent {
	
	private String starshipId;
	private String memberName;
}
