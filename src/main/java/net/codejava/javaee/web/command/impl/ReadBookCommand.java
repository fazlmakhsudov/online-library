package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.entity.Book;
import net.codejava.javaee.service.BookService;
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

public class ReadBookCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("readBookCommand");
    private BookService bookService;

    public ReadBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");
        request.setAttribute("command", "Read Book Command");
        String forward;
        // I am not sure what to show
        if (Method.isGet(request)) {
           forward=Path.PAGE_BOOK_FORM;
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Book existingBook = bookService.find(id);
                request.setAttribute("book", existingBook);
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