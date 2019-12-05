package br.com.projetoEmpresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoEmpresa.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public List<Endereco> findByNomeContaining(String nome);
	
}
