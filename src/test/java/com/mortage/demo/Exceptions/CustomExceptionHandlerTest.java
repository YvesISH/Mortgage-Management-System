package com.mortage.demo.Exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomExceptionHandlerTest {

    private final CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();

    @Test
    public void handleInvalidFormatException_ShouldReturnBadRequestWithErrorMessage() {
        // Arrange
        InvalidFormatException ex = new InvalidFormatException(
                null, "Invalid value 'a' for the 'years' field", null);

        // Act
        ResponseEntity<String> responseEntity = exceptionHandler.handleInvalidFormatException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid value. Please provide a positive number", responseEntity.getBody());
    }
}
