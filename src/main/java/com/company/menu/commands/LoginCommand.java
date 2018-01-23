package com.company.menu.commands;

import com.company.Client;
import com.company.menu.MainMenu;
import com.company.menu.Menu;
import com.company.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.bind.ValidationException;
import java.io.BufferedReader;

public class LoginCommand extends Command {
    public LoginCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "Log In";
    }

    @SneakyThrows
    @Override
    public void execute() {
        try {
            client.login();
            Menu menu = new MainMenu(client, reader);
            menu.runMenu();
        } catch (HttpClientErrorException e) {
            String response = e.getResponseBodyAsString();

            ErrorResponse errorResponse = new ObjectMapper()
                    .readValue(response, ErrorResponse.class);

            System.out.println(errorResponse.getErrorResponse());
        } catch (ValidationException e) {
            System.out.println("Log in failed.");
        }
    }
}
