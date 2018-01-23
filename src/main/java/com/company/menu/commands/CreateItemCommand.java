package com.company.menu.commands;

import com.company.Client;
import com.company.menu.MainMenu;
import com.company.menu.Menu;
import com.company.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;

public class CreateItemCommand extends Command {
    public CreateItemCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "Create Item";
    }

    @SneakyThrows
    @Override
    public void execute() {
        try {
            client.createItem();
        } catch (HttpClientErrorException e) {
            String response = e.getResponseBodyAsString();

            ErrorResponse errorResponse = new ObjectMapper()
                    .readValue(response, ErrorResponse.class);

            System.out.println(errorResponse.getErrorResponse());
        }
    }
}
