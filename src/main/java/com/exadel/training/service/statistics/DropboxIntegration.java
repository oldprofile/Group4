package com.exadel.training.service.statistics;

import com.dropbox.core.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Locale;

/**
 * Created by asd on 07.08.2015.
 */
@Service
public class DropboxIntegration {
    private final static String APP_KEY = "dix6ykfcwrl406d";
    private final static String APP_SECRET = "dix6ykfcwrl406d";
    private final static String accessToken = "h5opKpPw0GMAAAAAAAADezEiVXRSW7bFSQAVhFmfWyUFzQIC36ct9i89yv2CCKAO";
    private DbxClient client;

    public DropboxIntegration() {
        DbxRequestConfig config = new DbxRequestConfig(
                "ExadelTrainingSystem", Locale.getDefault().toString());
        client = new DbxClient(config, accessToken);
    }

    public String uploadFile(File uploadingFile, String fileName) throws IOException, DbxException {
        FileInputStream inputStream = new FileInputStream(uploadingFile);
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/" + fileName,
                    DbxWriteMode.add(), uploadingFile.length(), inputStream);
        } finally {
            inputStream.close();
            return client.createShareableUrl("/" + fileName);
        }
    }

    public void downloadFile(String fileName, String filePath) throws IOException, DbxException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        try {
            DbxEntry.File downloadedFile = client.getFile("/" + fileName, null,
                    outputStream);
        } finally {
            outputStream.close();
        }
    }
    }
