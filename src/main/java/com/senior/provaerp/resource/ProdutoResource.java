package com.senior.provaerp.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.senior.provaerp.model.Produto;
import com.senior.provaerp.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired 
	ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> list(){
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Produto> create(@Valid @RequestBody Produto produto, HttpServletResponse response) {
		Produto produtoSalvo = produtoRepository.save(produto);
		
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(produtoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update (@PathVariable Long id, @Valid @RequestBody Produto produto){
		Produto produtoSalvo = produtoRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(produto, produtoSalvo,"id");
		produtoRepository.save(produtoSalvo);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT )
	public void delete(@PathVariable Long id) {
		produtoRepository.deleteById(id);
	}
	
}
