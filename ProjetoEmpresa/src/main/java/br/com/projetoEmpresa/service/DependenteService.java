package br.com.projetoEmpresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoEmpresa.model.Dependente;
import br.com.projetoEmpresa.repository.DependenteRepository;

@Service
public class DependenteService {

	@Autowired
	private DependenteRepository repository;
	
	@Transactional(readOnly = true)
	public List<Dependente> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Dependente dependente) {
		this.repository.save(dependente);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Dependente> getById(Long id) {
		return this.repository.findById(id);
	}
	
	
}
