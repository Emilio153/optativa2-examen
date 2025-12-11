package com.daw.services.mappers;

import java.util.List;

import com.daw.persistence.entities.PizzaPedido;
import com.daw.services.dto.PizzaPedidoInputDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;

public class PizzaPedidoMapper {
	
	public static PizzaPedidoOutputDTO toDTO(PizzaPedido pizzaPedido) {
		PizzaPedidoOutputDTO dto = new PizzaPedidoOutputDTO();
		
		dto.setId(pizzaPedido.getId());
		dto.setCantidad(pizzaPedido.getCantidad());
		dto.setPrecio(pizzaPedido.getPrecio());
		dto.setIdPizza(pizzaPedido.getIdPizza());
		dto.setPizza(pizzaPedido.getPizza().getNombre());
		
		return dto;
	}
	
	public static List<PizzaPedidoOutputDTO> toDtos(List<PizzaPedido> pizzaPedidos){
		return pizzaPedidos.stream().map(pp -> toDTO(pp)).toList();
	}
	
	public static PizzaPedido toEntity(PizzaPedidoInputDTO dto) {
		PizzaPedido entity = new PizzaPedido();
		
		entity.setId(dto.getId());
		entity.setIdPizza(dto.getIdPizza());
		entity.setIdPedido(dto.getIdPedido());
		entity.setCantidad(dto.getCantidad());
		
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
	

}
