package net.codejava.javaee.util;

public class DBInfo {
    private static String jdbcURL;
    private static String jdbcUsername;
    private static String jdbcPassword;

    static {
        jdbcURL = "jdbc:mysql://localhost:3306/store";
        jdbcUsername = "root";
        jdbcPassword = "YES";
    }

    public static String getJdbcURL() {
        return jdbcURL;
    }

    public static String getJdbcUsername() {
        return jdbcUsername;
    }

    public static String getJdbcPassword() {
        return jdbcPassword;
    }
}
