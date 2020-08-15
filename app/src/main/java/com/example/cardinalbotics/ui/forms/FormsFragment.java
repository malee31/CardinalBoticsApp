package com.example.cardinalbotics.ui.forms;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.android.material.snackbar.Snackbar;

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
		final int[] id = {0};
		AppSharedResources.getInstance(getActivity().getApplicationContext()).requestDataSheet(new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					JSONArray entries = response.getJSONArray("values");
					boolean sectionedOff = false;
					for (int entry = 0; entry < entries.length(); entry++) {
						JSONArray data = entries.getJSONArray(entry);
						String type = data.getString(0);
						String name = data.getString(1);
						String url = data.getString(2);
						String status = data.getString(3);
						String dueBy = data.getString(4);

						if(!sectionedOff && type.trim().toLowerCase().equals("always active")) {
							TableLayout layout = getView().findViewById(R.id.formsList);
							TableRow newRow = new TableRow(getContext());

							TextView text = new TextView(getContext());
							text.setText("Permanent Forms");

							newRow.addView(text);
							layout.addView(newRow);
							System.out.println("SECTIONING OFF HERE");
							sectionedOff = true;
						}

						//Generate the Buttons Here
						appendFormRow(name, dueBy, url);
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
		TableLayout layout = getView().findViewById(R.id.formsList);
		TableRow newRow = new TableRow(getContext());

		Button btn = new Button(getContext());
		btn.setText(buttonText);
		btn.setBackgroundColor(Color.parseColor("#A17D1120"));
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

		TextView text = new TextView(getContext());
		text.setText(sideText);

		Space space = new Space(getContext());
		ViewGroup.LayoutParams spaceLayout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 25);
		space.setLayoutParams(spaceLayout);

		ViewGroup.LayoutParams rowLayout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		newRow.setLayoutParams(rowLayout);
		newRow.addView(btn);
		newRow.addView(text);

		layout.addView(newRow);
		layout.addView(space);

		setMargins(text, 50, 0, 0, 0);
	}

	private void setMargins(View view, int left, int top, int right, int bottom) {
		if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
			p.setMargins(left, top, right, bottom);
			view.requestLayout();
		}
	}
}