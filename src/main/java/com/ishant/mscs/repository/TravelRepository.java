package com.ishant.mscs.repository;

import com.ishant.mscs.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Journey,Long> {
}
