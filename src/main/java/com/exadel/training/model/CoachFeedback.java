package com.exadel.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by asd on 23.07.2015.
 */
@Entity
@Table(name = "cfeedbacks")
public class CoachFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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

    private String other;

    @ManyToOne(cascade = CascadeType.ALL)
    private User feedbacker;

    @NotNull
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User coach;

    private int type;

    public CoachFeedback() {
    }

    public CoachFeedback(boolean howEnounceMaterial, boolean explainHardness, boolean highlightMain, boolean interesting, boolean askingQuestions, boolean explainHowToUseNew, boolean creativity, boolean kindness, boolean patience, boolean erudition, boolean styleOfTeaching, String other, User feedbacker, User coach) {
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
        this.feedbacker = feedbacker;
        this.coach = coach;
        this.other = other;
        this.date = new Date();
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public User getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(User feedbacker) {
        this.feedbacker = feedbacker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
