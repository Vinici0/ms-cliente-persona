package org.borja.springcloud.msvc.clients.msclients.validadors;

import lombok.RequiredArgsConstructor;
import org.borja.springcloud.msvc.clients.msclients.exceptions.InsufficientBalanceException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovementValidator {

    public void validateBalance(double newBalance) {
        if (newBalance < 0) {
            throw new InsufficientBalanceException("Saldo no disponible");
        }
    }
}