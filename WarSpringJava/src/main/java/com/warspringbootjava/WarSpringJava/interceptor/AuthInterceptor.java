package com.warspringbootjava.WarSpringJava.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse res,
                             Object handler) throws Exception {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/");
            return false;
        }
        return true;
    }
	
}
