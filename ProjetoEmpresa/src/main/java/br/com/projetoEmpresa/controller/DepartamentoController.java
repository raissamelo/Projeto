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

import br.com.projetoEmpresa.model.Departamento;
import br.com.projetoEmpresa.service.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/departamentos")
	public ResponseEntity<Departamento> save(@RequestBody Departamento departamento){
		this.service.save(departamento);
		departamento.add(linkTo(methodOn(DepartamentoController.class).
				delete(departamento.getCodigo())).
				withRel("Deletar Funcionário" + departamento.getNome()));
		departamento.add(linkTo(methodOn(DepartamentoController.class).
				get(departamento.getCodigo())).
				withRel("Buscar Funcionário" + departamento.getNome()));
		departamento.add(linkTo(methodOn(DepartamentoController.class).
				edit(departamento.getCodigo())).
				withRel("Editar Funcionário" + departamento.getNome()));
		
		return new ResponseEntity<Departamento>(departamento, HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/departamentos")
	public ResponseEntity<List<Departamento>> list() {
		List<Departamento> departamentos = this.service.list();
		for (Departamento departamento : departamentos) {
			departamento.add(linkTo(methodOn(DepartamentoController.class).
					delete(departamento.getCodigo())).
					withRel("Deletar Funcionário" + departamento.getNome()));
			departamento.add(linkTo(methodOn(DepartamentoController.class).
					get(departamento.getCodigo())).
					withRel("Buscar Funcionário" + departamento.getNome()));
			departamento.add(linkTo(methodOn(DepartamentoController.class).
					edit(departamento.getCodigo())).
					withRel("Editar Funcionário" + departamento.getNome()));
		}
		return ResponseEntity.ok(this.service.list());
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/departamentos/{id}")
	public ResponseEntity<Departamento> get(@PathVariable Long id){
		Optional<Departamento> departamentoOptional = this.service.getById(id);
		Departamento departamento = departamentoOptional.get();
		if(departamentoOptional.isPresent()) {			
			departamento.add(linkTo(methodOn(DepartamentoController.class).
					delete(departamento.getCodigo())).
					withRel("Deletar Funcionário" + departamento.getNome()));
			departamento.add(linkTo(methodOn(DepartamentoController.class).
					edit(departamento.getCodigo())).
					withRel("Editar Funcionário" + departamento.getNome()));
			
		}
		return ResponseEntity.ok(departamento);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/departamentos/{id}")
	public ResponseEntity<Departamento> delete(@PathVariable Long id) {
		Optional<Departamento> departamento = this.service.getById(id);
		if(departamento.isPresent()) {
			this.service.remove(departamento.get().getCodigo());
			return ResponseEntity.ok().body(departamento.get());
		} else {			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/departamentos/{id}")
	public ResponseEntity<Departamento> edit(@PathVariable Long id){
		Optional<Departamento> departamentoOptional = this.service.getById(id);
		Departamento departamento = departamentoOptional.get();
		if(departamentoOptional.isPresent()) {			
			departamento.add(linkTo(methodOn(DepartamentoController.class).
					delete(departamento.getCodigo())).
					withRel("Deletar Funcionário" + departamento.getNome()));
			departamento.add(linkTo(methodOn(DepartamentoController.class).
					get(departamento.getCodigo())).
					withRel("Buscar Funcionário" + departamento.getNome()));
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	
	
}
