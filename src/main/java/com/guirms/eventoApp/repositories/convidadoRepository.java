package com.guirms.eventoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guirms.eventoApp.models.Convidado;
import com.guirms.eventoApp.models.Evento;

public interface convidadoRepository extends JpaRepository<Convidado, Long> {
	
	Iterable<Convidado> findByEvento(Evento evento);
	
}
