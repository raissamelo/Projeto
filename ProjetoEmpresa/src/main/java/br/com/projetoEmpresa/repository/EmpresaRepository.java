package br.com.projetoEmpresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoEmpresa.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	public List<Empresa> findByNomeContaining(String nome);
	
}
