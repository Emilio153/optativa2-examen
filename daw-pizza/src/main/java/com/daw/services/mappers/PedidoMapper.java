package com.daw.services.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.entities.PizzaPedido;
import com.daw.services.dto.PedidoDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;

public class PedidoMapper {
	
	public static PedidoDTO toDTO(Pedido pedido) {
		PedidoDTO dto = new PedidoDTO();
		
		//Transformo la entidad en el DTO
		dto.setId(pedido.getId());
		dto.setFecha(pedido.getFecha());
		dto.setMetodo(pedido.getMetodo());
		dto.setTotal(pedido.getTotal());
		
		//Esto viene de cliente
		dto.setCliente(pedido.getCliente().getNombre());
		dto.setTelefono(pedido.getCliente().getTelefono());
		dto.setDireccion(pedido.getCliente().getDireccion());
		
		dto.setNotas(pedido.getNotas());		
		dto.setNumeroPizzas(pedido.getPizzaPedidos().size());
		
		List<PizzaPedidoOutputDTO> pizzas = new ArrayList<PizzaPedidoOutputDTO>();
		
		for(PizzaPedido pp : pedido.getPizzaPedidos()) {
			PizzaPedidoOutputDTO ppDTO = PizzaPedidoMapper.toDTO(pp);
			
			pizzas.add(ppDTO);
		}
		
		dto.setPizzas(pizzas);
		
		return dto;
	}
	
	public static List<PedidoDTO> toDTOsFuncional(List<Pedido> pedidos){
		return pedidos.stream()
				.map(ped -> PedidoMapper.toDTO(ped))
				.collect(Collectors.toList());
	}
	
	public static List<PedidoDTO> toDTOsDeclarativo(List<Pedido> pedidos){
		List<PedidoDTO> dtos = new ArrayList<PedidoDTO>();
		
		for(Pedido p : pedidos) {
			dtos.add(PedidoMapper.toDTO(p));
		}
		
		return dtos;
	}

	
}
