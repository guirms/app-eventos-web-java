package com.guirms.eventoApp.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Convidado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer rg;
	private String nomeConvidado;

	@ManyToOne // mutios convidados para um evento
	private Evento evento;

	public Convidado() {
	}

	public Convidado(Integer rg, String nomeConvidado, Evento evento) {
		this.rg = rg;
		this.nomeConvidado = nomeConvidado;
		this.evento = evento;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convidado other = (Convidado) obj;
		return Objects.equals(rg, other.rg);
	}

}
