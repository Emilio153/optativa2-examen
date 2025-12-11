package com.daw.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza_pedido")
@Getter
@Setter
@NoArgsConstructor
public class PizzaPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "id_pizza")
	private int idPizza;
	
	@Column(name = "id_pedido")
	private int idPedido;
	
	@Column(columnDefinition = "DECIMAL(2,1)")
	private double cantidad;

	@Column(columnDefinition = "DECIMAL(5,2)")
	private double precio;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_pizza", referencedColumnName = "id", insertable = false, updatable = false)
	private Pizza pizza;

}
