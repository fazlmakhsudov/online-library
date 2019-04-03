package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.repository.BookRepository;
import net.codejava.javaee.repository.UserRepository;
import net.codejava.javaee.repository.impl.MySQLBookRepositoryImpl;
import net.codejava.javaee.repository.impl.MySQLUserRepositoryImpl;
import net.codejava.javaee.service.BookService;
import net.codejava.javaee.service.UserService;
import net.codejava.javaee.service.impl.BookServiceImpl;
import net.codejava.javaee.service.impl.UserServiceImpl;
import net.codejava.javaee.util.DBInfo;
import net.codejava.javaee.util.DBUtil;
import net.codejava.javaee.web.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class CommandContainer {

    private static final Logger LOG = Logger.getLogger("CommandContainer");

    private static Map<String, Command> commands = new HashMap<>();

    static {
        DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(),DBInfo.getJdbcUsername(),DBInfo.getJdbcPassword());
        UserRepository userRepository = new MySQLUserRepositoryImpl(dbUtil);
        BookRepository bookRepository = new MySQLBookRepositoryImpl(dbUtil);
        UserService userService= new UserServiceImpl(userRepository);
        BookService bookService = new BookServiceImpl(bookRepository);
        commands.put("main", new MainCommand());
        commands.put("login", new LoginCommand(userService));
        commands.put("registration", new SignupCommand(userService));
        commands.put("noCommand", new NoCommand());
        commands.put("createUser", new CreateUserCommand(userService));
        commands.put("readUser", new ReadUserCommand(userService));
        commands.put("updateUser", new UpdateUserCommand(userService));
        commands.put("deleteUser", new DeleteUserCommand(userService));
        commands.put("listUsers", new ListUsersCommand(userService));
        commands.put("createBook", new CreateBookCommand(bookService));
        commands.put("readBook", new ReadBookCommand(bookService));
        commands.put("updateBook", new UpdateBookCommand(bookService));
        commands.put("deleteBook", new DeleteBookCommand(bookService));
        commands.put("listBooks", new ListBooksCommand(bookService));
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