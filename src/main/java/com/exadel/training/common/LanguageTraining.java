package com.exadel.training.common;

/**
 * Created by Клим on 15.07.2015.
 */
public enum LanguageTraining {
    Russian,
    English;

    public static int parseToInt(String language) throws NoSuchFieldException {
        switch (language) {
            case "Russian": {
                return 1;
            }
            case "English": {
                return  2;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public static String parseToString(int language) throws NoSuchFieldException {
        switch (language) {
            case 1: {
                return "Russian";
            }
            case 2: {
                return "English";
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
}
