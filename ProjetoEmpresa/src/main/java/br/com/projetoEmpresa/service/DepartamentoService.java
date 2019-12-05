package br.com.projetoEmpresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoEmpresa.model.Departamento;
import br.com.projetoEmpresa.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;
	
	@Transactional(readOnly = true)
	public List<Departamento> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Departamento departamento) {
		this.repository.save(departamento);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Departamento> getById(Long id) {
		return this.repository.findById(id);
	}
	
	
}
