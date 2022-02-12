package com.guirms.eventoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guirms.eventoApp.models.Evento;

public interface eventoRepository extends JpaRepository<Evento, Long> {
	
	Evento findByCodigo(Long codigo);
}
