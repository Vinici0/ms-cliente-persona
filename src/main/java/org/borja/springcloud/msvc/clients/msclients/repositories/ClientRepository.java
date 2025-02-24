package org.borja.springcloud.msvc.clients.msclients.repositories;

import org.borja.springcloud.msvc.clients.msclients.models.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {

    Flux<Client> findAllByStatus(Boolean status);
    Flux<Client> findByIdentification(String identification);
    Mono<Client> findByIdAndStatus(Long clientId, Boolean status);
}