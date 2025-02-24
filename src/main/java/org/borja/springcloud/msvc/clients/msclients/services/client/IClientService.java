package org.borja.springcloud.msvc.clients.msclients.services.client;


import org.borja.springcloud.msvc.clients.msclients.dto.client.ClientRequestDto;
import org.borja.springcloud.msvc.clients.msclients.dto.client.ClientResponseDto;
import org.borja.springcloud.msvc.clients.msclients.models.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Mono<ClientResponseDto> addClient(ClientRequestDto clientDto);
    Flux<Client> getAllClients();
    Mono<ClientResponseDto> getClientById(Long id);
    Mono<ClientResponseDto> updateClient(Long id, ClientRequestDto clientDto);
    Mono<Void> deleteClient(Long id);
}