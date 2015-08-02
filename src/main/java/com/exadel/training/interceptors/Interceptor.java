package com.exadel.training.interceptors;

import com.exadel.training.tokenAuthentification.SessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HP on 31.07.2015.
 */
@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private SessionToken sessionToken;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if(this.isAuthentication(uri)) {
            return true;
        } else {
            if (!sessionToken.isEmpty()) {
                String header = httpServletRequest.getHeader("authorization");
                if (sessionToken.containsToken(header)) {
                    httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
                    return true;
                } else {
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
            }
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    private boolean isAuthentication(String uri) {
        return uri.equalsIgnoreCase("/authentication/log_password");
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
