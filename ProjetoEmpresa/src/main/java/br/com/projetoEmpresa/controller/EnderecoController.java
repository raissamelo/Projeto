package br.com.projetoEmpresa.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoEmpresa.model.Endereco;
import br.com.projetoEmpresa.service.EnderecoService;

@RestController
@RequestMapping("/api")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/enderecos")
	public ResponseEntity<Endereco> save(@RequestBody Endereco endereco){
		this.service.save(endereco);
		endereco.add(linkTo(methodOn(EnderecoController.class).
				delete(endereco.getCodigo())).
				withRel("Deletar Funcionário" + endereco.getRua()));
		endereco.add(linkTo(methodOn(EnderecoController.class).
				get(endereco.getCodigo())).
				withRel("Buscar Funcionário" + endereco.getRua()));
		endereco.add(linkTo(methodOn(EnderecoController.class).
				edit(endereco.getCodigo())).
				withRel("Editar Funcionário" + endereco.getRua()));
		
		return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/enderecos")
	public ResponseEntity<List<Endereco>> list() {
		List<Endereco> enderecos = this.service.list();
		for (Endereco endereco : enderecos) {
			endereco.add(linkTo(methodOn(EnderecoController.class).
					delete(endereco.getCodigo())).
					withRel("Deletar Funcionário" + endereco.getRua()));
			endereco.add(linkTo(methodOn(EnderecoController.class).
					get(endereco.getCodigo())).
					withRel("Buscar Funcionário" + endereco.getRua()));
			endereco.add(linkTo(methodOn(EnderecoController.class).
					edit(endereco.getCodigo())).
					withRel("Editar Funcionário" + endereco.getRua()));
		}
		return ResponseEntity.ok(this.service.list());
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/enderecos/{id}")
	public ResponseEntity<Endereco> get(@PathVariable Long id){
		Optional<Endereco> enderecoOptional = this.service.getById(id);
		Endereco endereco = enderecoOptional.get();
		if(enderecoOptional.isPresent()) {			
			endereco.add(linkTo(methodOn(EnderecoController.class).
					delete(endereco.getCodigo())).
					withRel("Deletar Funcionário" + endereco.getRua()));
			endereco.add(linkTo(methodOn(EnderecoController.class).
					edit(endereco.getCodigo())).
					withRel("Editar Funcionário" + endereco.getRua()));
			
		}
		return ResponseEntity.ok(endereco);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/enderecos/{id}")
	public ResponseEntity<Endereco> delete(@PathVariable Long id) {
		Optional<Endereco> endereco = this.service.getById(id);
		if(endereco.isPresent()) {
			this.service.remove(endereco.get().getCodigo());
			return ResponseEntity.ok().body(endereco.get());
		} else {			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/enderecos/{id}")
	public ResponseEntity<Endereco> edit(@PathVariable Long id){
		Optional<Endereco> enderecoOptional = this.service.getById(id);
		Endereco endereco = enderecoOptional.get();
		if(enderecoOptional.isPresent()) {			
			endereco.add(linkTo(methodOn(EnderecoController.class).
					delete(endereco.getCodigo())).
					withRel("Deletar Funcionário" + endereco.getRua()));
			endereco.add(linkTo(methodOn(EnderecoController.class).
					get(endereco.getCodigo())).
					withRel("Buscar Funcionário" + endereco.getRua()));
			return ResponseEntity.ok(endereco);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	
	
}
