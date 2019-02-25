package net.codejava.javaee.web;

import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.command.impl.CommandContainer;
import net.codejava.javaee.web.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        name = "controller",
        urlPatterns = "/command"
)
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2423353715955164816L;

    private static final Logger LOG = Logger.getLogger("Controller");

    @Override
    protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected final void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {

        LOG.info("Controller starts");
        // extract command name from the request
        String commandName = request.getParameter("action");
        LOG.info("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);

        LOG.info("Obtained command --> " + command);

        // execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(request, response);
        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            LOG.info(String.format("Error occured: %s", ex.getMessage()));
        }

        LOG.info("Forward address --> " + forward);

        LOG.info("Controller finished, now go to forward address --> " + forward);

        /**
         *
         * Redirect if command (controller?command=main)
         * Forward if page (/WEB-INF/jsp/home.jsp)
         *
         */
        if (forward.contains("command?action=main")) {
            response.sendRedirect(forward);
        } else {
            request.getRequestDispatcher(forward).forward(request, response);
        }
    }

}