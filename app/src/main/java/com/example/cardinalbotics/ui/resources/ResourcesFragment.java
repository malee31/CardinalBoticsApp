package com.example.cardinalbotics.ui.resources;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

public class ResourcesFragment extends Fragment {

    private ResourcesViewModel resourcesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        resourcesViewModel =
                ViewModelProviders.of(this).get(ResourcesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_resources, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final LinearLayout resourceList = ((LinearLayout) getView().findViewById(R.id.resourcesList));
        AppSharedResources.getInstance(getActivity().getApplicationContext()).requestDataSheet(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray entries = response.getJSONObject("feed").getJSONArray("entry");

                    //Formatting 2D ArrayList as ArrayList of []
                    int[] sheetSize = new int[] {0, 0};
                    ArrayList<JSONObject> cells = new ArrayList<>();

                    //Find size for the array and extract each data chunk into ArrayList
                    for(int ent = 0; ent < entries.length(); ent++) {
                        JSONObject data = entries.getJSONObject(ent).getJSONObject("gs$cell");
                        sheetSize[0] = Math.max(sheetSize[0], data.getInt("row"));
                        sheetSize[1] = Math.max(sheetSize[1], data.getInt("col"));
                        cells.add(data);
                    }

                    //Fill Array with entries in their proper positions. Some may be null
                    JSONObject[][] sheetData = new JSONObject[sheetSize[0]][sheetSize[1]];
                    for(int ent = 0; ent < entries.length(); ent++) {
                        JSONObject data = entries.getJSONObject(ent).getJSONObject("gs$cell");
                        sheetData[data.getInt("row") - 1][data.getInt("col") - 1] = data;
                    }

                    //Done. Now confirming everything is there
                    System.out.println("SUCCESS...? Data Size: " + sheetSize[0] + " rows  " + sheetSize[1] + " columns");
                } catch (Exception err) {
                    System.out.println("Oh no, can't load / process things right now");
                    err.printStackTrace();
                }
            }
        });
    }
}
