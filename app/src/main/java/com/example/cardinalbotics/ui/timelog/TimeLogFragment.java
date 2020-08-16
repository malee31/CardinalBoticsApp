package com.example.cardinalbotics.ui.timelog;

import android.os.Bundle;
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

public class TimeLogFragment extends Fragment {

	private TimeLogViewModel timelogViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		timelogViewModel = new ViewModelProvider(this).get(TimeLogViewModel.class);
		return inflater.inflate(R.layout.fragment_timelog, container, false);
	}

	@Override
	public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

	}
}