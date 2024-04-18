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

public class GameXmlParser {
    public static List<Game> parse(File file) throws Exception {
        List<Game> games = null;
        Game game = null;
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
                    if (tagName.equalsIgnoreCase("Game")) {
                        game = new Game();
                        if (games == null) {
                            games = new ArrayList<>();
                        }
                    }
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (game != null) {
                        if (tagName.equalsIgnoreCase("Home")) {
                            game.setHome(text);
                        } else if (tagName.equalsIgnoreCase("Away")) {
                            game.setAway(text);
                        } else if (tagName.equalsIgnoreCase("WinHome")) {
                            game.setWinHome(text);
                        } else if (tagName.equalsIgnoreCase("Tie")) {
                            game.setTie(text);
                        } else if (tagName.equalsIgnoreCase("WinAway")) {
                            game.setWinAway(text);
                        } else if (tagName.equalsIgnoreCase("Game")) {
                            games.add(game);
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        return games;
    }
}
