package com.example.luckybet;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CouponXmlParser {
    public static List<Coupon> parse(File file) throws Exception {
        List<Coupon> coupons = null;
        Coupon coupon = null;
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
                    if (tagName.equalsIgnoreCase("Coupon")) {
                        coupon = new Coupon();
                        if (coupons == null) {
                            coupons = new ArrayList<>();
                        }
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (coupon != null) {
                        if (tagName.equalsIgnoreCase("Userid")) {
                            coupon.setUserid(text);
                        }else if (tagName.equalsIgnoreCase("Home")) {
                            coupon.setHome(text);
                        }else if (tagName.equalsIgnoreCase("Away")) {
                            coupon.setAway(text);
                        } else if (tagName.equalsIgnoreCase("SelectedTeam")) {
                            coupon.setSelectedteam(text);
                        } else if (tagName.equalsIgnoreCase("Multiplier")) {
                            coupon.setMultiplier(text);
                        } else if (tagName.equalsIgnoreCase("Stake")) {
                            coupon.setStake(text);
                        } else if (tagName.equalsIgnoreCase("Coupon")) {
                            coupons.add(coupon);
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        return coupons;
    }

    public static void serializeCoupons(List<Coupon> coupons, File file) {
        StringBuilder xml = new StringBuilder();
        xml.append("<Coupons>" + "\n");
        for (Coupon coupon : coupons) {
            xml.append("<Coupon>" + "\n");
            xml.append("<Userid>");xml.append(coupon.getUserid());xml.append("</Userid>" + "\n");
            xml.append("<Home>");xml.append(coupon.getHome());xml.append("</Home>" + "\n");
            xml.append("<Away>");xml.append(coupon.getAway());xml.append("</Away>" + "\n");
            xml.append("<SelectedTeam>");xml.append(coupon.getSelectedteam());xml.append("</SelectedTeam>" + "\n");
            xml.append("<Multiplier>");xml.append(coupon.getMultiplier());xml.append("</Multiplier>" + "\n");
            xml.append("<Stake>");xml.append(coupon.getStake());xml.append("</Stake>" + "\n");
            xml.append("</Coupon>"+ "\n");
        }
        xml.append("</Coupons>");
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
