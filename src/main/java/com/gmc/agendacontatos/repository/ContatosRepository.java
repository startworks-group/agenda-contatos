package com.gmc.agendacontatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmc.agendacontatos.domain.Contato;

public interface ContatosRepository extends JpaRepository<Contato, Long> {
}
