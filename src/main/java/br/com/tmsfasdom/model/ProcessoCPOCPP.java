package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the Inspecao database table.
 * 
 */
@Entity
@NamedQuery(name = "ProcessoCPOCPP.findAll", query = "SELECT i FROM ProcessoCPOCPP i")
public class ProcessoCPOCPP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idSECCOM;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEntradaProcessoCPOCPP;

	private String observacoes;
	
	@ManyToMany(mappedBy = "processosCPOCPP")
	private List<Interessado> interessados;
	
	@ManyToMany(mappedBy = "processosCPOCPP")
	private List<ProcessoRelacionado> processosRelacionados;
	
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;
	
	public ProcessoCPOCPP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProcessoCPOCPP(long idSECCOM, Date dtEntradaProcessoCPOCPP, String observacoes,
			List<Interessado> interessados, List<ProcessoRelacionado> processosRelacionados, Status status) {
		super();
		this.idSECCOM = idSECCOM;
		this.dtEntradaProcessoCPOCPP = dtEntradaProcessoCPOCPP;
		this.observacoes = observacoes;
		this.interessados = interessados;
		this.processosRelacionados = processosRelacionados;
		this.status = status;
	}

	public long getIdSECCOM() {
		return idSECCOM;
	}

	public void setIdSECCOM(long idSECCOM) {
		this.idSECCOM = idSECCOM;
	}

	public Date getDtEntradaProcessoCPOCPP() {
		return dtEntradaProcessoCPOCPP;
	}

	public void setDtEntradaProcessoCPOCPP(Date dtEntradaProcessoCPOCPP) {
		this.dtEntradaProcessoCPOCPP = dtEntradaProcessoCPOCPP;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<Interessado> getInteressados() {
		return interessados;
	}

	public void setInteressados(List<Interessado> interessados) {
		this.interessados = interessados;
	}

	public List<ProcessoRelacionado> getProcessosRelacionados() {
		return processosRelacionados;
	}

	public void setProcessosRelacionados(List<ProcessoRelacionado> processosRelacionados) {
		this.processosRelacionados = processosRelacionados;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}