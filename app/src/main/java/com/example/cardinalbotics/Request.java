package com.example.cardinalbotics;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Request {
	private static String sheetURL = "", calendarURL = "";

	// Should get a JSON input of all the relevant events to add as a String
	public static void requestCalendar () throws JSONException {
		String test = "{\"a\": 5, \"b\": [99, 200]}";
		JSONObject received = new JSONObject(test);

		System.out.println("JSON PARSED");
		System.out.println(received.getInt("a"));

		System.out.println("JSON ARRAY PARSED");
		System.out.println(received.getJSONArray("b").getInt(0));
	}

	// Should get a JSON input of all the relevant events to add as a String
	public static void requestSheetData() {

	}
}
