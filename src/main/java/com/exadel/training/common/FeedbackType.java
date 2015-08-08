package com.exadel.training.common;

import com.exadel.training.model.CoachFeedback;
import com.exadel.training.model.TrainingFeedback;
import com.exadel.training.model.UserFeedback;

/**
 * Created by asd on 30.07.2015.
 */
public enum FeedbackType {
    Positive,
    Neutral,
    Negative;

    public static int parseFeedbackTypeToInt(FeedbackType feedbackType) throws NoSuchFieldException{
        switch (feedbackType) {
            case Positive:
                return 1;
            case Neutral:
                return 2;
            case Negative:
                return 3;
            default:
                return 2;
        }
    }

    public static FeedbackType parseIntToFeedbackType(int i) throws NoSuchFieldException {
        switch (i) {
            case 1:
                return Positive;
            case 2:
                return Neutral;
            case 3:
                return Negative;
            default:
                return Neutral;
        }
    }

    public static int getFeedbackType(TrainingFeedback trainingFeedback) {
        int isPositive = 0;
        if(trainingFeedback.isClear()) {
            isPositive++;
        }
        if(trainingFeedback.isInteresting()) {
            isPositive++;
        }
        if(trainingFeedback.isNewMaterial()) {
            isPositive++;
        }
        if(trainingFeedback.isRecommendation()) {
            isPositive++;
        }
        if(isPositive/4 < 0.5) {
            return 3;
        } else if (isPositive/4 == 0.5) {
            return 2;
        } else
            return 1;
    }

    public static int getFeedbackType(UserFeedback userFeedback) {
        int isPositive = 0;
        if(userFeedback.isAttendance()) {
            isPositive++;
        }
        if(userFeedback.isAttitude()) {
            isPositive++;
        }
        if(userFeedback.isCommSkills()) {
            isPositive++;
        }
        if(userFeedback.isFocusOnResult()) {
            isPositive++;
        }
        if(userFeedback.isMotivation()) {
            isPositive++;
        }
        if(userFeedback.isQuestions()) {
            isPositive++;
        }
        if(isPositive/6 < 0.5) {
            return 3;
        } else if (isPositive/6 == 0.5) {
            return 2;
        } else
            return 1;
    }

    public static int getFeedbackType(CoachFeedback coachFeedback) {
        int isPositive = 0;
        if(coachFeedback.isAskingQuestions()) {
            isPositive++;
        }
        if(coachFeedback.isCreativity()) {
            isPositive++;
        }
        if(coachFeedback.isErudition()) {
            isPositive++;
        }
        if(coachFeedback.isExplainHardness()) {
            isPositive++;
        }
        if(coachFeedback.isHighlightMain()) {
            isPositive++;
        }
        if(coachFeedback.isExplainHowToUseNew()) {
            isPositive++;
        }
        if(coachFeedback.isInteresting()) {
            isPositive++;
        }
        if(coachFeedback.isHowEnounceMaterial()) {
            isPositive++;
        }
        if(coachFeedback.isKindness()) {
            isPositive++;
        }
        if(coachFeedback.isPatience()) {
            isPositive++;
        }
        if(coachFeedback.isStyleOfTeaching()) {
            isPositive++;
        }
        if(isPositive/11 < 0.5) {
            return 3;
        } else if (isPositive/11 == 0.5) {
            return 2;
        } else
            return 1;
    }
}
