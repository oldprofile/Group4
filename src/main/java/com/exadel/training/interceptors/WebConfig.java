package com.exadel.training.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by HP on 31.07.2015.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

   @Autowired
  @Qualifier("interceptor")
   private HandlerInterceptor handlerInterceptor;
   @Autowired
   @Qualifier("interceptorRole")
   private HandlerInterceptor handlerInterceptorRole;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor);
        registry.addInterceptor(handlerInterceptorRole);
    }
}
