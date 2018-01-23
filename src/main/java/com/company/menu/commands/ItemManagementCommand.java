package com.company.menu.commands;

import com.company.Client;
import com.company.menu.ItemManagementMenu;
import com.company.menu.Menu;

import java.io.BufferedReader;

public class ItemManagementCommand extends Command {
    public ItemManagementCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "Item Management";
    }

    @Override
    public void execute() {
        Menu menu = new ItemManagementMenu(client, reader);
        menu.runMenu();
    }
}
