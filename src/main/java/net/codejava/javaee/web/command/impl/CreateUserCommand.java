package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.entity.User;
import net.codejava.javaee.DAO.UserDAO;
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

public class CreateUserCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("CreateUserCommand");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");

        String forward;
        if (Method.isPost(request)) {
            request.setAttribute("command", "Create User Command");
            forward = Path.COMMAND_USER_LIST;
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            User newUser = new User(name, password, email);
            try {
                new UserDAO().insertUser(newUser);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            //todo process post
        } else {
            request.setAttribute("command", "New User Form Command");
            forward = Path.PAGE_USER_FORM;
            //todo process get
        }
        LOG.info("Command finished");
        System.out.println("forward : " + forward);
        return forward;
    }
}
