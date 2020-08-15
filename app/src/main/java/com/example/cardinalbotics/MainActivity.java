package com.example.cardinalbotics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
	Button button;
	private AppBarConfiguration mAppBarConfiguration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
//		FloatingActionButton fab = findViewById(R.id.fab);
//		fab.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Snackbar.make(view, "email ayaka.chou@team4159.org if you have any questions on how to use app", Snackbar.LENGTH_LONG)
//						.setAction("Action", null).show();
//				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:ayaka.chou@team4159.org"));
//				startActivity(browserIntent);
//			}
//		});
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
			@Override
			public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
				((TextView) findViewById(R.id.studentId)).setText(AppSharedResources.getInstance(getApplicationContext()).storeGet("password"));
			}

			@Override
			public void onDrawerOpened(@NonNull View drawerView) {

			}

			@Override
			public void onDrawerClosed(@NonNull View drawerView) {

			}

			@Override
			public void onDrawerStateChanged(int newState) {

			}
		});
		NavigationView navigationView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_home, R.id.nav_calendar, R.id.nav_forms, R.id.nav_resources, R.id.nav_training, R.id.nav_login)
				.setOpenableLayout(drawer)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
				|| super.onSupportNavigateUp();
	}

	public void link(View view) {
		String url = "https://";
		if (view.getId() == R.id.button1) {
			url = "https://www.team4159.org/";
		} else if (view.getId() == R.id.button2) {
			url = "https://drive.google.com/drive/folders/1i3iPyM8DhkLzd8wtGz9jn305scfvdKW2?usp=sharing";
		} else if (view.getId() == R.id.button3) {
			url = "https://docs.google.com/spreadsheets/d/1RdV0RmXQFTVhPmzd7iwHLWjBwX4bcGN4Cr1apzvLKGo/edit?usp=sharing";
		} else if (view.getId() == R.id.button4) {
			url = "https://docs.google.com/spreadsheets/d/10tFI-9L5lEg6GgEVudibQn9Qtt8VgiWgW3QRCUARfD8/edit";
		} else if (view.getId() == R.id.button5) {
			url = "https://calendar.google.com/calendar/embed?src=nicholas.do%40team4159.org&ctz=America%2FLos_Angeles";
		} else if (view.getId() == R.id.button6) {
			url = "https://docs.google.com/spreadsheets/d/1po_TE36FA-I7J2Y-Biw5snWdfSm_Cx055KVi1c43G7Y/edit?usp=sharing";
		} else if (view.getId() == R.id.button7) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button8) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button9) {
			url = "https://drive.google.com/drive/folders/1rBSu0S9LPT8yfo2YVwzZgNt_JxE4767O?usp=sharing";
		} else if (view.getId() == R.id.button10) {
			url = "https://docs.google.com/document/d/135V9yQGAYNLl9aOCvyKJVLOLIh2yPcCEuZ69nNrumYY/edit?usp=sharing"));
		} else {
			url = "https://docs.google.com/document/d/17RjyV-YCr4_zKslnDachYI_9EAM11asWkexZfDYN4CM/edit?usp=sharing";
		}
		Intent browserIntent = new Intent(new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		startActivity(browserIntent);
	}

	public void training(View view) {
		String url = "";
		if (view.getId() == R.id.button12) {
			url = "https://docs.google.com/presentation/d/1-3JSEriTv5PFjdkyCE34i-5jmUD_zAhhj0TTWjjj3x4/edit?usp=sharing";
		} else if (view.getId() == R.id.button13) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ"));
		} else if (view.getId() == R.id.button14) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button15) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button16) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button17) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button18) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button19) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button20) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button21) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button22) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button23) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button24) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button25) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button26) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button27) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button28) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button29) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button30) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button31) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button32) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button33) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button34) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button35) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button36) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button37) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button38) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button39) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button40) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button41) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ"));
		} else {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		}
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ"));
		startActivity(browserIntent);
	}
}
