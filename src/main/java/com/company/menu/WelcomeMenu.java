package com.company.menu;

import com.company.Client;
import com.company.menu.commands.ExitCommand;
import com.company.menu.commands.LoginCommand;
import com.company.menu.commands.RegistrationCommand;

import java.io.BufferedReader;

public class WelcomeMenu extends Menu {

    public WelcomeMenu(Client client, BufferedReader reader) {
        super(client, reader);
    }

    @Override
    protected void initMenu() {
        commands.add(new RegistrationCommand(client, reader));
        commands.add(new LoginCommand(client, reader));
        commands.add(new ExitCommand(client, reader));
    }

}
