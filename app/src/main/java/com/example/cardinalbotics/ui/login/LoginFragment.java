package com.example.cardinalbotics.ui.login;

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

public class LoginFragment extends Fragment {

	public EditText editText;
	TextView textView;
	private LoginViewModel loginViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
		System.out.println("PASSWORD SET TO: " + AppSharedResources.getInstance(getActivity().getApplicationContext()).storeGet("password"));
		return inflater.inflate(R.layout.fragment_login, container, false);
	}

	@Override
	public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
		textView = view.findViewById(R.id.studentId);
		editText = ((EditText) getView().findViewById(R.id.password));
		view.findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText pw = (EditText) (getView().findViewById(R.id.password));
				String password = pw.getText().toString();
				AppSharedResources.getInstance(getActivity().getApplicationContext()).store("password", password);
				System.out.println("PASSWORD SET TO: " + AppSharedResources.getInstance(getActivity().getApplicationContext()).storeGet("password"));
				((TextView) getActivity().findViewById(R.id.studentId)).setText(password);
				((EditText) getActivity().findViewById(R.id.password)).setText("");
			}
		});
	}
}