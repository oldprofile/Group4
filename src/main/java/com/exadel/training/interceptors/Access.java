package com.exadel.training.interceptors;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 01.08.2015.
 */
public class Access {

    private final static String USER_CONTROLLER = "/user_controller";
    private ConcurrentHashMap<Integer,List<String>> access = new ConcurrentHashMap<>();

    public Access() {

    }
    private List<String> adminAccess() {
        List<String> admin = new LinkedList<>();

        admin.add(USER_CONTROLLER + "/find_by_role/{type}");
        admin.add(USER_CONTROLLER + "/user_info/{login}");
        admin.add(USER_CONTROLLER + "/all_trainings_of_user");
        admin.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_coach");
        admin.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_student");
        admin.add(USER_CONTROLLER + "/find_user_by_login");
        admin.add(USER_CONTROLLER + "/leave_training");
        admin.add(USER_CONTROLLER + "/join_training");
        admin.add(USER_CONTROLLER + "/all_trainings_sorted_by_date");
        admin.add(USER_CONTROLLER + "/find_my_training");
        admin.add(USER_CONTROLLER + "/find_coach_of_user/{login}");
        admin.add(USER_CONTROLLER + "/insert_phone");
        admin.add(USER_CONTROLLER + "/insert_external_employee");

        return admin;
    }
}
