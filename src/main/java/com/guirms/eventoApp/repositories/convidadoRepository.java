package com.guirms.eventoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guirms.eventoApp.models.Convidado;

public interface convidadoRepository extends JpaRepository<Convidado, Long> {
	
}
