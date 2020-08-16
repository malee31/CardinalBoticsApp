package com.example.cardinalbotics.ui.timelog;

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
import com.example.cardinalbotics.R;

import org.json.JSONObject;

public class TimeLogFragment extends Fragment {
	TextView textView;
	private TimeLogViewModel timelogViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		timelogViewModel = new ViewModelProvider(this).get(TimeLogViewModel.class);
		return inflater.inflate(R.layout.fragment_timelog, container, false);
	}

	@Override
	public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
		AppSharedResources shared = AppSharedResources.getInstance(getActivity().getApplicationContext());
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
		int hour=5, minute=7;
		textView = view.findViewById(R.id.time);
		textView.setText("Did not Sign in");
		textView.setText(hour+" : "+ minute);

		shared.requestDataCalendar(new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
//					response = new JSONObject("{\"log\": [[\"timeIn\":\"12/9/2020T15:30:30\", \"timeOut\":\"12/9/2020T17:45:00\", \"info\": \"Did stuffs\"],[\"timeIn\":\"12/19/2020T15:30:30\", \"timeOut\":\"12/19/2020T17:45:00\", \"info\": \"Did more stuffs\"],[\"timeIn\":\"12/21/2020T15:30:30\", \"" + "timeOut\":\"12/21/2020T18:00:00\", \"info\": \"Finished stuffs\"]]}");
					int month = 7, day = 10, year = 2020, hour=15, minute=7, second=20;
					String didwhat = "Stuff done alot";
					for(int log = 0; log < 10; log++) {
						textView = view.findViewById(R.id.time);
						textView.setText("Did not Sign in");
						textView.setText(hour+" : "+ minute);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}