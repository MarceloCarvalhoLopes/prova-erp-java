package com.senior.provaerp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senior.provaerp.model.Pedido;
import com.senior.provaerp.repository.PedidoRepository;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findbyId(@PathVariable Long id){
		return pedidoRepository.findById(id)
				.map(pedido -> ResponseEntity.ok(pedido))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
}
