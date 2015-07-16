package com.exadel.training.common;

/**
 * Created by Клим on 15.07.2015.
 */
public enum LanguageTraining {
    russian,
    english;

    public static int parseToInt(String language) throws NoSuchFieldException {
        switch (language) {
            case "russian": {
                return 1;
            }
            case "english": {
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
                return "russian";
            }
            case 2: {
                return "english";
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
}
