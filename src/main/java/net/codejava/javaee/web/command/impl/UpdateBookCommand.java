package net.codejava.javaee.web.command.impl;

import net.codejava.javaee.entity.Book;
import net.codejava.javaee.service.impl.BookServiceImpl;
import net.codejava.javaee.util.Method;
import net.codejava.javaee.util.Path;
import net.codejava.javaee.web.command.Command;
import net.codejava.javaee.web.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UpdateBookCommand implements Command {
    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger("updateBookCommand");

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command starts");
        LOG.info("Set request attribute: command index");

        String forward;
        if (Method.isPost(request)) {
            request.setAttribute("command", "Update Book Command");
            forward = Path.COMMAND_BOOK_LIST;
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            float price = Float.parseFloat(request.getParameter("price"));
            HttpSession session = request.getSession(false);
            int user_id = (Integer) session.getAttribute("userId");
            Book book = new Book(id, title, author, price,user_id);

            try {
                BookServiceImpl.getInstance().save(book);
//                new BookDAO().updateBook(book);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
            //todo process post
        } else {
            request.setAttribute("command", "Edit Book Form Command");
            forward = Path.PAGE_BOOK_FORM;
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                Book existingBook = BookServiceImpl.getInstance().find(id);//new BookDAO().getBook(id);
                request.setAttribute("book", existingBook);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }

            //todo process get
        }
        LOG.info("Command finished");
        return forward;
    }
}
