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

public class UpdateUserCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("updateUserCommand");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");

        String forward;
        if (Method.isPost(request)) {
            request.setAttribute("command", "Update User Command");
            forward = Path.COMMAND_USER_LIST;
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            User user = new User(id, name, password, email);
            try {
                new UserDAO().updateUser(user);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            //todo process post
        } else {
            request.setAttribute("command", "Edit User Form Command");
            forward = Path.PAGE_USER_FORM;
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                User existingUser = new UserDAO().getUser(id);
                request.setAttribute("user", existingUser);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            //todo process get
        }
        LOG.info("Command finished");
        return forward;
    }
}
