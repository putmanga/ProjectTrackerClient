package com.company.menu.commands;

import com.company.Client;

import java.io.BufferedReader;

public class BackCommand extends Command {
    public BackCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "Back";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {

    }
}
