package com.example.demo.controller.errorhandle;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.errorhandle.dto.SimpleFieldErrorDTO;

/**
 * Controller that handles exceptions
 */
@ControllerAdvice
public class ErrorHandlerController {

	/**
	 * Handles the ConstraintValidationException thrown by annotation validations like @NotNull and so on.
	 * @param e - the thrown ConstraintValidationException to be handled.
	 * @return ResponseEntity containing the error messages and a bad request status.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<List<SimpleFieldErrorDTO>> processConstraintViolations(ConstraintViolationException e) {
		List<SimpleFieldErrorDTO> messages = e.getConstraintViolations().stream()
				.map(cv -> new SimpleFieldErrorDTO(cv.getPropertyPath().toString(), cv.getMessage()))
				.collect(Collectors.toList());
		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}

}