package com.company.validation;

import javax.xml.bind.ValidationException;

public class EmailValidationProcessor implements BaseValidationProcessor {

    private String value;
    private String message;

    public EmailValidationProcessor(String value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public void validate() throws ValidationException {
        org.apache.commons.validator.routines.EmailValidator validator =
                org.apache.commons.validator.routines.EmailValidator.getInstance();

        boolean valid = validator.isValid(value);

        if (!valid) {
            System.out.println(message);
            throw new ValidationException(message);
        }
    }

}
