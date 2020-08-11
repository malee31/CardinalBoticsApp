package com.example.cardinalbotics.ui.forms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cardinalbotics.R;

public class FormsFragment extends Fragment {

    private FormsViewModel formsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        formsViewModel =
                ViewModelProviders.of(this).get(FormsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forms, container, false);
        return root;
    }
}