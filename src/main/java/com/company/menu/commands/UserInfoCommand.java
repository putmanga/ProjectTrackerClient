package com.company.menu.commands;

import com.company.Client;
import com.company.menu.Menu;
import com.company.menu.UserInfoMenu;

import java.io.BufferedReader;

public class UserInfoCommand extends Command {
    public UserInfoCommand(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    public String getName() {
        return "User Info";
    }

    @Override
    public void execute() {
        Menu menu = new UserInfoMenu(client, reader);
        menu.runMenu();
    }
}
