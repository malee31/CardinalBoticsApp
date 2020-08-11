package com.example.cardinalbotics;

import android.content.Context;
import android.os.SystemClock;

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

//import org.json.JSONException;
import org.json.JSONObject;

public class AppSharedResources {
    private static AppSharedResources allResources;
    private Context appContext;
    //Requester Variables
    private RequestQueue requestQueue;
    private Cache cache;
    private Network network = new BasicNetwork(new HurlStack());
    private String
            sheetURL = "",
            calendarURL = "";

    //Timer Variables. Start and End Times should be in Seconds
    private static long start = 0, end = -1;
    private static boolean running = false;

    private AppSharedResources(Context appCont) {
        appContext = appCont;
        cache = new DiskBasedCache(appContext.getCacheDir(), 1024 * 1024); // 1MB cap
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public static AppSharedResources getInstance(Context appCont) {
        if(null == allResources) allResources = new AppSharedResources(appCont);
        return allResources;
    }

    public void timerStart() {
        if (running) {
            System.out.println("The Timer is already running!!!");
            return;
        }
        start = SystemClock.elapsedRealtime() / 1000;
        running = true;
    }

    public void timerStop() {
        if (!running) {
            System.out.println("The Timer is not running!!!");
        } else {
            end = SystemClock.elapsedRealtime() / 1000;
            running = false;
        }
    }

    public void timerToggle() {
        if(running) {
            System.out.println("END TIMER TOGGLED");
            timerStop();
            if(timerElapsed() > 0) sendTime(timerElapsed());
        } else {
            System.out.println("START TIMER TOGGLED");
            timerStart();
        }
    }

    public long timerElapsed() {
        return timerElapsed(false);
    }

    public long timerElapsed(boolean useCurrentTime) {
        if(useCurrentTime) {
            if(!running) {
                System.out.println("The timer isn't running! But here's the current time: " + now());
                return -1;
            }
            return now() - start;
        }
        else if(start > end) {
            System.out.println("The timer hasn't been started before");
            return -1;
        } else {
            return end - start;
        }
    }

    private long now() { return SystemClock.elapsedRealtime() / 1000; }

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
    }

    public void sendTime(long duration) {
        System.out.println("Logged in for " + duration + " Seconds");
    }
}
