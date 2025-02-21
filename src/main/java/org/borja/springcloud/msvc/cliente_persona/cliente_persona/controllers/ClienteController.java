package org.borja.springcloud.msvc.cliente_persona.cliente_persona.controllers;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.apache.kafka.common.requests.ApiVersionsResponse;
import org.borja.springcloud.msvc.cliente_persona.cliente_persona.dto.cliente.ClienteRequestDto;
import org.borja.springcloud.msvc.cliente_persona.cliente_persona.dto.cliente.ClienteResponseDto;
import org.borja.springcloud.msvc.cliente_persona.cliente_persona.event.ClienteEvent;
import org.borja.springcloud.msvc.cliente_persona.cliente_persona.response.ApiResponse;
import org.borja.springcloud.msvc.cliente_persona.cliente_persona.services.KafkaProducerService;
import org.borja.springcloud.msvc.cliente_persona.cliente_persona.services.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<ApiResponse> crear(@Validated @RequestBody ClienteRequestDto clienteDto) {
        try {
            ClienteResponseDto nuevo = clienteService.crearCliente(clienteDto);
            ClienteEvent event = new ClienteEvent(nuevo.getId(), nuevo.getClienteId(), nuevo.getNombre());
            kafkaProducerService.sendClienteEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Cliente creado exitosamente", nuevo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> listarTodos() {
        try {
            List<ClienteResponseDto> clientes = clienteService.obtenerTodos();
            return ResponseEntity.ok(new ApiResponse("Lista de clientes recuperada", clientes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> obtenerPorId(@PathVariable Long id) {
        try {
            ClienteResponseDto cliente = clienteService.obtenerPorId(id);
            return ResponseEntity.ok(new ApiResponse("Cliente encontrado", cliente));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizar(
            @PathVariable Long id,
            @Validated @RequestBody ClienteRequestDto clienteDto) {
        try {
            ClienteResponseDto actualizado = clienteService.actualizarCliente(id, clienteDto);
            return ResponseEntity.ok(new ApiResponse("Cliente actualizado exitosamente", actualizado));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        try {
            clienteService.eliminarCliente(id);
            return ResponseEntity.ok(new ApiResponse("Cliente eliminado exitosamente", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

}
