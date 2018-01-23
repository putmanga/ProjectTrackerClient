package com.company.menu;

import com.company.Client;
import com.company.menu.commands.Command;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    Client client;
    BufferedReader reader;

    protected List<Command> commands = new ArrayList<>();

    public Menu(Client client, BufferedReader reader) {
        this.client = client;
        this.reader = reader;
    }

    @SneakyThrows
    public final void runMenu() {
        initMenu();

        while (true) {
            printMenu();
            System.out.println("Choose command:");
            int index = Integer.parseInt(reader.readLine());

            Command command = commands.get(index - 1);
            command.execute();

            if (command.isExit()) {
                break;
            }

        }
    }

    protected abstract void initMenu();

    private void printMenu() {
        for (int i = 0; i < commands.size(); i++) {
            System.out.println(String.format("%d. %s",
                    i + 1,
                    commands.get(i).getName()));
        }
    }
}
