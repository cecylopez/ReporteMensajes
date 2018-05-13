package com.cecylopez.CustomerRegistry.reporte;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cecylopez.CustomerRegistry.reporte.entities.MensajeEnviado;
import com.cecylopez.CustomerRegistry.reporte.repositories.MensajeEnviadoRepository;
import com.robertux.leaderboard.util.LogHelper;

@SpringBootApplication
public class ReporteMensajesApplication implements CommandLineRunner {
	public static final String LOG4J_PROPS_NAME = "log4j.properties";
	public static final String CSV_FILE_NAME = "csvFileName";
	public static final String[] COLUMNAS = new String[] {"FechaEnvio, Celular, Mensaje, AcuseRecibo"};
	
	private DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private DateFormat sdfEnvio = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@Autowired
	MensajeEnviadoRepository mensajeRepo;

	public static void main(String[] args) {
		LogHelper.configureLog4j(LOG4J_PROPS_NAME);
		Logger logger = Logger.getLogger(ReporteMensajesApplication.class);
		logger.info("Log4j configurado satisfactoriamente");
		SpringApplication.run(ReporteMensajesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Logger logger = Logger.getLogger(this.getClass());
		logger.info("Inicio proceso de generación de reporte de mensajes enviados por SMS el día de hoy");
		List<MensajeEnviado> mensajes = mensajeRepo.findByToday();
		if (System.getProperty(CSV_FILE_NAME) == null || System.getProperty(CSV_FILE_NAME).trim().length() == 0) {
			logger.error("No se recibió el parámetro necesario " + CSV_FILE_NAME);
			return;
		}
		
		StringBuilder csvFileName = new StringBuilder(System.getProperty(CSV_FILE_NAME));
		csvFileName.append("-").append(sdf.format(new Date())).append(".csv");
		
		
		logger.info("Escribiendo " + mensajes.size() + " registros al archivo " + csvFileName.toString());
		FileWriter out = new FileWriter(csvFileName.toString(), false);
		try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(COLUMNAS))) {
			mensajes.stream().forEach(m -> { try { 
				printer.printRecord(sdfEnvio.format(m.getFechaEnvio()), m.getCelular(), m.getMensaje(), m.getAcuseRecibo()); 
				} catch (Exception ex) {
					logger.error("Error agregando registro " + m + ": " + ex.getMessage(), ex);
				} });
		} catch (Exception e) {
			logger.error("Error generando archivo CSV: " + e.getMessage(), e);
		}
		logger.info("Proceso de generación de reporte finalizado");
		
	}
}
