package com.example.cardinalbotics.ui.resources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cardinalbotics.R;

public class ResourcesFragment extends Fragment {

    private ResourcesViewModel resourcesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        resourcesViewModel =
                ViewModelProviders.of(this).get(ResourcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resources, container, false);
        return root;
    }
}
