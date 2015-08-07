package com.exadel.training.comparator;

import com.exadel.training.model.Training;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by HP on 06.08.2015.
 */
public class ComparatorDateTraining implements Comparator<Training> {
    @Override
    public int compare(Training o1, Training o2) {
        Date date1 = o1.getDateTime();
        Date date2 = o2.getDateTime();

        return date1.compareTo(date2);
    }
}
