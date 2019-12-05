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

import br.com.projetoEmpresa.model.Funcionario;
import br.com.projetoEmpresa.service.FuncionarioService;

@RestController
@RequestMapping("/api")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/funcionarios")
	public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario){
		this.service.save(funcionario);
		funcionario.add(linkTo(methodOn(FuncionarioController.class).
				delete(funcionario.getCodigo())).
				withRel("Deletar Funcionário" + funcionario.getNome()));
		funcionario.add(linkTo(methodOn(FuncionarioController.class).
				get(funcionario.getCodigo())).
				withRel("Buscar Funcionário" + funcionario.getNome()));
		funcionario.add(linkTo(methodOn(FuncionarioController.class).
				edit(funcionario.getCodigo())).
				withRel("Editar Funcionário" + funcionario.getNome()));
		
		return new ResponseEntity<Funcionario>(funcionario, HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/funcionarios")
	public ResponseEntity<List<Funcionario>> list() {
		List<Funcionario> funcionarios = this.service.list();
		for (Funcionario funcionario : funcionarios) {
			funcionario.add(linkTo(methodOn(FuncionarioController.class).
					delete(funcionario.getCodigo())).
					withRel("Deletar Funcionário" + funcionario.getNome()));
			funcionario.add(linkTo(methodOn(FuncionarioController.class).
					get(funcionario.getCodigo())).
					withRel("Buscar Funcionário" + funcionario.getNome()));
			funcionario.add(linkTo(methodOn(FuncionarioController.class).
					edit(funcionario.getCodigo())).
					withRel("Editar Funcionário" + funcionario.getNome()));
		}
		return ResponseEntity.ok(this.service.list());
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> get(@PathVariable Long id){
		Optional<Funcionario> funcionarioOptional = this.service.getById(id);
		Funcionario funcionario = funcionarioOptional.get();
		if(funcionarioOptional.isPresent()) {			
			funcionario.add(linkTo(methodOn(FuncionarioController.class).
					delete(funcionario.getCodigo())).
					withRel("Deletar Funcionário" + funcionario.getNome()));
			funcionario.add(linkTo(methodOn(FuncionarioController.class).
					edit(funcionario.getCodigo())).
					withRel("Editar Funcionário" + funcionario.getNome()));
			
		}
		return ResponseEntity.ok(funcionario);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> delete(@PathVariable Long id) {
		Optional<Funcionario> funcionario = this.service.getById(id);
		if(funcionario.isPresent()) {
			this.service.remove(funcionario.get().getCodigo());
			return ResponseEntity.ok().body(funcionario.get());
		} else {			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> edit(@PathVariable Long id){
		Optional<Funcionario> funcionarioOptional = this.service.getById(id);
		Funcionario funcionario = funcionarioOptional.get();
		if(funcionarioOptional.isPresent()) {			
			funcionario.add(linkTo(methodOn(FuncionarioController.class).
					delete(funcionario.getCodigo())).
					withRel("Deletar Funcionário" + funcionario.getNome()));
			funcionario.add(linkTo(methodOn(FuncionarioController.class).
					get(funcionario.getCodigo())).
					withRel("Buscar Funcionário" + funcionario.getNome()));
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	
	
}
