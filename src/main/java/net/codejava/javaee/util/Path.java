package net.codejava.javaee.util;

public final class Path {

    /*
     * Pages
     */
    public static final String PAGE_MAIN = "/WEB-INF/jsp/home.jsp";

    public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";

    public static final String PAGE_SIGNUP = "/WEB-INF/jsp/signup.jsp";

    public static final String PAGE_ERROR = "/WEB-INF/jsp/error.jsp";

    public static final String PAGE_USER_LIST = "/WEB-INF/jsp/UserList.jsp";
    public static final String PAGE_USER_FORM = "/WEB-INF/jsp/UserForm.jsp";
    public static final String PAGE_BOOK_LIST = "/WEB-INF/jsp/BookList.jsp";
    public static final String PAGE_BOOK_FORM = "/WEB-INF/jsp/BookForm.jsp";

    /*
     * Commands
     */
    public static final String COMMAND_MAIN = "controller?action=main";
    public static final String COMMAND_USER_LIST = "controller?action=listUsers";
    public static final String COMMAND_BOOK_LIST = "controller?action=listBooks";
    private Path() {
    }

}