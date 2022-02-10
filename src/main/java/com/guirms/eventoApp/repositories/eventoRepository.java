package com.guirms.eventoApp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.guirms.eventoApp.models.Evento;

public interface eventoRepository extends CrudRepository<Evento, Long> {
	
}
