package net.codejava.javaee.util;

public final class Path {

    /*
     * Pages
     */
    public static final String PAGE_MAIN = "/WEB-INF/jsp/home.jsp";

    public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";

    public static final String PAGE_SIGNUP = "/WEB-INF/jsp/signup.jsp";

    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    public static final String FORWARD_USER_LIST_PAGE = "Store/command?action=listUsers";
    public static final String FORWARD_BOOK_LIST_PAGE = "Store/command?action=listbBooks";
    public static final String USER_LIST_PAGE = "/user/UserList.jsp";
    public static final String USER_FORM_PAGE = "/user/UserForm.jsp";
    public static final String BOOK_LIST_PAGE = "/book/BookList.jsp";
    public static final String BOOK_FORM_PAGE = "/book/BookForm.jsp";

    /*
     * Commands
     */
    public static final String COMMAND_MAIN = "command?action=main";

    private Path() {
    }

}