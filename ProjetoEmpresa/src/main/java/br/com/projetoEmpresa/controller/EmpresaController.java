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

import br.com.projetoEmpresa.model.Empresa;
import br.com.projetoEmpresa.service.EmpresaService;

@RestController
@RequestMapping("/api")
public class EmpresaController {
	
	@Autowired
	private EmpresaService service;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/empresas")
	public ResponseEntity<Empresa> save(@RequestBody Empresa empresa){
		this.service.save(empresa);
		empresa.add(linkTo(methodOn(EmpresaController.class).
				delete(empresa.getCodigo())).
				withRel("Deletar Funcionário" + empresa.getNome()));
		empresa.add(linkTo(methodOn(EmpresaController.class).
				get(empresa.getCodigo())).
				withRel("Buscar Funcionário" + empresa.getNome()));
		empresa.add(linkTo(methodOn(EmpresaController.class).
				edit(empresa.getCodigo())).
				withRel("Editar Funcionário" + empresa.getNome()));
		
		return new ResponseEntity<Empresa>(empresa, HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/empresas")
	public ResponseEntity<List<Empresa>> list() {
		List<Empresa> empresas = this.service.list();
		for (Empresa empresa : empresas) {
			empresa.add(linkTo(methodOn(EmpresaController.class).
					delete(empresa.getCodigo())).
					withRel("Deletar Funcionário" + empresa.getNome()));
			empresa.add(linkTo(methodOn(EmpresaController.class).
					get(empresa.getCodigo())).
					withRel("Buscar Funcionário" + empresa.getNome()));
			empresa.add(linkTo(methodOn(EmpresaController.class).
					edit(empresa.getCodigo())).
					withRel("Editar Funcionário" + empresa.getNome()));
		}
		return ResponseEntity.ok(this.service.list());
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/empresas/{id}")
	public ResponseEntity<Empresa> get(@PathVariable Long id){
		Optional<Empresa> empresaOptional = this.service.getById(id);
		Empresa empresa = empresaOptional.get();
		if(empresaOptional.isPresent()) {			
			empresa.add(linkTo(methodOn(EmpresaController.class).
					delete(empresa.getCodigo())).
					withRel("Deletar Funcionário" + empresa.getNome()));
			empresa.add(linkTo(methodOn(EmpresaController.class).
					edit(empresa.getCodigo())).
					withRel("Editar Funcionário" + empresa.getNome()));
			
		}
		return ResponseEntity.ok(empresa);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<Empresa> delete(@PathVariable Long id) {
		Optional<Empresa> empresa = this.service.getById(id);
		if(empresa.isPresent()) {
			this.service.remove(empresa.get().getCodigo());
			return ResponseEntity.ok().body(empresa.get());
		} else {			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/empresas/{id}")
	public ResponseEntity<Empresa> edit(@PathVariable Long id){
		Optional<Empresa> empresaOptional = this.service.getById(id);
		Empresa empresa = empresaOptional.get();
		if(empresaOptional.isPresent()) {			
			empresa.add(linkTo(methodOn(EmpresaController.class).
					delete(empresa.getCodigo())).
					withRel("Deletar Funcionário" + empresa.getNome()));
			empresa.add(linkTo(methodOn(EmpresaController.class).
					get(empresa.getCodigo())).
					withRel("Buscar Funcionário" + empresa.getNome()));
			return ResponseEntity.ok(empresa);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	
	
}
