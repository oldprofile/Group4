package com.exadel.training.notification;

/**
 * Created by asd on 03.08.2015.
 */
public class MessageFabric {
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
                return new String("Sorry, your " + trainingName + " training was canceled.");
            case OneDayLeft:
                return new String("One day left before your " + trainingName + " training, please, don't forget!");
            case OneHourLeft:
                return new String("Don't forget about your " + trainingName + " training, one hour left!");
            case Race:
                return new String("We offer you a place on a " + trainingName + " training. Hurry up and contact us if you ready to take it.");
            case Subscribe:
                return new String("you have subscribed to "+trainingName);
        }
        return "";
    }

}
