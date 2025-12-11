package com.daw.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.repositories.PedidoRepository;
import com.daw.services.dto.PedidoDTO;
import com.daw.services.dto.PizzaPedidoInputDTO;
import com.daw.services.dto.PizzaPedidoOutputDTO;
import com.daw.services.exceptions.ClienteNotFoundException;
import com.daw.services.exceptions.PedidoNotFoundException;
import com.daw.services.mappers.PedidoMapper;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PizzaPedidoService pizzaPedidoService;

	public List<PedidoDTO> findAll() {
		return PedidoMapper.toDTOsFuncional(this.pedidoRepository.findAll());
	}

	public PedidoDTO findById(int idPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		return PedidoMapper.toDTO(this.pedidoRepository.findById(idPedido).get());
	}

	public Pedido findEntityById(int idPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		return this.pedidoRepository.findById(idPedido).get();
	}

	public PedidoDTO create(Pedido pedido) {
		pedido.setId(0);
		pedido.setFecha(LocalDateTime.now());
		pedido.setTotal(0.0);

		return PedidoMapper.toDTO(this.pedidoRepository.save(pedido));
	}

	public PedidoDTO update(int idPedido, Pedido pedido) {
		Pedido pedidoBD = this.findEntityById(idPedido);
		pedidoBD.setIdCliente(pedido.getIdCliente());
		pedidoBD.setFecha(pedido.getFecha());
		pedidoBD.setTotal(pedido.getTotal());
		pedidoBD.setMetodo(pedido.getMetodo());
		pedidoBD.setNotas(pedido.getNotas());

		return PedidoMapper.toDTO(this.pedidoRepository.save(pedidoBD));
	}

	public void deleteById(int idPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		this.pedidoRepository.deleteById(idPedido);
	}

	// CRUDs PizzaPedido
	// findAll
	public List<PizzaPedidoOutputDTO> findPizzasByIdPedido(int idPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		return this.pizzaPedidoService.findByIdPedido(idPedido);
	}

	// findById
	public PizzaPedidoOutputDTO findPizzaPedidoById(int idPedido, int idPizzaPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		return this.pizzaPedidoService.findDTOById(idPizzaPedido);
	}

	// create
	public PizzaPedidoOutputDTO createPizzaPedido(int idPedido, PizzaPedidoInputDTO dto) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		PizzaPedidoOutputDTO dtoOutput = this.pizzaPedidoService.createDTO(dto);
		this.recalcularTotal(idPedido);
		
		return dtoOutput;
	}

	// update
	public PizzaPedidoOutputDTO updatePizzaPedido(int idPedido, int idPizzaPedido, PizzaPedidoInputDTO dto) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		PizzaPedidoOutputDTO dtoOutput = this.pizzaPedidoService.updateDTO(idPizzaPedido, dto);
		this.recalcularTotal(idPedido);
		
		return dtoOutput;
	}
	
	// delete
	public void deletePizzaPedidoById(int idPedido, int idPizzaPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoNotFoundException("El ID indicado no existe. ");
		}

		this.pizzaPedidoService.deleteById(idPizzaPedido);
		this.recalcularTotal(idPedido);
	}
	
	//Funciones auxiliares
	private void recalcularTotal(int idPedido) {
		Pedido pedido = this.findEntityById(idPedido);
		double total = 0.0;
		
		for(PizzaPedidoOutputDTO pp : this.pizzaPedidoService.findByIdPedido(pedido.getId())) {
			total += pp.getPrecio();
		}
		
		pedido.setTotal(total);
		this.pedidoRepository.save(pedido);
	}

}
