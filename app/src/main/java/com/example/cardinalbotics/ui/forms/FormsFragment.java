package com.example.cardinalbotics.ui.forms;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Response;
import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormsFragment extends Fragment {

	private FormsViewModel formsViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		formsViewModel = new ViewModelProvider(this).get(FormsViewModel.class);
		return inflater.inflate(R.layout.fragment_forms, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		final TableLayout formList = ((TableLayout) getView().findViewById(R.id.formsList));
		AppSharedResources.getInstance(getActivity().getApplicationContext()).requestDataSheet(new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					JSONArray entries = response.getJSONArray("values");
					for (int entry = 0; entry < entries.length(); entry++) {
						JSONArray data = entries.getJSONArray(entry);
						String type = data.getString(0);
						String name = data.getString(1);
						String url = data.getString(2);
						String status = data.getString(3);
						String dueBy = data.getString(4);

						//Generate the Buttons Here
						appendFormRow(name, dueBy, "");
					}
					//Done. Now confirming everything is there
					System.out.println(entries.toString());
				} catch (Exception err) {
					System.out.println("Oh no, can't load / process things right now");
					err.printStackTrace();
				}
			}
		});
	}

	public void appendFormRow(String buttonText, String sideText, String url) {
//		int color = Integer.parseInt("#A17D1120", 16);
		TableLayout layout = getView().findViewById(R.id.formsList);
		TableRow newRow = new TableRow(getContext());
		Button btn = new Button(getContext());
		TextView text = new TextView(getContext());
		text.setText(sideText);
		text.setGravity(Gravity.CENTER);
		btn.setText(buttonText);
		btn.setBackgroundColor(Color.RED);
		newRow.addView(btn);
		newRow.addView(text);
		layout.addView(newRow);
		layout.addView(new Space(getContext()));
	}
}