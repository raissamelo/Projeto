package br.com.projetoEmpresa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="dependente")
public class Empresa extends ResourceSupport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String nome;
	private int CNPJ;
	
	@ManyToOne
	private Departamento departamento;
	
}
