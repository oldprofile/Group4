package com.exadel.training.common;

import sun.net.www.protocol.http.AuthenticationHeader;

/**
 * Created by Клим on 14.07.2015.
 */
public enum StateTraining {
    Finished,
    Ahead,
    InProcess,
    Canceled,
    Draft;

    public static StateTraining parseStringToStateTraining(String state) throws NoSuchFieldException {
        switch (state) {
            case "1": {
                return StateTraining.Finished;
            }
            case "2": {
                return  StateTraining.Ahead;
            }
            case "3": {
                return  StateTraining.InProcess;
            }
            case  "4": {
                return  StateTraining.Canceled;
            }
            case  "5": {
                return  StateTraining.Draft;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public static String parseStateTrainingToString(StateTraining state) throws NoSuchFieldException {
        switch (state) {
            case InProcess: {
                return "1";
            }
            case Ahead: {
                return "2";
            }
            case Finished: {
                return "3";
            }
            case Canceled: {
                return "4";
            }
            case Draft: {
                return "5";
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
}
