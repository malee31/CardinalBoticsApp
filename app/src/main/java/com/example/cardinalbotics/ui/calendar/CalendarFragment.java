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
import com.google.api.client.util.DateTime;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
					Calendar calendar = new GregorianCalendar();
					for(int eventData = 0; eventData < loadedEvents.length(); eventData++) {
						JSONObject data = loadedEvents.getJSONObject(eventData);
						//Dates are in 2018-09-14T18:00:00-07:00 format (RFC 3339)
						System.out.println("Data: " + data.toString());
						String mode = data.getJSONObject("start").has("date") ? "date" : "dateTime";
						Date dateStart = new Date(DateTime.parseRfc3339(data.getJSONObject("start").getString(mode)).getValue());
						Date dateEnd = new Date(DateTime.parseRfc3339(data.getJSONObject("end").getString(mode)).getValue());
						calendar.setTime(dateStart);
						eventDay.add(CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)));
						eventName.add(data.getString("summary"));
						System.out.println("Start: " + dateStart.toString() + " End: " + dateEnd.toString() + " Info: " + data.getString("summary"));
					}
					System.out.println("DONE FINDING DATES FROM CALENDAR PAGE");
					decor.updateSet(new HashSet<CalendarDay>(eventDay));
					widget.invalidateDecorators();
					System.out.println("DECORATORS UPDATED");
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