package br.com.projetoEmpresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoEmpresa.model.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Long> {

	public List<Dependente> findByNomeContaining(String nome);
	
}
