package org.borja.springcloud.msvc.clients.msclients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.borja.springcloud.msvc.clients.msclients.models.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("clients")
public class Client extends Person {

    @Id
    private Long id;

    private String clientId;
    private String password;
    private Boolean status = true;

    // Se genera un clientId al crear un nuevo cliente
    public Client(String name, Gender gender, Integer age, String identification, String address, String phone, String password) {
        super(name, gender, age, identification, address, phone);
        this.clientId = generateRandomClientId();
        this.password = password;
        this.status = true;
    }

    private String generateRandomClientId() {
        return "CLI-" + UUID.randomUUID().toString();
    }
}
