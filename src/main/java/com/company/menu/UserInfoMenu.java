package com.company.menu;

import com.company.Client;
import com.company.menu.commands.BackCommand;
import com.company.menu.commands.GetAllUsersCommand;
import com.company.menu.commands.GetUserByIdCommand;
import com.company.menu.commands.UpdateUserCommand;

import java.io.BufferedReader;

public class UserInfoMenu extends Menu {

    public UserInfoMenu(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    protected void initMenu() {
        commands.add(new GetAllUsersCommand(client, reader));
        commands.add(new GetUserByIdCommand(client, reader));
        commands.add(new UpdateUserCommand(client, reader));
        commands.add(new BackCommand(client, reader));
    }
}
