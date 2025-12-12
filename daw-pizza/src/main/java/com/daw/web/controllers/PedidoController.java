package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pedido;
import com.daw.services.PedidoService;
import com.daw.services.PizzaPedidoService; // IMPORTANTE: Importar el servicio
import com.daw.services.dto.PedidoDTO;
import com.daw.services.dto.PizzaPedidoInputDTO;
import com.daw.services.exceptions.PedidoException;
import com.daw.services.exceptions.PedidoNotFoundException;
import com.daw.services.exceptions.PizzaPedidoNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	// NUEVA INYECCIÓN para usar la lógica de ofertas
	@Autowired
	private PizzaPedidoService pizzaPedidoService;
	
	//CRUDs de Pedido
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> list(){
		return ResponseEntity.ok(this.pedidoService.findAll());
	}
	
	@GetMapping("/{idPedido}")
	public ResponseEntity<?> findById(@PathVariable int idPedido){
		try {
			return ResponseEntity.ok(this.pedidoService.findById(idPedido));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pedido pedido){
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.create(pedido));
	}
	
	@PutMapping("/{idPedido}")
	public ResponseEntity<?> update(@PathVariable int idPedido, @RequestBody Pedido pedido){
		try {
			return ResponseEntity.ok(this.pedidoService.update(idPedido, pedido));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(PedidoException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{idPedido}")
	public ResponseEntity<?> delete(@PathVariable int idPedido){
		try {
			this.pedidoService.deleteById(idPedido);
			return ResponseEntity.ok().build();
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	// -------------------------------------------------------------------
	// CRUDs de PizzaPedido (MODIFICADOS PARA USAR PizzaPedidoService)
	// -------------------------------------------------------------------
	
	//findAll (Listar las pizzas de un pedido)
	@GetMapping("/{idPedido}/pizzas")
	public ResponseEntity<?> listPizzaPedido(@PathVariable int idPedido){
		try {
			// Usamos el servicio de PizzaPedido para buscar por idPedido
			return ResponseEntity.ok(this.pizzaPedidoService.findByIdPedido(idPedido));
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	//findById (Una linea concreta)
	@GetMapping("/{idPedido}/pizzas/{idPizzaPedido}")
	public ResponseEntity<?> findPizzaPedidoById(@PathVariable int idPedido, @PathVariable int idPizzaPedido){
		try {
			// Llamamos directamente al servicio de PizzaPedido
			return ResponseEntity.ok(this.pizzaPedidoService.findDTOById(idPizzaPedido));
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}		
	}
	
	// CREATE (AQUÍ SE APLICA LA OFERTA)
	@PostMapping("/{idPedido}/pizzas")
	public ResponseEntity<?> create(@PathVariable int idPedido, @RequestBody PizzaPedidoInputDTO dto){
		try {
			// Aseguramos que el DTO tenga el ID del pedido de la URL
			dto.setIdPedido(idPedido);
			
			// Llamamos a createDTO de PizzaPedidoService (donde pusimos la lógica de precios)
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pizzaPedidoService.createDTO(dto));
		}
		catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	// UPDATE (AQUÍ TAMBIÉN SE APLICA LA OFERTA)
	@PutMapping("/{idPedido}/pizzas/{idPizzaPedido}")
	public ResponseEntity<?> update(@PathVariable int idPedido, @PathVariable int idPizzaPedido, @RequestBody PizzaPedidoInputDTO dto){
		try {
			dto.setIdPedido(idPedido);
			dto.setId(idPizzaPedido);
			
			// Llamamos a updateDTO de PizzaPedidoService
			return ResponseEntity.ok(this.pizzaPedidoService.updateDTO(idPizzaPedido, dto));
		}
		catch(Exception ex) { // Capturamos Exception genérica para cubrir todas
			return ResponseEntity.badRequest().body(ex.getMessage());
		}		
	}

	// DELETE
	@DeleteMapping("/{idPedido}/pizzas/{idPizzaPedido}")
	public ResponseEntity<?> delete(@PathVariable int idPedido, @PathVariable int idPizzaPedido){
		try {
			this.pizzaPedidoService.deleteById(idPizzaPedido);
			return ResponseEntity.ok().build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}		
	}
}