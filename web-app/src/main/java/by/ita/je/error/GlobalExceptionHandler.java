package by.ita.je.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final String ATTRIBUTE_NAME = "errorMessage";
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(HttpClientErrorException ex, Model model) {
        model.addAttribute(ATTRIBUTE_NAME, String.format("%s, %s", "Client error: ", ex.getMessage()));
        return "error.html";
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public String handleServertError(HttpClientErrorException ex, Model model) {
        model.addAttribute(ATTRIBUTE_NAME, String.format("%s, %s", "Server error: ", ex.getMessage()));
        return "error.html";
    }
}
