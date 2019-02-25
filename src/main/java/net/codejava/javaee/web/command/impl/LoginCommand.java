package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.util.Method;
import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

public class LoginCommand implements Command, Serializable {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("LoginCommand");

    @Override
    public final String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");
        request.setAttribute("command", "main");
        String forward;
        if (Method.isPost(request)) {
            forward = Path.COMMAND_MAIN;
            //todo process post
        } else {
            forward = Path.PAGE_LOGIN;
            //todo process get
        }
        LOG.info("Command finished");
        return forward;
    }

}