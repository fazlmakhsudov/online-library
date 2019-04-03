package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.entity.User;
import net.codejava.javaee.service.UserService;
import net.codejava.javaee.service.impl.UserServiceImpl;
import net.codejava.javaee.util.Method;
import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Logger;

public class LoginCommand implements Command, Serializable {

    private static final long serialVersionUID = -3071536593627692473L;
    private UserService userService;
    private static final Logger LOG = Logger.getLogger("LoginCommand");

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public final String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");
        request.setAttribute("command", "main");
        String forward;
        if (Method.isPost(request)) {
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            LOG.info("Request parameter: email --> " + email);
            String password = request.getParameter("password");
            User user = null;
            try {
                user = userService.find(email, password);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            if (user != null) {
                session.setAttribute("userRole", user.getRole());
                session.setAttribute("userId",user.getId());
                forward = Path.COMMAND_MAIN;
            } else {
                forward = Path.PAGE_LOGIN;
            }
        } else {
            forward = Path.PAGE_LOGIN;
        }
        LOG.info("Command finished");
        return forward;
    }

}