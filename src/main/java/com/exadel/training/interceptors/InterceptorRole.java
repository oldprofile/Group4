package com.exadel.training.interceptors;

import com.exadel.training.interceptors.access.Access;
import com.exadel.training.interceptors.access.AccessRole;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HP on 06.08.2015.
 */
@Component
public class InterceptorRole implements HandlerInterceptor {

    @Autowired
    private AccessRole accessRole;
    private static final int COUNT_SLASH = 3;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if(!this.isAuthentication(uri) && !uri.equalsIgnoreCase("/")) {

            String login = httpServletRequest.getHeader("login");

            return accessRole.allowMethod(login, mySubString(uri));
        } else {
            return true;
        }
    }

    private String mySubString(String uri) {
        String findUri;
        if(uri.split("/").length-1 == COUNT_SLASH) {
            int first = uri.indexOf('/');
            int last = uri.lastIndexOf('/');
            return findUri = uri.substring(first, last);
        } else {
            return uri;
        }
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
