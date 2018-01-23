package com.company.menu;

import com.company.Client;
import com.company.menu.commands.*;

import java.io.BufferedReader;

public class ItemManagementMenu extends Menu {

    public ItemManagementMenu(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    protected void initMenu() {
        commands.add(new CreateItemCommand(client, reader));
        commands.add(new GetAllItemsCommand(client, reader));
        commands.add(new GetMyItemsCommand(client, reader));
        commands.add(new BackCommand(client, reader));
    }
}
