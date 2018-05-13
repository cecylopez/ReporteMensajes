package com.cecylopez.CustomerRegistry.reporte.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cecylopez.CustomerRegistry.reporte.entities.MensajeEnviado;

public interface MensajeEnviadoRepository extends JpaRepository<MensajeEnviado, Long> {
	@Query(value = "SELECT * FROM MENSAJE_ENVIADO WHERE DATE(FECHA_ENVIO) = CURDATE()", nativeQuery = true)
	List<MensajeEnviado> findByToday();
}
