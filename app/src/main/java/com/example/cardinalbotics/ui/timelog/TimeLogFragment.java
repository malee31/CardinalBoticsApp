package com.example.cardinalbotics.ui.timelog;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;
import com.example.cardinalbotics.ui.login.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;

import static com.example.cardinalbotics.AppSharedResources.running;

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
//		String startTime = AppSharedResources.getInstance(getActivity().getApplicationContext()).storeGet("com.example.cardinalbotics.timestart");
//		long timeTnSeconds =SystemClock.elapsedRealtime()/1000;
//		if(startTime.equals("No Entry")){
//			return;
//		}else if(running==false){
//			return;
//		}else{
//			long longStartTime=Long.parseLong(AppSharedResources.getInstance(getActivity().getApplicationContext()).storeGet("com.example.cardinalbotics.timestart"));
//			long totalTimeIn=Math.subtractExact(timeTnSeconds, longStartTime);
//		}
		int hour=5, minute=7;
		textView = view.findViewById(R.id.time);
		textView.setText("Did not Sign in");
		textView.setText(hour+" : "+ minute);
	}
}