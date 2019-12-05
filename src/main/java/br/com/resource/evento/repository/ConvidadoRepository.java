package br.com.resource.evento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.resource.evento.models.Convidado;
import br.com.resource.evento.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);
}
