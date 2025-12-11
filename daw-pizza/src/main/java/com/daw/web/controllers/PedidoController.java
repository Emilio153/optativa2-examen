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
//		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.create(pedido));
//		}
//		catch(PizzaException ex) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//		}
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
	
	//CRUDs de PizzaPedido
	//findAll
	@GetMapping("/{idPedido}/pizzas")
	public ResponseEntity<?> listPizzaPedido(@PathVariable int idPedido){
		try {
			return ResponseEntity.ok(this.pedidoService.findPizzasByIdPedido(idPedido));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	//findById
	@GetMapping("/{idPedido}/pizzas/{idPizzaPedido}")
	public ResponseEntity<?> findPizzaPedidoById(@PathVariable int idPedido, @PathVariable int idPizzaPedido){
		try {
			return ResponseEntity.ok(this.pedidoService.findPizzaPedidoById(idPedido, idPizzaPedido));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(PizzaPedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}		
	}
	
	//create
	@PostMapping("/{idPedido}/pizzas")
	public ResponseEntity<?> create(@PathVariable int idPedido, @RequestBody PizzaPedidoInputDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.createPizzaPedido(idPedido, dto));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	//update
	@PutMapping("/{idPedido}/pizzas/{idPizzaPedido}")
	public ResponseEntity<?> update(@PathVariable int idPedido, @PathVariable int idPizzaPedido, @RequestBody PizzaPedidoInputDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.updatePizzaPedido(idPedido, idPizzaPedido, dto));
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(PizzaPedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}		
	}
	//delete
	@DeleteMapping("/{idPedido}/pizzas/{idPizzaPedido}")
	public ResponseEntity<?> delete(@PathVariable int idPedido, @PathVariable int idPizzaPedido){
		try {
			this.pedidoService.deletePizzaPedidoById(idPedido, idPizzaPedido);
			return ResponseEntity.ok().build();
		}
		catch(PedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch(PizzaPedidoNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}		
	}
	
	
	
	
	
	
	
	
	
}
