package com.example.cardinalbotics;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Request {
    private static String sheetURL="", calendarURL="";
    public static void requestCalendar() throws JSONException {
        String test = "{\"a\": 5, \"b\": [99, 200]}";
        JSONObject received = new JSONObject(test);

        System.out.println("JSON PARSED");
        System.out.println(received.getInt("a"));

        System.out.println("JSON ARRAY PARSED");
        System.out.println(received.getJSONArray("b").getInt(0));
    }
}
