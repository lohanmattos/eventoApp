package com.eventoapp.eventoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventoapp.eventoapp.models.Evento;
import com.eventoapp.eventoapp.repository.EventoRestRepository;

@RestController()
@RequestMapping("/eventosapi")
public class EventoRestController {
	
	@Autowired
	private EventoRestRepository eventoRestRepository;
	
	@GetMapping() //mapeamento não precisa colocar a rota aqui, pois ja esta no @ResquestMapping
	public List<Evento>listar() {
		
		return eventoRestRepository.findAll();
			
	}
	
	public EventoRestRepository getEventoRestRepository() {
		return eventoRestRepository;
	}

	public void setEventoRestRepository(EventoRestRepository eventoRestRepository) {
		this.eventoRestRepository = eventoRestRepository;
	}

}
