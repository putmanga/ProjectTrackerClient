package com.company.menu.commands;

import com.company.Client;
import com.company.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;

public class GetUserByIdCommand extends Command {
    public GetUserByIdCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "Get User By ID";
    }

    @SneakyThrows
    @Override
    public void execute() {
        try {
            client.getUserById();
        } catch (HttpClientErrorException e) {
            String response = e.getResponseBodyAsString();

            ErrorResponse errorResponse = new ObjectMapper()
                    .readValue(response, ErrorResponse.class);

            System.out.println(errorResponse.getErrorResponse());
        }
    }
}
