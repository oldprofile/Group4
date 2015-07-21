package com.exadel.training.common;

import sun.net.www.protocol.http.AuthenticationHeader;

/**
 * Created by Клим on 14.07.2015.
 */
public enum StateTraining {
    Draft,
    Ahead,
    InProcess,
    Edited,
    Finished,
    Canceled;

    public static String parseToString(int state) throws NoSuchFieldException {
        switch (state) {
            case 1: {
                return "Draft";
            }
            case 2: {
                return  "Ahead";
            }
            case 3: {
                return  "InProcess";
            }
            case 4: {
                return "Edited";
            }
            case  5: {
                return  "Finished";
            }
            case  6: {
                return  "Canceled";
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public static int parseToInt(String state) throws NoSuchFieldException {
        switch (state) {
            case "Draft": {
                return 1;
            }
            case "Ahead": {
                return 2;
            }
            case "InProcess": {
                return 3;
            }
            case "Edited": {
                return 4;
            }
            case "Finished": {
                return 5;
            }
            case "Canceled": {
                return 6;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
}
