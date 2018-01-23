package com.company;

import com.company.menu.WelcomeMenu;
import com.company.menu.Menu;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App
{
    private static final String SESSION_ID = "sessionId";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public static void main( String[] args ) {

        Client client = new Client(reader);
        Menu menu = new WelcomeMenu(client, reader);

        menu.runMenu();

        reader.close();
    }
}
