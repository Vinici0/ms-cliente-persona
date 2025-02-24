package org.borja.springcloud.msvc.clients.msclients.models;

import org.borja.springcloud.msvc.clients.msclients.models.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientTest {
    @Test
    void createClientWithConstructor() {
        String name = "John Doe";
        Gender gender = Gender.MALE;
        Integer age = 30;
        String identification = "123456789";
        String address = "123 Main St";
        String phone = "1234567890";
        String password = "password123";

        Client client = new Client(name, gender, age, identification, address, phone, password);

        assertNotNull(client);
        assertEquals(name, client.getName());
        assertEquals(gender, client.getGender());
        assertEquals(age, client.getAge());
        assertEquals(identification, client.getIdentification());
        assertEquals(address, client.getAddress());
        assertEquals(phone, client.getPhone());
        assertEquals(password, client.getPassword());
        assertTrue(client.getStatus());
        assertTrue(client.getClientId().startsWith("CLI-"));
    }

    @Test
    void testSettersAndGetters() {
        Client client = new Client();
        Long id = 1L;
        String clientId = "CLI-123";
        String password = "newPassword";
        Boolean status = false;

        client.setId(id);
        client.setClientId(clientId);
        client.setPassword(password);
        client.setStatus(status);

        assertEquals(id, client.getId());
        assertEquals(clientId, client.getClientId());
        assertEquals(password, client.getPassword());
        assertEquals(status, client.getStatus());
    }

    @Test
    void generateRandomClientIdShouldStartWithPrefix() {
        Client client = new Client();
        String clientId = client.getClientId();

        assertNull(clientId); // Should be null as it's not initialized

        client = new Client("Test", Gender.MALE, 25, "123", "Test St", "12345", "pass");
        clientId = client.getClientId();

        assertNotNull(clientId);
        assertTrue(clientId.startsWith("CLI-"));
        assertEquals(40, clientId.length());
    }
}