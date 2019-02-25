package net.codejava.javaee.util;


import javax.servlet.http.HttpServletRequest;

public final class Method {

    public static boolean isGet(final HttpServletRequest request) {
        return request.getMethod().equals("GET");
    }


    public static boolean isPost(final HttpServletRequest request) {
        return request.getMethod().equals("POST");
    }

    private Method() {
    }
}