package com.github.diegonsilveira.sample.query.passenger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "tripId", "name" }))
public class Passenger {

	@Id
	@GeneratedValue
	private Long id;

	private String tripId;

	private String name;

	public Passenger(String tripId, String name) {
		this.tripId = tripId;
		this.name = name;
	}

}
