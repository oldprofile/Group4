package com.exadel.training.interceptors.access;

/**
 * Created by HP on 06.08.2015.
 */
public interface Access {
    Boolean isAllowMethod(Long role, String method);
}
