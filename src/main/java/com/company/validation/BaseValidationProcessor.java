package com.company.validation;

import javax.xml.bind.ValidationException;

public interface BaseValidationProcessor {
    void validate() throws ValidationException;
}
