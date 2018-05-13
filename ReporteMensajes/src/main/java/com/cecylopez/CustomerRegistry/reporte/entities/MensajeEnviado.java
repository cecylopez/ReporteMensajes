package com.cecylopez.CustomerRegistry.reporte.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MENSAJE_ENVIADO", schema="Leaderboard")
public class MensajeEnviado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ENVIO")
	private Date fechaEnvio;
	
	private int celular;
	private String mensaje;
	
	@Column(name="ACUSE_RECIBO")
	private char acuseRecibo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public char getAcuseRecibo() {
		return acuseRecibo;
	}

	public void setAcuseRecibo(char acuseRecibo) {
		this.acuseRecibo = acuseRecibo;
	}

	@Override
	public String toString() {
		return "MensajeEnviado [id=" + id + ", fechaEnvio=" + fechaEnvio + ", celular=" + celular + ", mensaje="
				+ mensaje + ", acuseRecibo=" + acuseRecibo + "]";
	}
	
}
