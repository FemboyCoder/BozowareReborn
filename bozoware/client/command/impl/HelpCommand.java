package bozoware.client.command.impl;

import bozoware.client.command.Command;
import bozoware.client.command.CommandAnnotation;

@CommandAnnotation(name = "", usages = {"help"}, description = "help command")
public class HelpCommand extends Command {

    @Override
    protected void run() {

    }
}
