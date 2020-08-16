package com.example.cardinalbotics.ui.resources;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;

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

public class ResourcesFragment extends Fragment {

	private ResourcesViewModel resourcesViewModel;

	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		resourcesViewModel = new ViewModelProvider(this).get(ResourcesViewModel.class);
		return inflater.inflate(R.layout.fragment_resources, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		final LinearLayout layout = ((LinearLayout) getView().findViewById(R.id.resourcesList));
		final int[] id = {0};
		AppSharedResources.getInstance(getActivity().getApplicationContext()).requestDataResource(new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					JSONArray entries = response.getJSONArray("values");
					boolean sectionedOff = false;
					for (int entry = 0; entry < entries.length(); entry++) {
						JSONArray data = entries.getJSONArray(entry);

						String name = data.getString(0);
						String url = data.getString(1);
						String id = data.getString(2);

						//Generate the Buttons Here
						appendFormRow(name, url, id);
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

	public void appendFormRow(String buttonText, String url, String id) {
		LinearLayout layout = getView().findViewById(R.id.resourcesList);

		Button btn = new Button(getContext());
		btn.setText(buttonText);
		btn.setTextSize(20);
		btn.setTypeface(ResourcesCompat.getFont(getContext(), R.font.roboto_bold));
		btn.setBackgroundColor(Color.parseColor("#A17D1120"));
		btn.setTag(url);

		switch (id) {
			case "googledrive":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_google_drive, 0);
				break;
			case "roster":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_roster, 0);
				break;
			case "calendar":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_calendar, 0);
				break;
			case "forms":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_form, 0);
				break;
			case "website":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_teamweb, 0);
				break;
			case "photo":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_photos, 0);
				break;
			case "youtube":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_youtube, 0);
				break;
			case "handbook":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_handbook, 0);
				break;
			case "training":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_traindoc, 0);
				break;
			case "irc":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_ircgamemanual, 0);
				break;
			case "grant":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_grant, 0);
				break;
			case "money":
				btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_resource_money, 0);
				break;
		}

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


		Space space = new Space(getContext());
		ViewGroup.LayoutParams spaceLayout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 30);
		space.setLayoutParams(spaceLayout);

		layout.addView(btn);
		layout.addView(space);

		setMargins(layout, 30, 30, 30, 50);
	}

	public void setMargins(View view, int left, int top, int right, int bottom) {
		if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
			p.setMargins(left, top, right, bottom);
			view.requestLayout();
		}
	}
}
