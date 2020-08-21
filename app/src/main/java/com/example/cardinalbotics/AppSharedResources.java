package com.example.cardinalbotics;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//import org.json.JSONException;

public class AppSharedResources {
	private static AppSharedResources allResources;
	//Timer Variables. Start and End Times should be in Seconds
	private static long start = 0, end = -1;
	public static boolean running = false;
	SharedPreferences sharedPrefs;
	SharedPreferences.Editor prefsEditor;
	private Context appContext;
	//Requester Variables
	private RequestQueue requestQueue;
	private Cache cache;
	private Network network = new BasicNetwork(new HurlStack());
	private String
			sheetURL = "https://sheets.googleapis.com/v4/spreadsheets/1po_TE36FA-I7J2Y-Biw5snWdfSm_Cx055KVi1c43G7Y/values/App%20Assembly!A1:E?key=AIzaSyB2ynMpXWG49Fk-rS0cBZdytUH9GK96NzU",
			calendarURL = "https://www.googleapis.com/calendar/v3/calendars/nicholas.do%40team4159.org/events?key=AIzaSyDRSH4Trb-AdjEdzA06J7WOFLbyhOqnq-M&timeZone=UTC&timeMin=2020-08-16T00:00:00Z&orderBy=startTime&singleEvents=True",
			trainingURL = "https://sheets.googleapis.com/v4/spreadsheets/1fQyQXJPiFwXKT7wJ8wmJgTolLwxxiOe2PE3xpNQR9Wc/values/Training%20Links!A2:C?key=AIzaSyB2ynMpXWG49Fk-rS0cBZdytUH9GK96NzU",
			resourceURL = "https://sheets.googleapis.com/v4/spreadsheets/1fQyQXJPiFwXKT7wJ8wmJgTolLwxxiOe2PE3xpNQR9Wc/values/Resources!A2:C?key=AIzaSyB2ynMpXWG49Fk-rS0cBZdytUH9GK96NzU";

	private AppSharedResources(Context appCont) {
		appContext = appCont;
		cache = new DiskBasedCache(appContext.getCacheDir(), 1024 * 1024); // 1MB cap
		requestQueue = new RequestQueue(cache, network);
		requestQueue.start();
		sharedPrefs = appContext.getSharedPreferences("com.example.cardinalbotics.sharedprefs", Context.MODE_PRIVATE);
		prefsEditor = sharedPrefs.edit();
	}

	public static AppSharedResources getInstance(Context appCont) {
		if (null == allResources) allResources = new AppSharedResources(appCont);
		return allResources;
	}

	public void timerStart() {
		if (running) {
			System.out.println("The Timer is already running!!!");
			return;
		}
		start = SystemClock.elapsedRealtime() / 1000;
		prefsEditor.putLong("com.example.cardinalbotics.timerStart", start);
		prefsEditor.commit();

		toggleSignIn();

		running = true;
	}

	public void timerStop() {
		if (!running) {
			System.out.println("The Timer is not running!!!");
		} else {
			end = SystemClock.elapsedRealtime() / 1000;
			prefsEditor.putLong("com.example.cardinalbotics.timerStop", end);
			prefsEditor.commit();
			toggleSignIn();
			running = false;
		}
	}

	public void timerToggle() {
		if (running) {
			System.out.println("END TIMER TOGGLED");
			timerStop();
//			if (timerElapsed() > 0) sendTime(timerElapsed());
		} else {
			System.out.println("START TIMER TOGGLED");
			timerStart();
		}
	}

	public long timerElapsed() {
		return timerElapsed(false);
	}

	public long timerElapsed(boolean useCurrentTime) {
		if (useCurrentTime) {
			if (!running) {
				System.out.println("The timer isn't running! But here's the current time: " + now());
				return -1;
			}
			return now() - start;
		} else if (start > end) {
			System.out.println("The timer hasn't been started before");
			return -1;
		} else {
			return end - start;
		}
	}

	public void toggleSignIn() {
		StringRequest stringRequest = new StringRequest(Request.Method.GET,
			"http://18.221.165.138/src/endpoints/signin.php?password=" + Uri.encode(storeGet("password")),
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					System.out.println("Seemed to work! FIRST TRY! -> " + response.toString());
				}
			}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				System.out.println("That didn't work! -> " + error.toString());
			}
		});

		requestQueue.add(stringRequest);
	}

	public void accountExistify() {
		StringRequest stringRequest = new StringRequest(Request.Method.GET,
				"http://18.221.165.138/src/endpoints/adduser.php?username=app&password=" + Uri.encode(storeGet("password")),
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						System.out.println("Seemed to work! FIRST TRY! -> " + response.toString());
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				System.out.println("That didn't work! -> " + error.toString());
			}
		});

		requestQueue.add(stringRequest);
	}

	private long now() {
		return SystemClock.elapsedRealtime() / 1000;
	}

	public boolean timerRunning() {
		return running;
	}

	public void requestDataCalendar(Response.Listener<JSONObject> onFinish) {
		requestData(calendarURL, onFinish);
	}

	public void requestDataSheet(Response.Listener<JSONObject> onFinish) {
		requestData(sheetURL, onFinish);
	}

	public void requestDataTraining(Response.Listener<JSONObject> onFinish) {
		requestData(trainingURL, onFinish);
	}

	public void requestDataResource(Response.Listener<JSONObject> onFinish) {
		requestData(resourceURL, onFinish);
	}

	// Should get a JSON input of all the relevant events to add as a String
	private void requestData(String useURL, Response.Listener<JSONObject> onFinish) {
		System.out.println("JSON Request being constructed");

		JsonObjectRequest jsonRequester = new JsonObjectRequest(
				Request.Method.GET,
				useURL,
				null,
				onFinish,
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

	public void fetchUserData(Response.Listener<JSONArray> onFinish) {
		JsonArrayRequest jsonRequester = new JsonArrayRequest(
				Request.Method.GET,
				"http://18.221.165.138/src/endpoints/getdata.php?password=" + Uri.encode(storeGet("password")),
				null,
				onFinish,
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
					System.out.println("This shouldn't have happened... Bad programmers");
					error.printStackTrace();
					}
				}
		);
		requestQueue.add(jsonRequester);
	}

//	public void sendTime(long duration) {
//		System.out.println("Logged in for " + duration + " Seconds");
//		try {
//			JSONObject data = new JSONObject();
//			data.put("id", storeGet("password"));
//			data.put("duration", duration);
//			data.put("message", "I mean, it sent something... THIS IS A WIP DW");
//			data.put("timeout", new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
//
//			System.out.println("Successfully Created JSON Object: " + data.toString());
//
//			URL url = new URL("https://google.com");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type", "application/json");
////			conn.setRequestProperty("Accept","application/json");
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			conn.connect();
//
//			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//			os.writeBytes(data.toString());
//
//			os.flush();
//			os.close();
//
//			Log.i("STATUS", String.valueOf(conn.getResponseCode()));
//			Log.i("MSG", conn.getResponseMessage());
//		} catch (Exception e) {
//			System.out.println("How did it fail??? POST Time Failed");
//			e.printStackTrace();
//		}
//	}

	public void store(String key, String value) {
		prefsEditor.putString(key, value);
		prefsEditor.commit();
	}

	public String storeGet(String key) {
		return sharedPrefs.getString(key, "No Entry");
	}
}
