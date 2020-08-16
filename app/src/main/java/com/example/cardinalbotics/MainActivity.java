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
				R.id.nav_home, R.id.nav_calendar, R.id.nav_forms, R.id.nav_resources, R.id.nav_training, R.id.nav_login, R.id.nav_timelog)
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


	public void training(View view) {
		String url = "";
		if (view.getId() == R.id.button12) {
			url = "https://docs.google.com/presentation/d/1-3JSEriTv5PFjdkyCE34i-5jmUD_zAhhj0TTWjjj3x4/edit?usp=sharing";
		} else if (view.getId() == R.id.button13) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button14) {
			url = "https://docs.google.com/document/d/19Vp4Mc0M0EGQ0T_dZnD-DdPzOlWwLieARODYt-xSEbk/edit";
		} else if (view.getId() == R.id.button15) {
			url = "https://docs.google.com/presentation/d/16gn59qkjmUjYd--Mud4NDHDZpljU24xEVJ0jfXFNIyw/edit?usp=sharing";
		} else if (view.getId() == R.id.button16) {
			url = "https://docs.google.com/presentation/d/1WDbFyScvhplqefYX4b-AZP-TlWCloticNAgK4LUTPDc/edit?usp=sharing";
		} else if (view.getId() == R.id.button17) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button18) {
			url = "https://codingbat.com/python";
		} else if (view.getId() == R.id.button19) {
			url = "https://www.learnpython.org/";
		} else if (view.getId() == R.id.button20) {
			url = "https://www.tutorialspoint.com/python/index.htm";
		} else if (view.getId() == R.id.button21) {
			url = "https://www.codecademy.com/learn/learn-python";
		} else if (view.getId() == R.id.button22) {
			url = "https://robotpy.readthedocs.io/en/stable/";
		} else if (view.getId() == R.id.button23) {
			url = "http://www.citruscircuits.org/uploads/6/9/3/4/6934550/robot_wiring.pdf";
		} else if (view.getId() == R.id.button24) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button25) {
			url = "https://www.codecademy.com/learn/learn-java";
		} else if (view.getId() == R.id.button26) {
			url = "https://codingbat.com/java";
		} else if (view.getId() == R.id.button27) {
			url = "https://github.com/Team4159";
		} else if (view.getId() == R.id.button28) {
			url = "https://www.youtube.com/watch?v=uGtT8ojgSzg";
		} else if (view.getId() == R.id.button29) {
			url = "https://drive.google.com/drive/folders/1gisd-FH-XU8wbz2M4Mr-SzQx0UQNwspj";
		} else if (view.getId() == R.id.button30) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button31) {
			url = "https://docs.google.com/presentation/d/1VDdrfGJsfbc2MEezSSUdEgt_KqQ3IdYvN3mgrX8tmwI/edit?usp=sharing";
		} else if (view.getId() == R.id.button32) {
			url = "https://www.youtube.com/playlist?list=PLOKjEBaXxWbnwVZOEO9c0qaKvn-9O79Va";
		} else if (view.getId() == R.id.button33) {
			url = "https://docs.google.com/presentation/d/1RUE8cu9ajZRU7McBT-yi0a71mNaSPH6T2xEBwsBK2xM/edit#slide=id.p";
		} else if (view.getId() == R.id.button34) {
			url = "https://www.youtube.com/playlist?list=PLdhKcDv50ts4sUT_8LhrrAsd6VUVgtTwd";
		} else if (view.getId() == R.id.button35) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button36) {
			url = "https://www.youtube.com/playlist?list=PLdhKcDv50ts5ecZ4ML5bJVuMVUdPriRyL";
		} else if (view.getId() == R.id.button37) {
			url = "https://docs.google.com/presentation/d/1P5eF17pxyB4FdQ4eowdGZJCe5NBzLqwVASZN1GCMN-M/edit?usp=sharing";
		} else if (view.getId() == R.id.button38) {
			url = "https://docs.google.com/document/d/1s8yZl4swGWui5kfPxrPaXTZvgV3YQAYgpHOWLe_y96U/edit";
		} else if (view.getId() == R.id.button39) {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		} else if (view.getId() == R.id.button40) {
			url = "https://drive.google.com/drive/folders/0B1En7yzIhCm6UEZDMkdhdTdXYm8?usp=sharing";
		} else if (view.getId() == R.id.button41) {
			url = "https://www.youtube.com/playlist?list=PLdhKcDv50ts4elT5X0z1aaDkMYGTCw_cE";
		} else {
			url = "https://www.youtube.com/channel/UCDc0DVwU0NN62J98qPxuurQ";
		}
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		startActivity(browserIntent);
	}
}
