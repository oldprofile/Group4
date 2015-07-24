package com.exadel.training.controller.model.Feedback;

import com.exadel.training.model.CoachFeedback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */
public class CoachFeedbackADDModel implements Serializable {

    private boolean howEnounceMaterial;

    private boolean explainHardness;

    private boolean highlightMain;

    private boolean interesting;

    private boolean askingQuestions;

    private boolean explainHowToUseNew;

    private boolean creativity;

    private boolean kindness;

    private boolean patience;

    private boolean erudition;

    private boolean styleOfTeaching;

    private String coachLogin;

    private String feedbackerLogin;

    public CoachFeedbackADDModel() {
    }

    public CoachFeedbackADDModel(boolean howEnounceMaterial, boolean explainHardness, boolean highlightMain, boolean interesting, boolean askingQuestions, boolean explainHowToUseNew, boolean creativity, boolean kindness, boolean patience, boolean erudition, boolean styleOfTeaching, String coachLogin, String feedbackerLogin) {
        this.howEnounceMaterial = howEnounceMaterial;
        this.explainHardness = explainHardness;
        this.highlightMain = highlightMain;
        this.interesting = interesting;
        this.askingQuestions = askingQuestions;
        this.explainHowToUseNew = explainHowToUseNew;
        this.creativity = creativity;
        this.kindness = kindness;
        this.patience = patience;
        this.erudition = erudition;
        this.styleOfTeaching = styleOfTeaching;
        this.coachLogin = coachLogin;
        this.feedbackerLogin = feedbackerLogin;
    }

    public boolean isHowEnounceMaterial() {
        return howEnounceMaterial;
    }

    public void setHowEnounceMaterial(boolean howEnounceMaterial) {
        this.howEnounceMaterial = howEnounceMaterial;
    }

    public boolean isExplainHardness() {
        return explainHardness;
    }

    public void setExplainHardness(boolean explainHardness) {
        this.explainHardness = explainHardness;
    }

    public boolean isHighlightMain() {
        return highlightMain;
    }

    public void setHighlightMain(boolean highlightMain) {
        this.highlightMain = highlightMain;
    }

    public boolean isInteresting() {
        return interesting;
    }

    public void setInteresting(boolean interesting) {
        this.interesting = interesting;
    }

    public boolean isAskingQuestions() {
        return askingQuestions;
    }

    public void setAskingQuestions(boolean askingQuestions) {
        this.askingQuestions = askingQuestions;
    }

    public boolean isExplainHowToUseNew() {
        return explainHowToUseNew;
    }

    public void setExplainHowToUseNew(boolean explainHowToUseNew) {
        this.explainHowToUseNew = explainHowToUseNew;
    }

    public boolean isCreativity() {
        return creativity;
    }

    public void setCreativity(boolean creativity) {
        this.creativity = creativity;
    }

    public boolean isKindness() {
        return kindness;
    }

    public void setKindness(boolean kindness) {
        this.kindness = kindness;
    }

    public boolean isPatience() {
        return patience;
    }

    public void setPatience(boolean patience) {
        this.patience = patience;
    }

    public boolean isErudition() {
        return erudition;
    }

    public void setErudition(boolean erudition) {
        this.erudition = erudition;
    }

    public boolean isStyleOfTeaching() {
        return styleOfTeaching;
    }

    public void setStyleOfTeaching(boolean styleOfTeaching) {
        this.styleOfTeaching = styleOfTeaching;
    }

    public String getCoachLogin() {
        return coachLogin;
    }

    public void setCoachLogin(String coachLogin) {
        this.coachLogin = coachLogin;
    }

    public String getFeedbackerLogin() {
        return feedbackerLogin;
    }

    public void setFeedbackerLogin(String feedbackerLogin) {
        this.feedbackerLogin = feedbackerLogin;
    }

    public static CoachFeedbackADDModel parseCoachFeedback(CoachFeedback coachFeedback) {
        CoachFeedbackADDModel coachFeedbackADDModel = new CoachFeedbackADDModel(coachFeedback.isHowEnounceMaterial(), coachFeedback.isExplainHardness(), coachFeedback.isHighlightMain(),
                coachFeedback.isInteresting(), coachFeedback.isAskingQuestions(), coachFeedback.isExplainHowToUseNew(), coachFeedback.isCreativity(), coachFeedback.isKindness(), coachFeedback.isPatience(),
                coachFeedback.isErudition(), coachFeedback.isStyleOfTeaching(), coachFeedback.getCoach().getName(), coachFeedback.getFeedbacker().getLogin());
        return coachFeedbackADDModel;
    }

    public static List<CoachFeedbackADDModel> parseCoachFeedbackList(List<CoachFeedback> coachFeedbacks) {
        List<CoachFeedbackADDModel> coachFeedbackADDModels = new ArrayList<CoachFeedbackADDModel>();
        for(CoachFeedback coachFeedback: coachFeedbacks) {
            coachFeedbackADDModels.add(CoachFeedbackADDModel.parseCoachFeedback(coachFeedback));
        }
        return coachFeedbackADDModels;
    }
}
