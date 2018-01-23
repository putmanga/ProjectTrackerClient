package com.company.validation;

import com.company.validation.annotations.Email;
import com.company.validation.annotations.NotBlank;

import javax.xml.bind.ValidationException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Validator {

    public static void validate(Object o) throws ValidationException {
        boolean validationExceptionCaught = false;

        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                try {
                    validateAnnotation(annotation, (String) field.get(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ValidationException e) {
                    validationExceptionCaught = true;
                }
            }
        }

        if (validationExceptionCaught) {
            throw new ValidationException("Validation failed");
        }
    }

    private static void validateAnnotation(Annotation annotation, String field) throws ValidationException {
        try {

            BaseValidationProcessor processor = null;

            if (annotation.annotationType().equals(Email.class)) {
                processor = EmailValidationProcessor
                        .class
                        .getDeclaredConstructor(String.class, String.class)
                        .newInstance(field, ((Email)annotation).message());
            } else if (annotation.annotationType().equals(NotBlank.class)) {
                processor = NotBlankValidationProcessor
                        .class
                        .getDeclaredConstructor(String.class, String.class)
                        .newInstance(field, ((NotBlank)annotation).message());
            }

            if (processor != null) {
                processor.validate();
            }

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
