package com.exadel.training.common;

/**
 * Created by asd on 15.07.2015.
 */
public enum  UserEnglishLevel {
    PreIntermediate,
    Intermediate,
    UpperIntermediate,
    Advanced;

public static int parseUserEnglishLevelToInt(UserEnglishLevel userEnglishLevel) throws NoSuchFieldException{
    switch (userEnglishLevel) {
        case PreIntermediate:
            return 1;
        case Intermediate:
            return 2;
        case UpperIntermediate:
            return 3;
        case Advanced:
            return 4;
        default:
            throw new NoSuchFieldException("can't find such type");
    }
}

public static UserEnglishLevel parseIntToUserEnglishLevel(int i) throws NoSuchFieldException {
    switch (i) {
        case 1:
            return PreIntermediate;
        case 2:
            return Intermediate;
        case 3:
            return UpperIntermediate;
        case 4:
            return Advanced;
        default:
            throw new NoSuchFieldException("can't find such type");
    }
}
}
