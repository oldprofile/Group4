package com.exadel.training.common;

import sun.net.www.protocol.http.AuthenticationHeader;

/**
 * Created by Клим on 14.07.2015.
 */
public enum StateTraining {
    Finished,
    Ahead,
    InProcess,
    Canceled;

    public static StateTraining parseStringToStateTraining(String state) throws NoSuchFieldException {
        switch (state) {
            case "Finished": {
                return StateTraining.Finished;
            }
            case "Ahead": {
                return  StateTraining.Ahead;
            }
            case "InProcess": {
                return  StateTraining.InProcess;
            }
            case  "Canceled": {
                return  StateTraining.Canceled;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public static String parseStateTrainingToString(StateTraining state) throws NoSuchFieldException {
        switch (state) {
            case InProcess: {
                return "InProcess";
            }
            case Ahead: {
                return "Ahead";
            }
            case Finished: {
                return "Finished";
            }
            case Canceled: {
                return "Canceled";
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
}
