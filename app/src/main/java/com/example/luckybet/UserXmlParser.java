package com.example.luckybet;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserXmlParser {
    public static List<User> parse(File file) throws Exception {
        List<User> users = null;
        User user = null;
        String text = null;

        FileInputStream inputStream = new FileInputStream(file);
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagName.equalsIgnoreCase("User")) {
                        user = new User();
                        if (users == null) {
                            users = new ArrayList<>();
                        }
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (user != null) {
                        if (tagName.equalsIgnoreCase("Login")) {
                            user.setLogin(text);
                        } else if (tagName.equalsIgnoreCase("Password")) {
                            user.setPassword(text);
                        } else if (tagName.equalsIgnoreCase("Email")) {
                            user.setEmail(text);
                        } else if (tagName.equalsIgnoreCase("Fname")) {
                            user.setFname(text);
                        } else if (tagName.equalsIgnoreCase("Lname")) {
                            user.setLname(text);
                        } else if (tagName.equalsIgnoreCase("Phone")) {
                            user.setPhone(text);
                        } else if (tagName.equalsIgnoreCase("Balance")) {
                            user.setBalance(text);
                        } else if (tagName.equalsIgnoreCase("User")) {
                            users.add(user);
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        return users;
    }

    public static void serializeUsers(List<User> users, File file) {
        StringBuilder xml = new StringBuilder();
        xml.append("<Users>" + "\n");
        for (User user : users) {
            xml.append("<User>" + "\n");
            xml.append("<Login>");xml.append(user.getLogin());xml.append("</Login>" + "\n");
            xml.append("<Password>");xml.append(user.getPassword());xml.append("</Password>" + "\n");
            xml.append("<Email>");xml.append(user.getEmail());xml.append("</Email>" + "\n");
            xml.append("<Fname>");xml.append(user.getFname());xml.append("</Fname>" + "\n");
            xml.append("<Lname>");xml.append(user.getLname());xml.append("</Lname>" + "\n");
            xml.append("<Phone>");xml.append(user.getPhone());xml.append("</Phone>" + "\n");
            xml.append("<Balance>");xml.append(user.getBalance());xml.append("</Balance>" + "\n");
            xml.append("</User>"+ "\n");
        }
        xml.append("</Users>");
        String serializedData = xml.toString();

        try {
            Log.d("STATE", file.getPath());
            saveToFile(serializedData, file);
            // File saved successfully
        } catch (IOException e) {
            Log.d("STATE", e.toString());
            // Error occurred while saving the file
        }
    }

    public static void saveToFile(String data, File file) throws IOException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}
