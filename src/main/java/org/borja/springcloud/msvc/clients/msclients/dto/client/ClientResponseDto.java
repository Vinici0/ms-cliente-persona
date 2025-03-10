package org.borja.springcloud.msvc.clients.msclients.dto.client;

import lombok.Builder;
import lombok.Data;
import org.borja.springcloud.msvc.clients.msclients.models.enums.Gender;

@Data
@Builder
public class ClientResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String clientId;
}