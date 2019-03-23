package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.dao.UserDAO;
import net.codejava.javaee.entity.User;
import net.codejava.javaee.util.Method;
import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.exception.AppException;
import net.codejava.javaee.web.filter.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SignupCommand implements Command, Serializable {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("MainCommand");

    @Override
    public final String execute(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");
        request.setAttribute("command", "index");
        String forward;
        if (Method.isPost(request)) {
            HttpSession session = request.getSession();

            String password = request.getParameter("password");
            String confirm_password = request.getParameter("confirm_password");
            String email = request.getParameter("email");
            User user = null;
            boolean flag = true;
            if (!password.equals(confirm_password)) {
                password = "not match";
                flag = false;
            } else {
                try {
                    new UserDAO().insertUser(email, password);
                    user = new UserDAO().getUser(email, password);
                } catch (SQLException ex) {
                    throw new ServletException(ex);
                }
            }
            if (flag&&user != null) {
                session.setAttribute("userRole", Role.CLIENT);
                session.setAttribute("userId",user.getId());
                forward = Path.COMMAND_MAIN;
            } else {
                forward = Path.PAGE_LOGIN;
            }
            //todo process post
        } else {
            forward = Path.PAGE_SIGNUP;
            //todo process get
        }
        LOG.info("Command finished");
        return forward;
    }

}