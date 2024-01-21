package com.example.discuss;

import java.time.LocalDateTime;
import java.util.List;

public class Helpers {

    public static <T> String[] listToArray(List<T> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).toString();
        }
        return array;
    }

    public static String breakStringLines(String s, int fontSize) {
        final int LINE_MAX = 700 / fontSize;
        String[] splittedBySpace = s.split(" ");
        String result = "";
        int curr = 0;
        for (String part : splittedBySpace) {
            if (curr + part.length() > LINE_MAX) {
                curr = part.length();
                result += "\n" + part;
            } else {
                curr += part.length() + 1;
                result += " " + part;
            }
        }
        return result.substring(1);
    }

    public static String truncateString(String s, int fontsize) {
        final int LINE_MAX = 700 / fontsize - 5;
        if (s.length() <= LINE_MAX + 5) {
            return s;
        }
        return s.substring(0, LINE_MAX) + "...";
    }

    public static String formatDate(LocalDateTime date) {
        String raw = date.toString();
        String[] parts = raw.split("T");
        String datePortion = parts[0];
        String timePortion = parts[1].split("\\.")[0];
        return datePortion + " at " + timePortion;
    }

    public static class FormattedString {

        public static enum Mode {
            ORIGINAL, LINE_BROKEN, TRUNCATED
        }

        public String originalString;
        public int fontSize;
        public String truncatedString;
        public String lineBrokenString;
        public Mode toStringMode;

        public FormattedString(String s, int fontsize, Mode mode) {
            originalString = s;
            fontSize = fontsize;
            truncatedString = truncateString(s, fontsize);
            lineBrokenString = breakStringLines(s, fontsize);
            toStringMode = mode;
        }

        @Override
        public String toString() {
            switch (toStringMode) {
                case ORIGINAL:
                    return originalString;
                case LINE_BROKEN:
                    return lineBrokenString;
                case TRUNCATED:
                    return truncatedString;
            }
            throw new RuntimeException();
        }

    }

}
