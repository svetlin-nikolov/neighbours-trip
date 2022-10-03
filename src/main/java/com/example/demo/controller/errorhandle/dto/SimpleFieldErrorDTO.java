package com.example.demo.controller.errorhandle.dto;

/**
 * DTO used to store the field error information.
 */
public final class SimpleFieldErrorDTO {
	
	private String fieldName;
    private String message;

    public SimpleFieldErrorDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public SimpleFieldErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFieldName() {
        return fieldName;
    }
}
