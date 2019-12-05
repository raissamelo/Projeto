package br.com.projetoEmpresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoEmpresa.model.Endereco;
import br.com.projetoEmpresa.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	@Transactional(readOnly = true)
	public List<Endereco> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Endereco endereco) {
		this.repository.save(endereco);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Endereco> getById(Long id) {
		return this.repository.findById(id);
	}
	
	
}
