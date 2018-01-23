package com.company.validation;

import javax.xml.bind.ValidationException;

public class NotBlankValidationProcessor implements BaseValidationProcessor {

    private String value;
    private String message;

    public NotBlankValidationProcessor(String value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public void validate() throws ValidationException {

        boolean valid = value != null && !value.equals("");

        if (!valid) {
            System.out.println(message);
            throw new ValidationException(message);
        }
    }

}
