package com.company.menu.commands;

import com.company.Client;
import com.company.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedReader;

public class LogoutCommand extends Command {

    public LogoutCommand(Client client, BufferedReader reader) {
        super(client, reader);
        isExit = true;
    }

    @Override
    public String getName() {
        return "Log Out";
    }


    @SneakyThrows
    @Override
    public void execute() {
        try {
            client.logout();
        } catch (HttpClientErrorException e) {
            String response = e.getResponseBodyAsString();

            ErrorResponse errorResponse = new ObjectMapper()
                    .readValue(response, ErrorResponse.class);

            System.out.println(errorResponse.getErrorResponse());

            //do not exit in case of an error
            isExit = false;
        }
    }

    @Override
    public boolean isExit() {
        return isExit;
    }

}
