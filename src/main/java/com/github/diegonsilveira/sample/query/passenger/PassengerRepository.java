package com.github.diegonsilveira.sample.query.passenger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	List<Passenger> findPassengerByTripId(String tripId);

	void deleteByNameAndTripId(String name, String tripId);
}