package nl.ordina.counter.service;

import nl.ordina.counter.Exceptions.InvalidUserInput;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public void validateText(String text) {

        if (text.isEmpty()) {
            throw new InvalidUserInput("Text to analyze is empty. Unable to process request.");
        }
    }

    public void validateWord(String word) {
        if (word.isEmpty()) {
            throw new InvalidUserInput("Word to match is empty. Unable to process request.");
        }
        if (!word.matches("^[a-zA-Z]+")) {
            throw new InvalidUserInput(String.format("Presented word %s does not meet the minimal requirements.", word));
        }
    }

    public void validateNumber(int number) {
        if (number <= 0) {
            throw new InvalidUserInput("Number must be greater than 0");
        }
    }
}
