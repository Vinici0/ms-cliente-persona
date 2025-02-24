package org.borja.springcloud.msvc.clients.msclients.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.borja.springcloud.msvc.clients.msclients.models.enums.Gender;

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