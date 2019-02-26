package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.web.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class CommandContainer {

    private static final Logger LOG = Logger.getLogger("CommandContainer");

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("main", new MainCommand());
        commands.put("login", new LoginCommand());
        commands.put("registration", new SignupCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("readUser", new ReadUserCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("deleteUser", new DeleteUserCommand());
        commands.put("listUsers", new ListUsersCommand());
        commands.put("createBook", new CreateBookCommand());
        commands.put("readBook", new ReadBookCommand());
        commands.put("updateBook", new UpdateBookCommand());
        commands.put("deleteBook", new DeleteBookCommand());
        commands.put("listBooks", new ListBooksCommand());
        LOG.info("Command container was successfully initialized");
        LOG.info("Number of commands --> " + commands.size());
    }

    public static Command get(final String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.info("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

    private CommandContainer() {
    }
}