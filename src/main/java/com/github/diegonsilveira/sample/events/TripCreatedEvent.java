package com.github.diegonsilveira.sample.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripCreatedEvent {

	private String tripId;
	private String destination;
	private int distance;

}
