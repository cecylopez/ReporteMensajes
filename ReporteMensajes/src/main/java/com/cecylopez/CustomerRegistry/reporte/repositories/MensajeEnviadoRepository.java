package com.cecylopez.CustomerRegistry.reporte.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cecylopez.CustomerRegistry.reporte.entities.MensajeEnviado;

public interface MensajeEnviadoRepository {
	@Query(value = "SELECT * FROM MENSAJE_ENVIADO WHERE DATE(FECHA_ENVIADO) = CURDATE()", nativeQuery = true)
	List<MensajeEnviado> findByToday();
}
