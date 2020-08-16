package com.example.cardinalbotics.ui.training;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class TrainingFragment extends Fragment {

	private TrainingViewModel trainingViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		trainingViewModel = new ViewModelProvider(this).get(TrainingViewModel.class);
		return inflater.inflate(R.layout.fragment_training, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		final TableLayout formList = ((TableLayout) getView().findViewById(R.id.formsList));
		final int[] id = {0};
		AppSharedResources.getInstance(getActivity().getApplicationContext()).requestDataTraining(new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					JSONArray entries = response.getJSONArray("values");
					TableLayout table = getView().findViewById(R.id.trainingTable);
					String currentSection = "";
					for (int entry = 0; entry < entries.length(); entry++) {
						JSONArray data = entries.getJSONArray(entry);
						String type = data.getString(0);
						String name = data.getString(1);
						String url = data.getString(2);

						if (!type.equals("") && !type.trim().toLowerCase().equals(currentSection)) {
							TextView header = makeHeader(type);
							table.addView(header);
							currentSection = type.trim().toLowerCase();
						}

						Button btn = makeButton(name, url);

						TableRow newRow = new TableRow(getContext());
						TableLayout.MarginLayoutParams rowParams = new TableLayout.MarginLayoutParams(TableLayout.MarginLayoutParams.MATCH_PARENT, TableLayout.MarginLayoutParams.WRAP_CONTENT);
						rowParams.topMargin = 10;
						newRow.setLayoutParams(rowParams);

						newRow.addView(btn);
//						if(entry + 1 < entries.length() && entries.getJSONArray(entry + 1).getString(0).trim().toLowerCase().equals(currentSection)) {
//							newRow.addView(makeButton(entries.getJSONArray(entry + 1).getString(1).trim(), entries.getJSONArray(entry + 1).getString(2).trim()));
//							entry++;
//						}
						table.addView(newRow);
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

	public Button makeButton(String name, String url) {
		Button btn = new Button(getContext());
		btn.setText(name);
		btn.setBackgroundColor(Color.parseColor("#A17D1120"));
		btn.setTextColor(Color.parseColor("#000000"));
		btn.setTag(url);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) view.getTag()));
					startActivity(browserIntent);
				} catch (Exception e) {
					Snackbar.make(view, "Whoops, bad URL: " + (String) view.getTag(), Snackbar.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});

		return btn;
	}

	public TextView makeHeader(String headerText) {
		TextView header = new TextView(getContext());
		header.setText(headerText.trim());
		header.setTextColor(getResources().getColor(R.color.colorPrimary));
		header.setTextSize(30);
		header.setGravity(Gravity.CENTER_HORIZONTAL);

		TableRow.MarginLayoutParams headerLayout = new TableRow.MarginLayoutParams(TableRow.MarginLayoutParams.MATCH_PARENT, TableRow.MarginLayoutParams.WRAP_CONTENT);
		headerLayout.bottomMargin = 20;
		header.setLayoutParams(headerLayout);

		return header;
	}
}