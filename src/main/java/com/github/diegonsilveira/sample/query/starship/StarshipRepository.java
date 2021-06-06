package com.github.diegonsilveira.sample.query.starship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipRepository extends JpaRepository<Starship, String> {
}