package com.example.cardinalbotics.ui.timelog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeLogViewModel extends ViewModel {

	private MutableLiveData<String> mText;

	public TimeLogViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("This is resource fragment");
	}

	public LiveData<String> getText() {
		return mText;
	}
}