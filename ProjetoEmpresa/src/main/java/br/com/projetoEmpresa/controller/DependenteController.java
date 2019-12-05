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

import br.com.projetoEmpresa.model.Dependente;
import br.com.projetoEmpresa.service.DependenteService;;

@RestController
@RequestMapping("/api")
public class DependenteController {
	
	@Autowired
	private DependenteService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/dependentes")
	public ResponseEntity<Dependente> save(@RequestBody Dependente dependente){
		this.service.save(dependente);
		dependente.add(linkTo(methodOn(DependenteController.class).
				delete(dependente.getCodigo())).
				withRel("Deletar Funcionário" + dependente.getNome()));
		dependente.add(linkTo(methodOn(DependenteController.class).
				get(dependente.getCodigo())).
				withRel("Buscar Funcionário" + dependente.getNome()));
		dependente.add(linkTo(methodOn(DependenteController.class).
				edit(dependente.getCodigo())).
				withRel("Editar Funcionário" + dependente.getNome()));
		
		return new ResponseEntity<Dependente>(dependente, HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/dependentes")
	public ResponseEntity<List<Dependente>> list() {
		List<Dependente> dependentes = this.service.list();
		for (Dependente dependente : dependentes) {
			dependente.add(linkTo(methodOn(DependenteController.class).
					delete(dependente.getCodigo())).
					withRel("Deletar Funcionário" + dependente.getNome()));
			dependente.add(linkTo(methodOn(DependenteController.class).
					get(dependente.getCodigo())).
					withRel("Buscar Funcionário" + dependente.getNome()));
			dependente.add(linkTo(methodOn(DependenteController.class).
					edit(dependente.getCodigo())).
					withRel("Editar Funcionário" + dependente.getNome()));
		}
		return ResponseEntity.ok(this.service.list());
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/dependentes/{id}")
	public ResponseEntity<Dependente> get(@PathVariable Long id){
		Optional<Dependente> dependenteOptional = this.service.getById(id);
		Dependente dependente = dependenteOptional.get();
		if(dependenteOptional.isPresent()) {			
			dependente.add(linkTo(methodOn(DependenteController.class).
					delete(dependente.getCodigo())).
					withRel("Deletar Funcionário" + dependente.getNome()));
			dependente.add(linkTo(methodOn(DependenteController.class).
					edit(dependente.getCodigo())).
					withRel("Editar Funcionário" + dependente.getNome()));
			
		}
		return ResponseEntity.ok(dependente);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/dependentes/{id}")
	public ResponseEntity<Dependente> delete(@PathVariable Long id) {
		Optional<Dependente> dependente = this.service.getById(id);
		if(dependente.isPresent()) {
			this.service.remove(dependente.get().getCodigo());
			return ResponseEntity.ok().body(dependente.get());
		} else {			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/dependentes/{id}")
	public ResponseEntity<Dependente> edit(@PathVariable Long id){
		Optional<Dependente> dependenteOptional = this.service.getById(id);
		Dependente dependente = dependenteOptional.get();
		if(dependenteOptional.isPresent()) {			
			dependente.add(linkTo(methodOn(DependenteController.class).
					delete(dependente.getCodigo())).
					withRel("Deletar Funcionário" + dependente.getNome()));
			dependente.add(linkTo(methodOn(DependenteController.class).
					get(dependente.getCodigo())).
					withRel("Buscar Funcionário" + dependente.getNome()));
			return ResponseEntity.ok(dependente);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	
	
}
