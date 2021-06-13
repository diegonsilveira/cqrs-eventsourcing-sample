package com.github.diegonsilveira.sample.query.trip;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Trip {

	@Id
	private String id;

	private String destination;

	private int distance;

	private int totalPassengers;

	public Trip(String id, String destination, int distance) {
		this.id = id;
		this.destination = destination;
		this.distance = distance;
	}

	public void addPassenger() {
		this.totalPassengers++;
	}

	public void removePassenger() {
		this.totalPassengers--;
	}
}