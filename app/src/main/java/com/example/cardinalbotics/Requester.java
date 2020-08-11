package com.example.cardinalbotics;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Requester {
    private static Requester req;
    private static RequestQueue requestQueue;
    private static Cache cache;
    private static Network network = new BasicNetwork(new HurlStack());

    private static String
            sheetURL = "",
            calendarURL = "";

    private Requester(Context cont) {
        cache = new DiskBasedCache(cont.getCacheDir(), 1024 * 1024); // 1MB cap
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public static Requester getInstance(Context cont) {
        if (null == req) {
            req = new Requester(cont);
        }
        return req;
    }

    // Should get a JSON input of all the relevant events to add as a String
    public void requestCalendar() throws JSONException {


//		String test = "{\"a\": 5, \"b\": [99, 200]}";
//		JSONObject received = new JSONObject(test);
//
//		System.out.println("JSON PARSED");
//		System.out.println(received.getInt("a"));
//
//		System.out.println("JSON ARRAY PARSED");
//		System.out.println(received.getJSONArray("b").getInt(0));
    }

    // Should get a JSON input of all the relevant events to add as a String
    public void requestData(String type) {
        String useURL;
        switch (type) {
            case "calendar":
                useURL = calendarURL;
                break;
            case "sheet":
                useURL = sheetURL;
                break;
            default:
                System.out.println("INVALID URL");
                return;
        }

        System.out.println("JSON Request being constructed");

        JsonObjectRequest jsonRequester = new JsonObjectRequest(
                Request.Method.GET,
                useURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("SUCCESS...?");
                        System.out.println(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("This shouldn't have happened... Bad programmers");
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonRequester);
        System.out.println("Request Sent. Waiting on Response");
//			URL url = new URL(sheetURL);
//			URLConnection connection = url.openConnection();
//			connection.setDoOutput(true);
//			Scanner scanner = new Scanner(url.openStream());
//			res = scanner.useDelimiter("\\Z").next();

//		return res;
    }

    public static void send(long duration) {
        System.out.println("Logged in for " + duration + " Seconds");
    }
}