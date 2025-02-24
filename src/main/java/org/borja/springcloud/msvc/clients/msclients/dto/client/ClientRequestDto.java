package org.borja.springcloud.msvc.clients.msclients.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.borja.springcloud.msvc.clients.msclients.models.enums.Gender;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    private Integer age;

    @NotBlank
    private String identification;

    @NotBlank
    private String clientId;

    private String address;
    private String phone;

    @NotBlank
    private String password;

}