package org.borja.springcloud.msvc.clients.msclients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.borja.springcloud.msvc.clients.msclients.models.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {

    private String name;
    private Gender gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
}