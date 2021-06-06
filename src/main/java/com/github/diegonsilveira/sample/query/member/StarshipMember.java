package com.github.diegonsilveira.sample.query.member;

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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "starshipId", "memberName" }))
public class StarshipMember {

	@Id
	@GeneratedValue
	private Long id;

	private String starshipId;
	private String memberName;

	public StarshipMember(String starshipId, String memberName) {
		this.starshipId = starshipId;
		this.memberName = memberName;
	}

}
