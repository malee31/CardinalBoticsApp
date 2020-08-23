package com.example.cardinalbotics.ui.timelog;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.LayoutDirection;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Response;
import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeLogFragment extends Fragment {

	private TimeLogViewModel timelogViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		timelogViewModel = new ViewModelProvider(this).get(TimeLogViewModel.class);
		return inflater.inflate(R.layout.fragment_timelog, container, false);
	}

	@Override
	public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
		final AppSharedResources shared = AppSharedResources.getInstance(getActivity().getApplicationContext());
//		String startTime = shared.storeGet("com.example.cardinalbotics.timestart");
//		long timeTnSeconds = SystemClock.elapsedRealtime()/1000;
//		if(startTime.equals("No Entry")) {
//			return;
//		} else if(shared.running == false) {
//			return;
//		} else {
//			long longStartTime = Long.parseLong(AppSharedResources.getInstance(getActivity().getApplicationContext()).storeGet("com.example.cardinalbotics.timestart"));
//			long totalTimeIn = Math.subtractExact(timeTnSeconds, longStartTime);
//		}
//		int hour=5, minute=7;
//		textView = view.findViewById(R.id.time);
//		textView.setText("Did not Sign in");
//		textView.setText(hour+" : "+ minute);

		shared.fetchUserData(new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
//					response = new JSONObject("{\"log\": [[\"timeIn\":\"12/9/2020T15:30:30\", \"timeOut\":\"12/9/2020T17:45:00\", \"info\": \"Did stuffs\"],[\"timeIn\":\"12/19/2020T15:30:30\", \"timeOut\":\"12/19/2020T17:45:00\", \"info\": \"Did more stuffs\"],[\"timeIn\":\"12/21/2020T15:30:30\", \"" + "timeOut\":\"12/21/2020T18:00:00\", \"info\": \"Finished stuffs\"]]}");
					System.out.println("Res: " + response.toString());
					Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
					JSONArray entries = response.getJSONArray("sessions");
//					String name = response.getString("username");
					String password = response.getString("password");
					boolean signedIn = response.getBoolean("signedIn");

					TextView textView = getView().findViewById(R.id.timeSignedIn);
					textView.setText(password + " is Signed " + (signedIn ? "In" : "Out"));

					for (int log = 0; log < entries.length(); log++) {
						JSONObject entry = entries.getJSONObject(log);
						long timeOut = entry.getLong("date");
						long timeClocked = entry.getLong("time");
						String did = entry.getString("did");
						long timeIn = timeOut - timeClocked;

						calendar.setTimeInMillis(timeIn * 1000);

						int year = calendar.get(Calendar.YEAR);
						int month = calendar.get(Calendar.MONTH) + 1;
						int day = calendar.get(Calendar.DAY_OF_MONTH);
						int hour = calendar.get(Calendar.HOUR_OF_DAY);
						int minute = calendar.get(Calendar.MINUTE);

						inEachRow(month, day, year, hour, minute, timeClocked, did);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void inEachRow(int month, int day, int year, int hour, int minute, long timeIn, String did) {
		TableLayout layout = getView().findViewById(R.id.timesList);
		TableRow newRow = new TableRow(getContext());
//		TableLayout.LayoutParams rowLayout = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
////		rowLayout.setMargins(0, 20, 0, 30);
//		newRow.setLayoutParams(rowLayout);
		String text = "Logged In " + month + "/" + day + "/" + year + " at " + hour % 12 + " : " + minute + (hour / 12 >= 1 ? "PM" : "AM") + " for ";

		// Format time as hour : minute : second
		text += timeIn / 3600 + " : ";
		timeIn %= 3600;

		text += timeIn / 60 + " : ";
		timeIn %= 60;

		text += timeIn + "\nTasks: " + did;

		TextView timeLog = new TextView(getContext());
		timeLog.setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_bold));
		timeLog.setBackgroundColor(Color.parseColor("#FFFFFF"));
		timeLog.setTextColor(Color.parseColor("#7D1120"));
		timeLog.setTextSize(20);
		timeLog.setSingleLine(false);
		timeLog.setText(text);

		TableRow.LayoutParams textLayout = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
		textLayout.setMargins(20, 10, 20, 10);
		timeLog.setGravity(Gravity.START);
		timeLog.setLayoutParams(textLayout);

//		TextView text2 = new TextView(getContext());
//		text2.setText(hour + " : " + minute);
//		text2.setTextSize(20);
//		text2.setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_bold));
//		text2.setTextColor(Color.parseColor("#7D1120"));
//		text2.setGravity(Gravity.CENTER_HORIZONTAL);
//		setMargins(text2, 50, 0, 0, 30);
//		text2.setBackgroundColor(Color.RED);


//		TextView text3 = new TextView(getContext());
//		text3.setText(didwhat);
//		text3.setTextSize(20);
//		text3.setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_bold));
//		text3.setTextColor(Color.parseColor("#7D1120"));
//		text3.setGravity(Gravity.CENTER_HORIZONTAL);
//		text3.setBackgroundColor(Color.BLACK);

//		newRow.addView(text2);
//		newRow.addView(text3);
		newRow.addView(timeLog);
		layout.addView(newRow);
	}

	public void setMargins(View view, int left, int top, int right, int bottom) {
		if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
			p.setMargins(left, top, right, bottom);
			view.requestLayout();
		}
	}
}