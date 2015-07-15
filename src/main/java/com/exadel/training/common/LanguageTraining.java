package com.exadel.training.common;

/**
 * Created by Клим on 15.07.2015.
 */
public enum LanguageTraining {
    Russian,
    English;

    public static LanguageTraining parseStringToLanguageTraining(String language) throws NoSuchFieldException {
        switch (language) {
            case "1": {
                return LanguageTraining.Russian;
            }
            case "2": {
                return  LanguageTraining.English;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public static String parseLanguageTrainingToString(LanguageTraining languageTraining) throws NoSuchFieldException {
        switch (languageTraining) {
            case Russian: {
                return "1";
            }
            case English: {
                return "2";
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public static int parseStringLanguageTrainingToInt(String languageTraining) throws NoSuchFieldException {
        switch (languageTraining) {
            case "Russian": {
                return 1;
            }
            case "English": {
                return 2;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
}
