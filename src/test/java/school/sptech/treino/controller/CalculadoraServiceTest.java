package school.sptech.treino.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.treino.service.CalculadoraService;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraServiceTest {

    @Test
    @DisplayName("Quando acionado com 10 e 0, deve lançar uma exceção com mensagem específica")
    public void testDividirPorZero() {
        // Arrange
        CalculadoraService calculadoraService = new CalculadoraService();
        double a = 10;
        double b = 0;
        String expectedMessage = "400 BAD_REQUEST \"Divisão por zero não permitida\"";

        // Act & Assert
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> calculadoraService.dividir(a, b)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }
}
