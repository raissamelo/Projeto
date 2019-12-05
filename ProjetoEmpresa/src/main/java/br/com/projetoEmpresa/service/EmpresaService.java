package br.com.projetoEmpresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoEmpresa.model.Empresa;
import br.com.projetoEmpresa.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	@Transactional(readOnly = true)
	public List<Empresa> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Empresa empresa) {
		this.repository.save(empresa);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Empresa> getById(Long id) {
		return this.repository.findById(id);
	}
	
	
}
