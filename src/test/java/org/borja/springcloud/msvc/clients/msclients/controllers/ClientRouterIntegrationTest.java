package org.borja.springcloud.msvc.clients.msclients.controllers;

import org.borja.springcloud.msvc.clients.msclients.dto.client.ClientResponseDto;
import org.borja.springcloud.msvc.clients.msclients.models.enums.Gender;
import org.borja.springcloud.msvc.clients.msclients.services.client.IClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ClientRouterIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private IClientService clientService;

    @Test
    void testGetClientById() {
        // Prepare test data
        Long clientId = 1L;
        ClientResponseDto expectedResponse = ClientResponseDto.builder()
                .id(clientId)
                .name("John Doe")
                .gender(Gender.MALE)
                .age(30)
                .identification("123456789")
                .address("123 Main St")
                .phone("555-0123")
                .build();

        // Mock service response
        when(clientService.getClientById(clientId))
                .thenReturn(Mono.just(expectedResponse));

        // Test GET endpoint
        webTestClient.get()
                .uri("/api/clientes/{id}", clientId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Cliente encontrado")
                .jsonPath("$.data.id").isEqualTo(clientId)
                .jsonPath("$.data.name").isEqualTo("John Doe")
                .jsonPath("$.data.gender").isEqualTo("MALE")
                .jsonPath("$.data.age").isEqualTo(30)
                .jsonPath("$.data.identification").isEqualTo("123456789")
                .jsonPath("$.data.address").isEqualTo("123 Main St")
                .jsonPath("$.data.phone").isEqualTo("555-0123");
    }


}