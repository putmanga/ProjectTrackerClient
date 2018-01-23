package com.company.menu.commands;

import com.company.Client;
import com.company.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;

public class RegistrationCommand extends Command {
    public RegistrationCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "Registration";
    }

    @SneakyThrows
    @Override
    public void execute() {
        try {
            client.registration();
        } catch (HttpClientErrorException e) {
            String response = e.getResponseBodyAsString();

            ErrorResponse errorResponse = new ObjectMapper()
                    .readValue(response, ErrorResponse.class);

            System.out.println(errorResponse.getErrorResponse());
        }
    }
}
