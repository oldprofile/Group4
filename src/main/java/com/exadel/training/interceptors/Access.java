package com.exadel.training.interceptors;

import com.exadel.training.model.Role;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 01.08.2015.
 */
public class Access {

    private ConcurrentHashMap<Role,List<String>> access = new ConcurrentHashMap<>();
}
