package com.servlet.project.controller.filters;

import com.servlet.project.model.entity.Role;
import com.servlet.project.model.entity.User;
import com.servlet.project.model.service.SecurityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/event/*")
public class AuthFilter implements Filter {
    private static final Logger log = LogManager.getLogger(AuthFilter.class);
    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        securityService = new SecurityService();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        User user = securityService.getLoggedUser(session);

        try {
            if (Objects.isNull(user) || (user.getRole() != Role.MODERATOR)) {
                request.getRequestDispatcher("/WEB-INF/view/errors/403.jsp").forward(request, response);
                return;
            }
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            log.error("Authentication error", e);
        }
    }

    @Override
    public void destroy() {
    }
}
