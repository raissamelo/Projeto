package br.com.projetoEmpresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetoEmpresa.model.Funcionario;
import br.com.projetoEmpresa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	@Transactional(readOnly = true)
	public List<Funcionario> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Funcionario funcionario) {
		this.repository.save(funcionario);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Funcionario> getById(Long id) {
		return this.repository.findById(id);
	}
	
	
}
