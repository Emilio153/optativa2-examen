package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
