package com.ishant.mscs.repository;

import com.ishant.mscs.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetroCardRepository extends JpaRepository<Card, Long> {

}
