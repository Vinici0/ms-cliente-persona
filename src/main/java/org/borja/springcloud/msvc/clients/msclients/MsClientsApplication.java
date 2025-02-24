package org.borja.springcloud.msvc.clients.msclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class MsClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClientsApplication.class, args);
    }

}
