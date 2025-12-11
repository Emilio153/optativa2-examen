package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.PizzaPedido;

public interface PizzaPedidoRepository extends JpaRepository<PizzaPedido, Integer> {
	
	List<PizzaPedido> findByIdPedido(int idPedido);

}
