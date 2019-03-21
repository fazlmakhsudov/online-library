package net.codejava.javaee.web.filter;

import net.codejava.javaee.util.Path;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


@WebFilter(filterName = "CommandAccessFilter", servletNames = {"controller"},
        initParams = {
                @WebInitParam(name = "admin", value = "main createUser readUser updateUser deleteUser listUsers createBook " +
                        "readBook updateBook deleteBook listBooks"),
                @WebInitParam(name = "client", value = "main createBook readBook updateBook deleteBook listBooks"),
                @WebInitParam(name = "common", value = ""),
                @WebInitParam(name = "out-of-control", value = "login registration signup")
        })
public class CommandAccessFilter implements Filter {

    private static final Logger LOG =
            Logger.getLogger("CommandAccessFilter");

    private Map<Role, List<String>> accessMap = new HashMap<>();

    private List<String> commons = new ArrayList<>();

    private List<String> outOfControl = new ArrayList<>();

    @Override
    public final void destroy() {
    }

    @Override
    public final void doFilter(final ServletRequest request,
                               final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        LOG.info("Filter starts");

        if (accessAllowed(request)) {
            LOG.info("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessasge = "You do not have permission "
                    + "to access the requested resource";

            request.setAttribute("errorMessage", errorMessasge);
            LOG.info("Set the request attribute: errorMessage --> "
                    + errorMessasge);

            request.getRequestDispatcher(
                    Path.PAGE_ERROR).forward(request, response);
        }
    }

    /**
     * Check whether access allowed.
     *
     * @param request request
     * @return boolean
     */
    private boolean accessAllowed(final ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("action");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        System.out.println("UserRole " + userRole.toString());
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    @Override
    public final void init(final FilterConfig fConfig) {
        LOG.info("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
        LOG.info("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        LOG.info("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOG.info("Out of control commands --> " + outOfControl);

        LOG.info("Filter initialization finished");
    }

    private List<String> asList(final String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

}