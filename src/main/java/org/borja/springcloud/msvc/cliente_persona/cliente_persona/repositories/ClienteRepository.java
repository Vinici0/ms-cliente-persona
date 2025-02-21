package org.borja.springcloud.msvc.cliente_persona.cliente_persona.repositories;

import org.borja.springcloud.msvc.cliente_persona.cliente_persona.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByClienteId(String clienteId);
}
