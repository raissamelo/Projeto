package br.com.projetoEmpresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoEmpresa.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	public List<Departamento> findByNomeContaining(String nome);
	
}
