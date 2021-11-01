package com.eventoapp.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.eventoapp.models.Evento;

public interface EventoRespository extends CrudRepository<Evento, String> {

	Evento findByCodigo(Long codigo);	
	
}
