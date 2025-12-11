package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
