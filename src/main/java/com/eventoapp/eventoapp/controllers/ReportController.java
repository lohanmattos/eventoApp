package com.eventoapp.eventoapp.controllers;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.eventoapp.eventoapp.models.Evento;
import com.eventoapp.eventoapp.repository.EventoRestRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReportController {
	
	@Autowired
	private EventoRestRepository eventoRestRepository;
	
	public List<Evento>listar() {
		
		return eventoRestRepository.findAll();
			
	}
	
	@GetMapping("/relatorio_evento")
	public ResponseEntity<byte[]> generatePdf() throws Exception, JRException {	
		
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listar());//cria a base de dados
		JasperReport compileReport =  JasperCompileManager.compileReport(new FileInputStream("src/main/resources/report_eventos.jrxml")); //carregar o arquivo .jrxml
		
		
		HashMap<String, Object> map = new  HashMap<>();
		JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource); //carregar os dados no arquivo jr.xml
		
		//JasperExportManager.exportReportToPdfFile(report, "eventos.pdf"); //gerar o pdf na raiz do programa
		
		
		//gerar o pdf na aba do navegador
		byte[] data = JasperExportManager.exportReportToPdf(report);		
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		headers.set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,"inline;filename=eventos.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		
	
	}

	
	public EventoRestRepository getEventoRestRepository() {
		return eventoRestRepository;
	}

	public void setEventoRestRepository(EventoRestRepository eventoRestRepository) {
		this.eventoRestRepository = eventoRestRepository;
	}
	
}
