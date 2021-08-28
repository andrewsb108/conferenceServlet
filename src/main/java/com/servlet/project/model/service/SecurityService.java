package com.servlet.project.model.service;

import com.servlet.project.model.entity.Role;
import com.servlet.project.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityService {
    private static final Logger log = LogManager.getLogger(SecurityService.class);

    public User getLoggedUser(HttpSession session) {
        return (User) session.getAttribute("loggedUser");
    }

    public void storeLoggedUser(HttpSession session, User user) {
        session.setAttribute("loggedUser", user);
    }

    public String setUriByRole(HttpServletRequest request) {
        User user = getLoggedUser(request.getSession());
        if (user.getRole() == Role.MODERATOR) {
            return "/event/all";
        }
        return "/index";
    }

    public boolean passwordIsValid(String candidate, User user) {
        return BCrypt.checkpw(candidate, user.getPassword());
    }

    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
