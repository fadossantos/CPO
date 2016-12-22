package br.com.tmsfasdom.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Interessado.findAll", query="SELECT a FROM Interessado a")
public class Interessado implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private long reInteressado;

	private String nomeInteressado;

	@ManyToMany
	@JoinTable(
		name="processoCPOCPP_interessado"
		, joinColumns={
			@JoinColumn(name="idSECCOM")
			}
		, inverseJoinColumns={
			@JoinColumn(name="reInteressado")
			}
		)
	private List<ProcessoCPOCPP> processosCPOCPP;

	public Interessado() {
		super();
	}

	public Interessado(long reInteressado, String nomeInteressado, List<ProcessoCPOCPP> documentos) {
		super();
		this.reInteressado = reInteressado;
		this.nomeInteressado = nomeInteressado;
		this.processosCPOCPP = documentos;
	}

	public long getReInteressado() {
		return reInteressado;
	}

	public void setReInteressado(long reInteressado) {
		this.reInteressado = reInteressado;
	}

	public String getNomeInteressado() {
		return nomeInteressado;
	}

	public void setNomeInteressado(String nomeInteressado) {
		this.nomeInteressado = nomeInteressado;
	}

	public List<ProcessoCPOCPP> getProcessosCPOCPP() {
		return processosCPOCPP;
	}

	public void setDocumentos(List<ProcessoCPOCPP> processosCPOCPP) {
		this.processosCPOCPP = processosCPOCPP;
	}
}
