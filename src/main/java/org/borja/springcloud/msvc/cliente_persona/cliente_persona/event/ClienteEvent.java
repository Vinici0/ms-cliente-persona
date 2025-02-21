package org.borja.springcloud.msvc.cliente_persona.cliente_persona.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEvent {
    private Long id;
    private String clienteId;
    private String nombre;
}