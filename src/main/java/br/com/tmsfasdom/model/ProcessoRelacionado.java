package br.com.tmsfasdom.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="ProcessoRelacionado.findAll", query="SELECT a FROM ProcessoRelacionado a")
public class ProcessoRelacionado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idProcessoRelacionado;
	
	private String descProcessoRelacionado;
	
	@ManyToMany
	@JoinTable(
		name="processoCPOCPP_processoRelacionado"
		, joinColumns={
			@JoinColumn(name="idSECCOM")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idProcessoRelacionado")
			}
		)
	private List<ProcessoCPOCPP> processosCPOCPP;

	public long getIdProcessoRelacionado() {
		return idProcessoRelacionado;
	}

	public void setIdProcessoRelacionado(long idProcessoRelacionado) {
		this.idProcessoRelacionado = idProcessoRelacionado;
	}

	public String getDescProcessoRelacionado() {
		return descProcessoRelacionado;
	}

	public void setDescProcessoRelacionado(String descProcessoRelacionado) {
		this.descProcessoRelacionado = descProcessoRelacionado;
	}

	public List<ProcessoCPOCPP> getProcessosCPOCPP() {
		return processosCPOCPP;
	}

	public void setProcessosCPOCPP(List<ProcessoCPOCPP> processosCPOCPP) {
		this.processosCPOCPP = processosCPOCPP;
	}

	public ProcessoRelacionado(long idProcessoRelacionado, String descProcessoRelacionado,
			List<ProcessoCPOCPP> processosCPOCPP) {
		super();
		this.idProcessoRelacionado = idProcessoRelacionado;
		this.descProcessoRelacionado = descProcessoRelacionado;
		this.processosCPOCPP = processosCPOCPP;
	}

	public ProcessoRelacionado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
