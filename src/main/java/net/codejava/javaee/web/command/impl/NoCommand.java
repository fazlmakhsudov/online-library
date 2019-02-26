package net.codejava.javaee.web.command.impl;


import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.logging.Logger;

public class NoCommand implements Command, Serializable {

    private static final long serialVersionUID = -2785976616686657267L;

    private static final Logger LOG = Logger.getLogger("NoCommand");

    public final String execute(final HttpServletRequest request,
                                final HttpServletResponse response) {
        LOG.info("Command starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);

        LOG.info(String.format("Set the request attribute: errorMessage --> %s", errorMessage));

        LOG.info("Command finished");
        return Path.PAGE_ERROR;
    }

}