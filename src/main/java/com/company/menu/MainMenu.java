package com.company.menu;

import com.company.Client;
import com.company.menu.commands.GetAllUsersCommand;
import com.company.menu.commands.ItemManagementCommand;
import com.company.menu.commands.LogoutCommand;
import com.company.menu.commands.UserInfoCommand;

import java.io.BufferedReader;

public class MainMenu extends Menu {
    public MainMenu(Client client, BufferedReader reader) {
        super(client, reader);
    }


    @Override
    protected void initMenu() {
        commands.add(new UserInfoCommand(client, reader));
        commands.add(new ItemManagementCommand(client, reader));
        commands.add(new LogoutCommand(client, reader));
    }
}
