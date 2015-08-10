package com.exadel.training.interceptors.access.impl;

import com.exadel.training.interceptors.access.Access;
import com.exadel.training.interceptors.util.RoleUtil;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HP on 01.08.2015.
 */
@Singleton
@Service
public class AccessImpl implements Access{

    private final static String TRAINING_CONTROLLER = "/training_controller";
    private final static String USER_CONTROLLER = "/user_controller";
    private final static String SEARCH_CONTROLLER = "/search_controller";
    private final static String NEWS_CONTROLLER = "/news_controller";
    private final static String OMISSION_CONTROLLER = "/omission_controller";
    private final static String FEEDBACK_CONTROLLER = "/feedbacks";
    private final static String AUTHENTIFICATION_CONTROLLER = "/authentication";
    private final static String CATEGORY_CONTROLLER = "";

    private ConcurrentHashMap<Long,List<String>> access = new ConcurrentHashMap<>();

    public AccessImpl() {

        access.put(RoleUtil.ADMIN, this.adminAccess());
        access.put(RoleUtil.EMPLOYEE, this.adminAccess());
        access.put(RoleUtil.EX_COACH, this.excoachAccess());
        access.put(RoleUtil.EX_EMPLOYEE, new ArrayList<>());

    }

    private List<String> adminAccess() {
        List<String> admin = new LinkedList<>();

        admin.add(USER_CONTROLLER + "/find_by_role");
        admin.add(USER_CONTROLLER + "/user_info");
        admin.add(USER_CONTROLLER + "/all_trainings_of_user");
        admin.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_coach");
        admin.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_student");
        admin.add(USER_CONTROLLER + "/find_user_by_login");
        admin.add(USER_CONTROLLER + "/leave_training");
        admin.add(USER_CONTROLLER + "/join_training");
        admin.add(USER_CONTROLLER + "/all_trainings_sorted_by_date");
        admin.add(USER_CONTROLLER + "/find_my_training");
        admin.add(USER_CONTROLLER + "/find_coach_of_user");
        admin.add(USER_CONTROLLER + "/insert_phone");
        admin.add(USER_CONTROLLER + "/insert_external_employee");
        admin.add(USER_CONTROLLER + "/select_all_users_login");
        admin.add(USER_CONTROLLER + "/select_all_users_exemployee");
        admin.add(USER_CONTROLLER + "/select_all_users_excoach");
        admin.add(USER_CONTROLLER + "/insert_external_coach");

        admin.add(SEARCH_CONTROLLER + "/search_users");
        admin.add(SEARCH_CONTROLLER + "/search_trainings");

        admin.add(NEWS_CONTROLLER + "/pages");
        admin.add(NEWS_CONTROLLER + "/count_of_news");
        admin.add(NEWS_CONTROLLER + "/unread");
        admin.add(NEWS_CONTROLLER + "/change_unread");

        admin.add(OMISSION_CONTROLLER + "/statistics");
        admin.add(OMISSION_CONTROLLER + "/get_omissions");
        admin.add(OMISSION_CONTROLLER + "/add_omissions");

        admin.add(FEEDBACK_CONTROLLER + "/user_feedback");
        admin.add(FEEDBACK_CONTROLLER + "/create_user_feedback");
        admin.add(FEEDBACK_CONTROLLER + "/coach_feedback");
        admin.add(FEEDBACK_CONTROLLER + "/create_coach_feedback");
        admin.add(FEEDBACK_CONTROLLER + "/training_feedback");
        admin.add(FEEDBACK_CONTROLLER + "/create_training_feedback");
        admin.add(FEEDBACK_CONTROLLER + "/request_user_feedback");

        admin.add(TRAINING_CONTROLLER + "/training_info");
        admin.add(TRAINING_CONTROLLER + "/list");
        admin.add(TRAINING_CONTROLLER + "/names_list");
        admin.add(TRAINING_CONTROLLER + "/list_by_states");
        admin.add(TRAINING_CONTROLLER + "/create_training");
        admin.add(TRAINING_CONTROLLER + "/edit_training");
        admin.add(TRAINING_CONTROLLER + "/list_by_category");
        admin.add(TRAINING_CONTROLLER + "/search_training");
        admin.add(TRAINING_CONTROLLER + "/latest_trainings");
        admin.add(TRAINING_CONTROLLER + "/featured_trainings");
        admin.add(TRAINING_CONTROLLER + "/date_info");
        admin.add(TRAINING_CONTROLLER + "/edited_training_info");
        admin.add(TRAINING_CONTROLLER + "/approve_create_training");
        admin.add(TRAINING_CONTROLLER + "/approve_edit_training");
        admin.add(TRAINING_CONTROLLER + "/delete_training");
        admin.add(TRAINING_CONTROLLER + "/trainings_for_approve");
        admin.add(TRAINING_CONTROLLER + "/change_date");
        admin.add(TRAINING_CONTROLLER + "/delete_date");
        admin.add(TRAINING_CONTROLLER + "/add_date");
        admin.add(TRAINING_CONTROLLER + "/listeners");
        admin.add(TRAINING_CONTROLLER + "/add_files");
        admin.add(TRAINING_CONTROLLER + "/delete_file");
        admin.add(TRAINING_CONTROLLER + "/files_info");

        admin.add(AUTHENTIFICATION_CONTROLLER + "/logout");
        admin.add(AUTHENTIFICATION_CONTROLLER + "/log_password");

        admin.add(CATEGORY_CONTROLLER + "/allCategories");

        return admin;
    }

    private List<String> employeeAccess() {
        List<String> employee = new LinkedList<>();

        employee.add(USER_CONTROLLER + "/find_by_role");
        employee.add(USER_CONTROLLER + "/user_info");
        employee.add(USER_CONTROLLER + "/all_trainings_of_user");
        employee.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_coach");
        employee.add(USER_CONTROLLER + "/all_trainings_of_user_by_type_student");
        employee.add(USER_CONTROLLER + "/find_user_by_login");
        employee.add(USER_CONTROLLER + "/leave_training");
        employee.add(USER_CONTROLLER + "/join_training");
        employee.add(USER_CONTROLLER + "/all_trainings_sorted_by_date");
        employee.add(USER_CONTROLLER + "/find_my_training");
        employee.add(USER_CONTROLLER + "/find_coach_of_user");
        employee.add(USER_CONTROLLER + "/insert_phone");
        employee.add(USER_CONTROLLER + "/select_all_users_login");
        employee.add(USER_CONTROLLER + "/select_all_users_exemployee");
        employee.add(USER_CONTROLLER + "/select_all_users_excoach");

        employee.add(SEARCH_CONTROLLER + "/search_trainings");

        employee.add(OMISSION_CONTROLLER + "/get_omissions");
        employee.add(OMISSION_CONTROLLER + "/add_omissions");

        employee.add(FEEDBACK_CONTROLLER + "/user_feedback");
        employee.add(FEEDBACK_CONTROLLER + "/create_user_feedback");
        employee.add(FEEDBACK_CONTROLLER + "/coach_feedback");
        employee.add(FEEDBACK_CONTROLLER + "/create_coach_feedback");
        employee.add(FEEDBACK_CONTROLLER + "/training_feedback");
        employee.add(FEEDBACK_CONTROLLER + "/create_training_feedback");

        employee.add(TRAINING_CONTROLLER + "/training_info");
        employee.add(TRAINING_CONTROLLER + "/list");
        employee.add(TRAINING_CONTROLLER + "/list_by_states");
        employee.add(TRAINING_CONTROLLER + "/list_by_category");
        employee.add(TRAINING_CONTROLLER + "/create_training");
        employee.add(TRAINING_CONTROLLER + "/edit_training");
        employee.add(TRAINING_CONTROLLER + "/search_training");
        employee.add(TRAINING_CONTROLLER + "/latest_trainings");
        employee.add(TRAINING_CONTROLLER + "/featured_trainings");
        employee.add(TRAINING_CONTROLLER + "/date_info");

        employee.add(AUTHENTIFICATION_CONTROLLER + "/logout");
        employee.add(AUTHENTIFICATION_CONTROLLER + "/log_password");

        employee.add(CATEGORY_CONTROLLER + "/allCategories");

        return employee;
    }
    private List<String> excoachAccess() {
        List<String> excoach = new LinkedList<>();

        excoach.add(OMISSION_CONTROLLER + "/get_omissions");
        excoach.add(OMISSION_CONTROLLER + "/add_omissions");

        excoach.add(TRAINING_CONTROLLER + "/training_info");
        excoach.add(TRAINING_CONTROLLER + "/edit_training");
        excoach.add(TRAINING_CONTROLLER + "/date_info");

        excoach.add(AUTHENTIFICATION_CONTROLLER + "/logout");
        excoach.add(AUTHENTIFICATION_CONTROLLER + "/log_password");

        return excoach;
    }

    @Override
    public Boolean isAllowMethod(Long role, String method) {
        List<String> methods = access.get(role);
        return methods.contains(method);
    }
}
