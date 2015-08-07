package com.exadel.training.notification;

/**
 * Created by asd on 03.08.2015.
 */
public class MessageFabric {
    private static final String TRAINING_LINK = "http://localhost:8080/#/courseinfo/";
    private static final String USER_LINK = "http://localhost:8080/#/profile/";

   public static enum messageType{
        Canceled,
        OneHourLeft,
        OneDayLeft,
        Subscribe,
        Race;
    }

    public static String getMessage(messageType messageType, String trainingName) {
        switch (messageType){
            case Canceled:
                return new String("Sorry, your " + trainingName + " training was canceled. Watch link: " + TRAINING_LINK + trainingName);
            case OneDayLeft:
                return new String("One day left before your " + trainingName + " training, please, don't forget! Watch link: " + TRAINING_LINK + trainingName);
            case OneHourLeft:
                return new String("Don't forget about your " + trainingName + " training, one hour left! Watch link: " + TRAINING_LINK + trainingName);
            case Race:
                return new String("We offer you a place on a " + trainingName + " training. Hurry up and contact us if you ready to take it. Watch link:" + TRAINING_LINK + trainingName);
            case Subscribe:
                return new String("You have subscribed to " + trainingName + ". Watch link: " + TRAINING_LINK + trainingName);
        }
        return "";
    }

    public static String getMessage(String trainingName, String userName, String userLogin) {
        return new String ("Please, leave your feedback on " + userName + " within " + trainingName + " training. Go to:" + USER_LINK + userLogin);
    }

}
