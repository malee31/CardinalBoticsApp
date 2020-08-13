package com.example.cardinalbotics.ui.training;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardinalbotics.R;

public class TrainingFragment extends Fragment {

	private TrainingViewModel trainingViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		trainingViewModel = new ViewModelProvider(this).get(TrainingViewModel.class);
		return inflater.inflate(R.layout.fragment_training, container, false);
	}
}