package com.company.menu.commands;

import com.company.Client;
import com.company.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;

public class UpdateUserCommand extends Command {
    public UpdateUserCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "Update User";
    }

    @SneakyThrows
    @Override
    public void execute() {
        try {
            client.updateUser();
        } catch (HttpClientErrorException e) {
            String response = e.getResponseBodyAsString();

            ErrorResponse errorResponse = new ObjectMapper()
                    .readValue(response, ErrorResponse.class);

            System.out.println(errorResponse.getErrorResponse());
        }
    }
}
