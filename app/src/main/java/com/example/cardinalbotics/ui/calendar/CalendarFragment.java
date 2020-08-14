package com.example.cardinalbotics.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Response;
import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.EventDecorator;
import com.example.cardinalbotics.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class CalendarFragment extends Fragment implements OnDateSelectedListener {

	public ArrayList<CalendarDay> eventDay = new ArrayList<>();
	TextView textView;
	MaterialCalendarView widget;
	ArrayList<String> eventName = new ArrayList<>();
	boolean eventsLoaded = false;
	private CalendarViewModel calendarViewModel;
	EventDecorator decor;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
		return inflater.inflate(R.layout.fragment_calendar, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		eventDay.add(CalendarDay.from(2020, 8, 14));
		eventDay.add(CalendarDay.from(2020, 9, 16));
		eventDay.add(CalendarDay.from(2020, 8, 18));
		eventName.add("Akira's Birthday");
		eventName.add("Everyone's Birthday");
		eventName.add("YEEP");
		decor = new EventDecorator(0, eventDay);

		textView = view.findViewById(R.id.calendartext);
		textView.setText("Loading Events");
		widget = view.findViewById(R.id.calendarView);
		widget.setOnDateChangedListener(this);
		widget.invalidateDecorators();
		widget.addDecorator(decor);

		AppSharedResources.getInstance(getActivity().getApplicationContext()).requestDataCalendar(new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					JSONArray loadedEvents = response.getJSONArray("items");
					for(int eventData = 0; eventData < loadedEvents.length(); eventData++) {
						JSONObject data = loadedEvents.getJSONObject(eventData);
						//Dates are in 2018-09-14T18:00:00-07:00 format (RFC 3339)
						System.out.println("Data: " + data.toString());
						String mode = data.getJSONObject("start").has("date") ? "date" : "dateTime";
						String dateStart = data.getJSONObject("start").getString(mode);
						String dateEnd = data.getJSONObject("end").getString(mode);
						eventName.add(data.getString("summary"));
						System.out.println("Start: " + dateStart + " End: " + dateEnd + " Info: " + data.getString("summary"));
					}
					System.out.println("DONE FINDING DATES FROM CALENDAR");
					eventsLoaded = true;
				} catch(Exception e) {
					System.out.println("Crap, something went wrong while getting the calendar events");
					e.printStackTrace();
				}
				if(response.has("nextPageToken")) System.out.println("OH GOD THERE'S MORE");
			}
		});
	}

	@Override
	public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
		for (int eventEntry = 0; eventEntry < Math.min(eventName.size(), eventDay.size()); eventEntry++) {
			if (eventDay.get(eventEntry).toString().equals(date.toString())) {
				textView.setText(eventName.get(eventEntry));
				return;
			}
		}
		textView.setText(eventsLoaded ? "No Events" : "Loading Events");
	}
}