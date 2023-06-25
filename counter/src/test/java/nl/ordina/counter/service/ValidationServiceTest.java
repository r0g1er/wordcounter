package nl.ordina.counter.service;

import nl.ordina.counter.Exceptions.InvalidUserInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @Test
    void validateTextIsValid() {
        assertDoesNotThrow(() -> validationService.validateText("a"));
    }

    @Test
    void validateWordIsValid() {
        assertDoesNotThrow(() -> validationService.validateWord("a"));
    }

    @Test
    void validateNumberIsValid() {
        assertDoesNotThrow(() -> validationService.validateNumber(1));
    }

    @Test
    void validateTextIsEmpty() {
        assertThrows(InvalidUserInput.class, () -> validationService.validateText(""));
    }

    @Test
    void validateWordIsEmpty() {
        assertThrows(InvalidUserInput.class, () -> validationService.validateWord(""));
    }

    @Test
    void validateWordIsNotAWord() {
        assertThrows(InvalidUserInput.class, () -> validationService.validateWord("p1p"));
    }

    @Test
    void validateNumberIsZeroOrNegative() {
        assertThrows(InvalidUserInput.class, () -> validationService.validateNumber(0));
        assertThrows(InvalidUserInput.class, () -> validationService.validateNumber(-1));
    }
}
