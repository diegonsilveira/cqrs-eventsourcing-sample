package com.github.diegonsilveira.sample.query.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipMemberRepository extends JpaRepository<StarshipMember, Long> {

	List<StarshipMember> findStarshipMembersByStarshipId(String starshipId);

	void deleteByMemberNameAndStarshipId(String participant, String starshipId);
}