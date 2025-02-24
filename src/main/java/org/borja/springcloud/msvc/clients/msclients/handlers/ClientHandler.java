package org.borja.springcloud.msvc.clients.msclients.handlers;

// import org.borja.springcloud.msvc.clients.msclients.dto.client.ClientRequestDto;
// import org.borja.springcloud.msvc.clients.msclients.response.ApiResponse;
// import org.borja.springcloud.msvc.clients.msclients.services.client.IClientService;
// import org.springframework.http.MediaType;
// import org.springframework.stereotype.Component;
// import org.springframework.web.reactive.function.server.ServerRequest;
// import org.springframework.web.reactive.function.server.ServerResponse;
// import reactor.core.publisher.Mono;

// @Component
public class ClientHandler {
    // private final IClientService clientService;

    // public ClientHandler(IClientService clientService) {
    //     this.clientService = clientService;
    // }

    // public Mono<ServerResponse> addClient(ServerRequest request) {
    //     return request.bodyToMono(ClientRequestDto.class)
    //             .flatMap(clientService::addClient)
    //             .flatMap(client -> {
    //                 ApiResponse response = new ApiResponse();
    //                 response.setMessage("Cliente creado exitosamente");
    //                 response.setData(client);
    //                 return ServerResponse.created(request.uri())
    //                         .contentType(MediaType.APPLICATION_JSON)
    //                         .bodyValue(response);
    //             });
    // }

    // public Mono<ServerResponse> getAllClients(ServerRequest request) {
    //     return ServerResponse.ok()
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(clientService.getAllClients()
    //                             .collectList()
    //                             .map(clients -> {
    //                                 ApiResponse response = new ApiResponse();
    //                                 response.setMessage("Lista de clientes recuperada");
    //                                 response.setData(clients);
    //                                 return response;
    //                             }),
    //                     ApiResponse.class);
    // }

    // public Mono<ServerResponse> getClientById(ServerRequest request) {
    //     Long id = Long.valueOf(request.pathVariable("id"));
    //     return clientService.getClientById(id)
    //             .flatMap(client -> {
    //                 ApiResponse response = new ApiResponse();
    //                 response.setMessage("Cliente encontrado");
    //                 response.setData(client);
    //                 return ServerResponse.ok()
    //                         .contentType(MediaType.APPLICATION_JSON)
    //                         .bodyValue(response);
    //             })
    //             .switchIfEmpty(ServerResponse.notFound().build());
    // }

    // public Mono<ServerResponse> updateClient(ServerRequest request) {
    //     Long id = Long.valueOf(request.pathVariable("id"));
    //     return request.bodyToMono(ClientRequestDto.class)
    //             .flatMap(clientDto -> clientService.updateClient(id, clientDto))
    //             .flatMap(client -> {
    //                 ApiResponse response = new ApiResponse();
    //                 response.setMessage("Cliente actualizado exitosamente");
    //                 response.setData(client);
    //                 return ServerResponse.ok()
    //                         .contentType(MediaType.APPLICATION_JSON)
    //                         .bodyValue(response);
    //             })
    //             .switchIfEmpty(ServerResponse.notFound().build());
    // }

    // public Mono<ServerResponse> deleteClient(ServerRequest request) {
    //     Long id = Long.valueOf(request.pathVariable("id"));
    //     return clientService.deleteClient(id)
    //             .then(Mono.fromCallable(() -> {
    //                 ApiResponse response = new ApiResponse();
    //                 response.setMessage("Cliente eliminado exitosamente");
    //                 response.setData(null);
    //                 return response;
    //             }))
    //             .flatMap(response -> ServerResponse.ok()
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .bodyValue(response));
    // }
}