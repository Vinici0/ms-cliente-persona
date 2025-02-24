package org.borja.springcloud.msvc.clients.msclients.controllers;

import lombok.RequiredArgsConstructor;
import org.borja.springcloud.msvc.clients.msclients.dto.client.ClientRequestDto;
import org.borja.springcloud.msvc.clients.msclients.exceptions.ResourceNotFoundException;
import org.borja.springcloud.msvc.clients.msclients.response.ApiResponse;
import org.borja.springcloud.msvc.clients.msclients.services.client.IClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@RequiredArgsConstructor
public class ClientRouter {

    private final IClientService clientService;

    @Bean
    public RouterFunction<ServerResponse> clientRoutes() {
        {
            return route()
                    .GET("/api/clientes", request ->
                            clientService.getAllClients()
                                    .collectList()
                                    .map(clients -> new ApiResponse("Clientes encontrados", clients))
                                    .flatMap(response -> ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(response)))

                    .GET("/api/clientes/{id}", request ->
                            clientService.getClientById(Long.parseLong(request.pathVariable("id")))
                                    .map(client -> new ApiResponse("Cliente encontrado", client))
                                    .flatMap(response -> ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(response))
                                    .onErrorResume(ResourceNotFoundException.class, e ->
                                            ServerResponse.status(HttpStatus.NOT_FOUND)
                                                    .bodyValue(new ApiResponse(e.getMessage()))))

                    .POST("/api/clientes", request ->
                            request.bodyToMono(ClientRequestDto.class)
                                    .flatMap(clientService::addClient)
                                    .map(client -> new ApiResponse("Cliente creado exitosamente", client))
                                    .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(response)))

                    .PUT("/api/clientes/{id}", request ->
                            request.bodyToMono(ClientRequestDto.class)
                                    .flatMap(dto -> clientService.updateClient(
                                            Long.parseLong(request.pathVariable("id")), dto))
                                    .map(client -> new ApiResponse("Cliente actualizado exitosamente", client))
                                    .flatMap(response -> ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(response)))

                    .DELETE("/api/clientes/{id}", request ->
                            clientService.deleteClient(Long.parseLong(request.pathVariable("id")))
                                    .then(Mono.just(new ApiResponse("Cliente eliminado exitosamente")))
                                    .flatMap(response -> ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(response)))
                    .build();
        }

    }
}