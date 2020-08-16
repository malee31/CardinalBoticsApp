package com.example.cardinalbotics.ui.training;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Response;
import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

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
						TextView header = null;
						Button btn2 = null;

						if (!type.equals("") && !type.trim().toLowerCase().equals(currentSection)) {
							header = makeHeader(type);
							table.addView(header);
							currentSection = type.trim().toLowerCase();
						}

						Button btn = makeButton(name, url);

						TableRow newRow = new TableRow(getContext());
//						TableLayout.MarginLayoutParams rowParams = new TableLayout.MarginLayoutParams(TableLayout.MarginLayoutParams.MATCH_PARENT, TableLayout.MarginLayoutParams.WRAP_CONTENT);
//						newRow.setLayoutParams(rowParams);

						newRow.addView(btn);
						if(entry + 1 < entries.length() && entries.getJSONArray(entry + 1).getString(0).trim().toLowerCase().equals("")) {
							entry++;
							btn2 = makeButton(entries.getJSONArray(entry).getString(1).trim(), entries.getJSONArray(entry).getString(2).trim());
							newRow.addView(btn2);
						}
						table.addView(newRow);

						if(null != header) setMargins(header, 0, dip(20f), 0, 0);
						setMargins(btn, dip(5f), 0, dip(5f), 0);
						if(null != btn2) setMargins(btn2, 0, 0, 0, dip(20f));
//						setMargins(newRow, 0, 0, 0, 0);
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
		btn.setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_bold));
		btn.setTextColor(Color.parseColor("#000000"));
		btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
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
		header.setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_bold));
		header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		header.setGravity(Gravity.CENTER_HORIZONTAL);

		TableRow.MarginLayoutParams headerLayout = new TableRow.MarginLayoutParams(TableRow.MarginLayoutParams.MATCH_PARENT, TableRow.MarginLayoutParams.WRAP_CONTENT);
		header.setLayoutParams(headerLayout);
		return header;
	}

	private void setMargins(View view, int left, int top, int right, int bottom) {
		if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
			p.setMargins(left, top, right, bottom);
			view.requestLayout();
		}
	}

	private int dip(float pix) {
		return (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP,
				pix,
				getResources().getDisplayMetrics()
		);
	}
}