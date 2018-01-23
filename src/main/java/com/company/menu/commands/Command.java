package com.company.menu.commands;

import com.company.Client;

import java.io.BufferedReader;

public abstract class Command {
    protected Client client;
    BufferedReader reader;
    protected boolean isExit = false;

    public Command(Client client, BufferedReader reader) {
        this.client = client;
        this.reader = reader;
    }

    public abstract String getName();

    public abstract void execute();

    public boolean isExit() {
        return isExit;
    }
}
