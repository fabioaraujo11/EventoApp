package br.com.resource.evento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.resource.evento.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {
	Evento findByidEvento(int idEvento);
}
