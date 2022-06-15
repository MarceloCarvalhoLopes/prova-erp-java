package com.senior.provaerp.resource;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senior.provaerp.model.ItemPedido;
import com.senior.provaerp.model.Pedido;
import com.senior.provaerp.repository.ItemPedidoRepository;
import com.senior.provaerp.repository.PedidoRepository;
import com.senior.provaerp.service.ProdutoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findbyId(@PathVariable Long id){
		return pedidoRepository.findById(id)
				.map(pedido -> ResponseEntity.ok(pedido))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	public ResponseEntity<Pedido> create(@RequestBody Pedido pedido,  HttpServletResponse response){
		
		Pedido pedidoSalvo = pedidoRepository.save(pedido);	
		
		for (ItemPedido ip : pedido.getItens()) {
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedidoSalvo);
		}
		
		itemPedidoRepository.saveAll(pedidoSalvo.getItens());
		
		
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(pedidoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
		
	}
	
	
}
