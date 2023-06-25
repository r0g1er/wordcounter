package nl.ordina.counter.controller;

import lombok.extern.slf4j.Slf4j;
import nl.ordina.counter.Exceptions.InvalidUserInput;
import nl.ordina.counter.service.TextAnalyzer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/text")
public class WordController {

    private final TextAnalyzer textAnalyzer;

    public WordController(TextAnalyzer textAnalyzer) {
        this.textAnalyzer = textAnalyzer;
    }

    @PostMapping("/analyze")
    public ResponseEntity<String> highestFrequency(@RequestBody String text) {
        log.info("Analyze request received");
        log.debug(text);
        try {
            return ResponseEntity.ok().body(Integer.toString(textAnalyzer.calculateHighestFrequency(text)));
        } catch (InvalidUserInput e) {
            return handleUserError(e);
        }
    }

    @PostMapping("/occurrence")
    public ResponseEntity<String> wordOccurrence(@RequestBody String text, @RequestParam String word) {
        try {
            log.info("Occurrence request received");
            log.debug(text);
            log.debug(word);
            return ResponseEntity.ok().body(Integer.toString(textAnalyzer.calculateWordFrequency(text, word)));
        } catch (InvalidUserInput e) {
            return handleUserError(e);
        }
    }

    @PostMapping("/popular")
    public ResponseEntity<String> getMostPopularWords(@RequestBody String text, @RequestParam int top) {
        try {
            log.info("Popular request received");
            log.debug(text);
            log.debug("Number requested: " + top);
            return ResponseEntity.ok().body(textAnalyzer.calculateMostPopularWords(text, top).toString());
        } catch (InvalidUserInput e) {
            return handleUserError(e);
        }
    }

    private ResponseEntity<String> handleUserError(InvalidUserInput e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }
}
