package com.example.cardinalbotics.ui.forms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Response;
import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FormsFragment extends Fragment {

    private FormsViewModel formsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        formsViewModel =
                ViewModelProviders.of(this).get(FormsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forms, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final TableLayout formList = ((TableLayout) getView().findViewById(R.id.formsList));
        AppSharedResources.getInstance(getActivity().getApplicationContext()).requestDataSheet(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject[][] sheetData;
                try {
                    JSONArray entries = response.getJSONArray("values");

                    //Done. Now confirming everything is there
                    System.out.println(entries.toString());
                } catch (Exception err) {
                    System.out.println("Oh no, can't load / process things right now");
                    err.printStackTrace();
                }
            }
        });
    }
}