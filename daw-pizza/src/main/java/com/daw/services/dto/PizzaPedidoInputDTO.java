package com.daw.services.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PizzaPedidoInputDTO {
	
	private int id;
	private Integer idPizza;
	private Integer idPedido;
	private Double cantidad;

}
