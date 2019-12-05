package br.com.projetoEmpresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoEmpresa.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	public List<Funcionario> findByNomeContaining(String nome);
	
}
