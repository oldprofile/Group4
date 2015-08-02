package com.exadel.training.interceptors;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 01.08.2015.
 */
public class Access {

    private final static String USER_CONTROLLER = "/user_controller";
    private final static String SEARCH_CONTROLLER = "/search_controller";
    private final static String NEWS_CONTROLLER = "/news_controller";

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

        admin.add(SEARCH_CONTROLLER + "/search_users");
        admin.add(SEARCH_CONTROLLER + "/search_trainings");

        admin.add(NEWS_CONTROLLER + "/pages/{pageNumber}");
        admin.add(NEWS_CONTROLLER + "/count_of_news");
        admin.add(NEWS_CONTROLLER + "/unread/{state}");

        return admin;
    }

    private List<String> employeeAccess() {
        List<String> employee = new LinkedList<>();

        employee.add(USER_CONTROLLER + "/find_by_role/{type}");
        employee.add(USER_CONTROLLER + "/user_info/{login}");
        employee.add(USER_CONTROLLER + "/all_trainings_of_user");
        employee.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_coach");
        employee.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_student");
        employee.add(USER_CONTROLLER + "/find_user_by_login");
        employee.add(USER_CONTROLLER + "/leave_training");
        employee.add(USER_CONTROLLER + "/join_training");
        employee.add(USER_CONTROLLER + "/all_trainings_sorted_by_date");
        employee.add(USER_CONTROLLER + "/find_my_training");
        employee.add(USER_CONTROLLER + "/find_coach_of_user/{login}");
        employee.add(USER_CONTROLLER + "/insert_phone");

        employee.add(SEARCH_CONTROLLER + "/search_trainings");

        return employee;
    }
}
