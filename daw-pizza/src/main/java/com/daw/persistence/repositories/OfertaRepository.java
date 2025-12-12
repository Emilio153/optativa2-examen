package com.daw.persistence.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daw.persistence.entities.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Integer> {
    List<Oferta> findByIdPizza(int idPizza);
    
    // Para buscar la oferta activa de una pizza concreta
    Optional<Oferta> findByIdPizzaAndActivaTrue(int idPizza);
}