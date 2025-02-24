package org.borja.springcloud.msvc.clients.msclients.services.client;

import lombok.RequiredArgsConstructor;

import org.borja.springcloud.msvc.clients.msclients.dto.client.ClientRequestDto;
import org.borja.springcloud.msvc.clients.msclients.dto.client.ClientResponseDto;
import org.borja.springcloud.msvc.clients.msclients.exceptions.ResourceNotFoundException;
import org.borja.springcloud.msvc.clients.msclients.models.Client;
import org.borja.springcloud.msvc.clients.msclients.repositories.ClientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public Mono<ClientResponseDto> addClient(ClientRequestDto clientDto) {
        System.out.println("Adding new client: " + clientDto.getName());
        return clientRepository.findByIdentification(clientDto.getIdentification())
                .collectList()
                .flatMap(existingClients -> {
                    if (!existingClients.isEmpty()) {
                        System.out.println("Client already exists with identification: " + clientDto.getIdentification());
                        return Mono.error(new ResourceNotFoundException("Client exists with identification: " + clientDto.getIdentification()));
                    }
                    Client client = convertToEntity(clientDto);
                    client.setPassword(passwordEncoder.encode(client.getPassword()));
                    System.out.println("Client created successfully with ID: " + client.getId());
                    return clientRepository.save(client)
                            .map(this::convertToDto);
                });
    }

    @Override
    public Flux<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<ClientResponseDto> getClientById(Long id) {
        log.info("Fetching client with ID: {}", id);
        return clientRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Client not found with ID: {}", id);
                    return Mono.error(new ResourceNotFoundException("Cliente no encontrado con id: " + id));
                }))
                .map(this::convertToDto);
    }

    @Override
    public Mono<ClientResponseDto> updateClient(Long id, ClientRequestDto clientDto) {
        log.info("Updating client with ID: {}", id);
        return clientRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Client not found with ID: {}", id);
                    return Mono.error(new ResourceNotFoundException("Cliente no encontrado con id: " + id));
                }))
                .flatMap(existingClient -> {
                    updateEntityFromDto(existingClient, clientDto);
                    if (clientDto.getPassword() != null && !clientDto.getPassword().isEmpty()) {
                        existingClient.setPassword(passwordEncoder.encode(clientDto.getPassword()));
                    }
                    return clientRepository.save(existingClient);
                })
                .map(savedClient -> {
                    log.info("Client updated successfully with ID: {}", savedClient.getId());
                    return convertToDto(savedClient);
                });
    }

    @Override
    public Mono<Void> deleteClient(Long id) {
        log.info("Disabling client with ID: {}", id);
        return clientRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("Client not found with ID: {}", id);
                    return Mono.error(new ResourceNotFoundException("Cliente no encontrado con id: " + id));
                }))
                .flatMap(client -> {
                    client.setStatus(false);
                    return clientRepository.save(client);
                })
                .doOnSuccess(result -> log.info("Client disabled successfully with ID: {}", id))
                .then();
    }


    private Client convertToEntity(ClientRequestDto dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setGender(dto.getGender());
        client.setAge(dto.getAge());
        client.setIdentification(dto.getIdentification());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());
        client.setPassword(dto.getPassword());
        // If dto.clientId is null or empty, generate a new client_id
        if (dto.getClientId() == null || dto.getClientId().isEmpty()) {
            client.setClientId("CLI-" + java.util.UUID.randomUUID().toString());
        } else {
            client.setClientId(dto.getClientId());
        }
        return client;
    }

    private ClientResponseDto convertToDto(Client client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .gender(client.getGender())
                .age(client.getAge())
                .identification(client.getIdentification())
                .address(client.getAddress())
                .phone(client.getPhone())
                .clientId(client.getClientId())
                .build();
    }

    private void updateEntityFromDto(Client client, ClientRequestDto dto) {
        client.setName(dto.getName());
        client.setGender(dto.getGender());
        client.setAge(dto.getAge());
        client.setIdentification(dto.getIdentification());
        client.setAddress(dto.getAddress());
        client.setPhone(dto.getPhone());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            client.setPassword(dto.getPassword());
        }
    }
}