package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.entity.User;
import net.codejava.javaee.service.UserService;
import net.codejava.javaee.util.Method;
import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ReadUserCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;
    private UserService userService;
    private static final Logger LOG = Logger.getLogger("readUserCommand");

    public ReadUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");
        request.setAttribute("command", "Read User Command");
        String forward;
        // I am not sure what to show
        if (Method.isGet(request)) {
            forward=Path.PAGE_USER_FORM;
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                User existingUser = userService.find(id);
                request.setAttribute("user", existingUser);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            //todo process post
        } else {
            forward = Path.COMMAND_MAIN;
            //todo process get
        }
        LOG.info("Command finished");
        return forward;
    }
}