package com.exadel.training.common;

/**
 * Created by Клим on 15.07.2015.
 */
public enum LanguageTraining {
    Russian,
    English;

    public static LanguageTraining parseStringToLanguageTraining(String language) throws NoSuchFieldException {
        switch (language) {
            case "Russian": {
                return LanguageTraining.Russian;
            }
            case "English": {
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
                return "Russian";
            }
            case English: {
                return "English";
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
}
