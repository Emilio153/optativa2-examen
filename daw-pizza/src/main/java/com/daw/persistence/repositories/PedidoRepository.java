package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
