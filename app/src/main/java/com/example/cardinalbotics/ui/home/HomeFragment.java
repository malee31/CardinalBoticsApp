package com.example.cardinalbotics.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        ToggleButton loginButton = ((ToggleButton) getView().findViewById(R.id.loginButton));

        //Toggle Timer on click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppSharedResources.getInstance(getActivity().getApplicationContext()).storeGet("password").equals("No Entry")) {
                    ((ToggleButton) v).setChecked(false);
                    Snackbar.make(view, "You're not logged in! Do that before Signing in", Snackbar.LENGTH_LONG).show();
                    return;
                }
                AppSharedResources.getInstance(getActivity().getApplicationContext()).timerToggle();
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