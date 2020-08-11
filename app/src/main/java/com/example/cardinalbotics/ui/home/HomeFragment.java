package com.example.cardinalbotics.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ToggleButton loginButton = ((ToggleButton) getView().findViewById(R.id.loginButton));

        //Toggle Timer on click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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