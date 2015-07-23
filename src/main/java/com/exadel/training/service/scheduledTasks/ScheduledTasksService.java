package com.exadel.training.service.scheduledTasks;

import com.exadel.training.common.StateTraining;
import com.exadel.training.controller.model.Training.NotificationTrainingModel;
import com.exadel.training.controller.model.Training.ShortTrainingInfo;
import com.exadel.training.controller.model.Training.TrainingInfo;
import com.exadel.training.controller.model.User.UserShort;
import com.exadel.training.model.Training;
import com.exadel.training.notification.mail.WrapperNotificationMail;
import com.exadel.training.notification.sms.WrapperNotificationSMS;
import com.exadel.training.service.TrainingService;
import com.twilio.sdk.TwilioRestException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 20.07.2015.
 */

@Service
@EnableScheduling
public class ScheduledTasksService {

    private WrapperNotificationMail wrapperNotificationMail;
    private WrapperNotificationSMS wrapperNotificationSMS;

    @Autowired
    TrainingService trainingService;

    public ScheduledTasksService() {
        this.wrapperNotificationMail = wrapperNotificationMail;
        this.wrapperNotificationSMS = wrapperNotificationSMS;
    }

    private int getHoursBeforeTraining(NotificationTrainingModel notificationTrainingModel) {
        Date date = notificationTrainingModel.getDate();
        int day = date.getDate();
        int year = date.getYear() + 1900;
        int month = date.getMonth()  + 1;
        int hours = date.getHours();
        int minutes = date.getMinutes();
        DateTime d1 = new DateTime(year, month, day, hours, minutes);
        int hoursBeetween = Hours.hoursBetween(DateTime.now(), d1).getHours();
        return  hoursBeetween;
    }

    private boolean shouldBeCanceled(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) {
        if(listeners.size()/notificationTrainingModel.getCapacity() < 0.5)
            return true;
        return false;
    }

    private int emptyPlaces(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) {
        return notificationTrainingModel.getCapacity() - listeners.size();
    }

    private void cancelTraining(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) throws MessagingException, NoSuchFieldException {
        Training training = trainingService.getTrainingByName(notificationTrainingModel.getName());
        training.setState(StateTraining.parseToInt("Canceled"));
            for (UserShort listener : listeners) {
                wrapperNotificationMail.sendMessage(listener.getEmail(), "canceled", "topic");
            }
            UserShort traininer = notificationTrainingModel.getTrainer();
            wrapperNotificationMail.sendMessage(traininer.getEmail(), "canceled", "topic");
    }

    private void notificateByEmail(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) throws MessagingException {
        for (UserShort listener : listeners) {
            wrapperNotificationMail.sendMessage(listener.getEmail(), "canceled", "topic");
        }
        UserShort traininer = notificationTrainingModel.getTrainer();
        wrapperNotificationMail.sendMessage(traininer.getEmail(), "text", "topic");
    }

    private void notificateBySms(NotificationTrainingModel notificationTrainingModel, List<UserShort> listeners) throws TwilioRestException {
        for(UserShort listener: listeners) {
            String phone = listener.getNumberPhone();
            if(phone != null && !phone.isEmpty())
                wrapperNotificationSMS.sendSMS(phone, "text");
        }
        UserShort traininer = notificationTrainingModel.getTrainer();
        String phone = traininer.getNumberPhone();
        if(phone != null && !phone.isEmpty())
            wrapperNotificationSMS.sendSMS(phone, "text");
    }

    private void race(NotificationTrainingModel notificationTrainingModel) throws TwilioRestException {
        Training training = trainingService.getTrainingByName(notificationTrainingModel.getName());
        List<UserShort> spareListeners = UserShort.parseUserShortList(training.getSpareUsers());
        for(UserShort spareListener: spareListeners) {
            String phone = spareListener.getNumberPhone();
            if(phone != "")
                wrapperNotificationSMS.sendSMS(phone, "text");
        }
    }

    private void hasPased(NotificationTrainingModel notificationTrainingModel) throws NoSuchFieldException {
        Training training = trainingService.getTrainingByName(notificationTrainingModel.getName());
        int state = training.getState();
        if (getHoursBeforeTraining(notificationTrainingModel) < 0 &&  (state == StateTraining.parseToInt("InProcess") || state == StateTraining.parseToInt("Ahead"))){
            training.setState(StateTraining.parseToInt("Finished"));
        }
    }

    @Scheduled(fixedDelay = 3600000)
    public void doSomething() throws ParseException, NoSuchFieldException, MessagingException, TwilioRestException {
        List<Training> trainingList = trainingService.getValidTrainings();
        List<NotificationTrainingModel> notificationTrainingModelList = NotificationTrainingModel.parseTrainingList(trainingList);
        for(NotificationTrainingModel notificationTrainingModel: notificationTrainingModelList) {
            int hoursBeetween = getHoursBeforeTraining(notificationTrainingModel);
            List<UserShort> listeners = notificationTrainingModel.getListeners();
            switch (hoursBeetween){
                case 24:
                    if(shouldBeCanceled(notificationTrainingModel, listeners)) {
                        cancelTraining(notificationTrainingModel, listeners);
                    } else {
                        notificateByEmail(notificationTrainingModel, listeners);
                    }
                    continue;
                case 3:
                    if(emptyPlaces(notificationTrainingModel, listeners) > 0)
                        race(notificationTrainingModel);
                case 1:
                    notificateBySms(notificationTrainingModel, listeners);
                    continue;
                default:
                    hasPased(notificationTrainingModel);
                    continue;
            }
        }
    }
}
