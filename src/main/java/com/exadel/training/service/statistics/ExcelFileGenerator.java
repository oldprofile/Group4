package com.exadel.training.service.statistics;

import com.dropbox.core.DbxException;
import com.exadel.training.controller.model.Feedback.UserFeedbackGETModel;
import com.exadel.training.controller.model.Omission.JournalOmissionModel;
import com.exadel.training.controller.model.Omission.StatisticsRequestModel;
import com.exadel.training.controller.model.Training.TrainingName;
import com.exadel.training.controller.model.User.UserLoginAndName;
import com.exadel.training.model.Omission;
import com.exadel.training.model.Training;
import com.exadel.training.model.User;
import com.exadel.training.model.UserFeedback;
import com.exadel.training.service.OmissionService;
import com.exadel.training.service.TrainingService;
import com.exadel.training.service.UserFeedbackService;
import com.exadel.training.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by asd on 23.07.2015.
 */

@Service
public class ExcelFileGenerator {

    @Autowired
    TrainingService trainingService;

    @Autowired
    UserService userService;

    @Autowired
    OmissionService omissionService;

    @Autowired
    UserFeedbackService userFeedbackService;

    @Autowired
    DropboxIntegration dropboxIntegration;

    private static final int DEFAULT_COLUMN_WIDTH = 20;
    private static final int NAMES_COLUMN_WIDTH = 23*256;
    private static final int TITLE_HEIGHT = 12;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TITLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yy");

    public ExcelFileGenerator() {
    }

    private String createFilePath(String fileName) throws IOException {
        String destination = System.getProperty("user.dir") + "\\src\\main\\webapp\\statistics\\" + fileName;
        if(!SystemUtils.IS_OS_WINDOWS)
            destination = destination.replace("\\", "/");
        return destination;
    }

    public String returnFilePath(String fileName) {
        String destination = "statistics\\" + fileName;
        if(!SystemUtils.IS_OS_WINDOWS)
            destination = destination.replace("\\", "/");
        return destination;
    }

    public XSSFWorkbook generateTrainingsList(Date dateFrom, Date dateTo, String userLogin) {
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByName(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(userLogin + " trainings statistics");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();
        XSSFCellStyle dataStyle = workbook.createCellStyle();

        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(IndexedColors.ROYAL_BLUE.getIndex());
        titleFont.setBold(true);
        titleFont.setFontHeight(TITLE_HEIGHT);
        titleStyle.setFont(titleFont);

        XSSFFont headersFont = workbook.createFont();
        headersFont.setColor(IndexedColors.WHITE.getIndex());
        headersFont.setBold(true);
        headersStyle.setFont(headersFont);
        headersStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headersStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headersStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        headersStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());

        XSSFFont dataFont = workbook.createFont();
        dataFont.setBold(true);
        dataStyle.setFont(dataFont);

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);

        title.createCell(0).setCellValue(userLogin + "'s trainings statistics by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.createCell(0).setCellValue("Trainings");
        headers.getCell(0).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 3;
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(trainingNameAndDates.get(trainingsIndex).getTrainingName());
        }

        return workbook;
    }

    public XSSFWorkbook generateUserList(Date dateFrom, Date dateTo, String trainingName) {
        List<User> listeners = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(listeners);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(trainingName + " users statistics");

        XSSFCellStyle titleStyle = workbook.createCellStyle();
        XSSFCellStyle headersStyle = workbook.createCellStyle();
        XSSFCellStyle dataStyle = workbook.createCellStyle();

        XSSFFont titleFont = workbook.createFont();
        titleFont.setColor(IndexedColors.ROYAL_BLUE.getIndex());
        titleFont.setBold(true);
        titleFont.setFontHeight(TITLE_HEIGHT);
        titleStyle.setFont(titleFont);

        XSSFFont headersFont = workbook.createFont();
        headersFont.setColor(IndexedColors.WHITE.getIndex());
        headersFont.setBold(true);
        headersStyle.setFont(headersFont);
        headersStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        headersStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headersStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        headersStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());

        XSSFFont dataFont = workbook.createFont();
        dataFont.setBold(true);
        dataStyle.setFont(dataFont);

        sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
        sheet.setColumnWidth(0, NAMES_COLUMN_WIDTH);

        XSSFRow title = sheet.createRow(0);
        XSSFRow headers = sheet.createRow(2);

        title.createCell(0).setCellValue(trainingName + " training's users statistics by period: " + TITLE_DATE_FORMAT.format(dateFrom) + " - " + TITLE_DATE_FORMAT.format(dateTo));
        title.getCell(0).setCellStyle(titleStyle);
        headers.createCell(0).setCellValue("Listeners");
        headers.getCell(0).setCellStyle(headersStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, title.getFirstCellNum(), title.getLastCellNum()));

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 3;
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(userLoginAndNames.get(usersIndex).getName());
        }

        return workbook;
    }

    public XSSFWorkbook generateForTrainingDates(Date dateFrom, Date dateTo, String trainingName) {

        List<User> listeners = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(listeners);

        XSSFWorkbook workbook = generateUserList(dateFrom, dateTo, trainingName);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.getRow(2).createCell(1).setCellValue("Dates");
        sheet.getRow(2).getCell(1).setCellStyle(sheet.getRow(2).getCell(0).getCellStyle());

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName,userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            XSSFRow row = sheet.getRow(rowIndex);
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue( journalOmissionModels.get(omissionsIndex).getDate() + " " + journalOmissionModels.get(omissionsIndex).getReason());
                }
            }
        }
        return workbook;
    }

    public XSSFWorkbook generateForUserDates(Date dateFrom, Date dateTo, String userLogin) {
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByName(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        XSSFWorkbook workbook = generateTrainingsList(dateFrom, dateTo, userLogin);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.getRow(2).createCell(1).setCellValue("Dates");
        sheet.getRow(2).getCell(1).setCellStyle(sheet.getRow(2).getCell(0).getCellStyle());

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(), userLogin, dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            XSSFRow row = sheet.getRow(rowIndex);
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                int columnIndex = omissionsIndex + 1;
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    row.createCell(columnIndex).setCellValue(journalOmissionModels.get(omissionsIndex).getDate() + " " + journalOmissionModels.get(omissionsIndex).getReason());
                }
            }
        }
        return workbook;
    }

    public XSSFWorkbook generateUserDatesAndFeedbacks (Date dateFrom, Date dateTo, String userLogin) throws NoSuchFieldException {
        XSSFWorkbook workbook = generateForUserDates(dateFrom, dateTo, userLogin);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<UserFeedback> userFeedbacks = userFeedbackService.getUserFeedbacksOrderByDate(userService.findUserByLogin(userLogin));
        List<UserFeedbackGETModel> userFeedbackGETModels = UserFeedbackGETModel.parseUserFeedbacks(userFeedbacks);

        for(int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex ++) {
            Training training = trainingService.getTrainingByName(sheet.getRow(rowIndex).getCell(0).getStringCellValue());
            String feedbackerLogin = training.getCoach().getLogin();
            StringBuilder stringBuilder = new StringBuilder();
            for (UserFeedbackGETModel userFeedbackGETModel : userFeedbackGETModels) {
                if(feedbackerLogin.equals(userFeedbackGETModel.getFeedbackerLogin())) {
                    stringBuilder.append(userFeedbackGETModel.toString() + "\n");
                }
            }
            if(stringBuilder.length() != 0) {
                Drawing drawing = sheet.createDrawingPatriarch();
                ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
                anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);
                Comment comment = drawing.createCellComment(anchor);
                RichTextString textString = workbook.getCreationHelper().createRichTextString(stringBuilder.toString());
                comment.setString(textString);
                sheet.getRow(rowIndex).getCell(0).setCellComment(comment);
            }
        }

        return workbook;
    }

    public XSSFWorkbook generateForTrainingAmount(Date dateFrom, Date dateTo, String trainingName) {
        List<User> listeners = trainingService.getListenersByTrainingNameSortByName(trainingName);
        List<UserLoginAndName> userLoginAndNames = UserLoginAndName.parseUserLoginAndName(listeners);
        List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingName, dateFrom, dateTo);
        int amountOfTrainings = dates.size();

        XSSFWorkbook workbook = generateUserList(dateFrom, dateTo, trainingName);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.getRow(2).createCell(1).setCellValue("Amount");
        sheet.getRow(2).getCell(1).setCellStyle(sheet.getRow(2).getCell(0).getCellStyle());

        for(int usersIndex = 0; usersIndex < userLoginAndNames.size(); usersIndex++) {
            int rowIndex = usersIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingName, userLoginAndNames.get(usersIndex).getLogin(), dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);

            XSSFRow row = sheet.getRow(rowIndex);
            StringBuilder stringBuilder = new StringBuilder();
            int omissionAmount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    omissionAmount++;
                    stringBuilder.append(journalOmissionModels.get(omissionsIndex).getDate() + "-" + journalOmissionModels.get(omissionsIndex).getReason() + "\n");
                }
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionAmount).toString() + "/" + Integer.valueOf(amountOfTrainings).toString());
            if (stringBuilder.length() != 0) {
                Drawing drawing = sheet.createDrawingPatriarch();
                ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
                anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);
                Comment comment = drawing.createCellComment(anchor);
                RichTextString textString = workbook.getCreationHelper().createRichTextString(stringBuilder.toString());
                comment.setString(textString);
                row.getCell(1).setCellComment(comment);
            }
        }
        return workbook;
    }

    public XSSFWorkbook generateForUserAmount(Date dateFrom, Date dateTo, String userLogin){
        List<Training> trainings = userService.selectAllTrainingBetweenDatesAndSortedByName(userLogin, dateFrom, dateTo);
        List<TrainingName> trainingNameAndDates = TrainingName.parseTrainingList(trainings);

        XSSFWorkbook workbook = generateTrainingsList(dateFrom, dateTo, userLogin);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.getRow(2).createCell(1).setCellValue("Amount");
        sheet.getRow(2).getCell(1).setCellStyle(sheet.getRow(2).getCell(0).getCellStyle());

        for(int trainingsIndex = 0; trainingsIndex < trainingNameAndDates.size(); trainingsIndex++) {
            int rowIndex = trainingsIndex + 3;
            List<Omission> omissions = omissionService.getOmisssionsByTrainingAndUser(trainingNameAndDates.get(trainingsIndex).getTrainingName(), userLogin, dateFrom, dateTo);
            List<JournalOmissionModel> journalOmissionModels = JournalOmissionModel.parseListOfOmissions(omissions);
            List<Date> dates = trainingService.getDatesByTrainingNameBetweenDates(trainingNameAndDates.get(trainingsIndex).getTrainingName(), dateFrom, dateTo);
            int amountOfTrainings = dates.size();
            XSSFRow row = sheet.getRow(rowIndex);
            StringBuilder stringBuilder = new StringBuilder();
            int omissionAmount = 0;
            for(int omissionsIndex = 0; omissionsIndex < journalOmissionModels.size(); omissionsIndex++) {
                if(journalOmissionModels.get(omissionsIndex).getIsOmission()) {
                    omissionAmount++;
                    stringBuilder.append(journalOmissionModels.get(omissionsIndex).getDate() + "-" + journalOmissionModels.get(omissionsIndex).getReason() + "\n");
                }
            }
            row.createCell(1).setCellValue(Integer.valueOf(omissionAmount).toString() + "/" + Integer.valueOf(amountOfTrainings).toString());
            if (stringBuilder.length() != 0) {
                Drawing drawing = sheet.createDrawingPatriarch();
                ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
                anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);
                Comment comment = drawing.createCellComment(anchor);
                RichTextString textString = workbook.getCreationHelper().createRichTextString(stringBuilder.toString());
                comment.setString(textString);
                row.getCell(1).setCellComment(comment);
            }
        }
        return workbook;
    }

    public String generateFile(XSSFWorkbook workbook, String fileName) throws IOException, DbxException {

        String filePath = createFilePath(fileName);
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        String sharebleURL = dropboxIntegration.uploadFile(new File(filePath), fileName);
        return sharebleURL;
    }
}
