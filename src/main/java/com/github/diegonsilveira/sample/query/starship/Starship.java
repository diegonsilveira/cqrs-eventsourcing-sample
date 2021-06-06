package com.github.diegonsilveira.sample.query.starship;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Starship {

	@Id
	private String starshipId;

	private String name;

	private int totalMembers;

	public Starship(String starshipId, String name) {
		this.starshipId = starshipId;
		this.name = name;
	}

	public void addMember() {
		this.totalMembers++;
	}

	public void removeMember() {
		this.totalMembers--;
	}
}