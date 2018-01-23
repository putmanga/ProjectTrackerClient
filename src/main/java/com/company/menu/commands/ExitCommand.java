package com.company.menu.commands;

import com.company.Client;

import java.io.BufferedReader;

public class ExitCommand extends Command {

    public ExitCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getName() {
        return "Exit";
    }

    @Override
    public void execute() {
    }
}
