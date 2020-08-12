package com.example.cardinalbotics.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        ToggleButton loginButton = ((ToggleButton) getView().findViewById(R.id.loginButton));
        editText = ((EditText) getView().findViewById(R.id.textInputEditText));

        //Toggle Timer on click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSharedResources shared = AppSharedResources.getInstance(getActivity().getApplicationContext());
                if(shared.storeGet("password").equals("No Entry")) {
                    ((ToggleButton) v).setChecked(false);
                    Snackbar.make(v, "You're not logged in! Do that before Signing in", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(((ToggleButton) v).isChecked()) {
                    shared.timerStart();
                } else {
                    if(((TextInputEditText)getActivity().findViewById(R.id.textInputEditText)).getText().length() == 0) {
                        System.out.println("NO TEXT");
                        Snackbar.make(getView(), "Type in what you did today. It can't be blank!", Snackbar.LENGTH_LONG).show();
                        ((ToggleButton) v).setChecked(true);
                        return;
                    }
                    System.out.println("STOP");
                    shared.timerStop();
                    ((EditText)getActivity().findViewById(R.id.textInputEditText)).setText("");
                }
            }
        });
        //Resets state to logged in or logged out
        loginButton.setChecked(AppSharedResources.getInstance(getActivity().getApplicationContext()).timerRunning());
    }


//    @Override
//    public View onResume() {
//
//    }
}