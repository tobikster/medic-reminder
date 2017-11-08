package com.tobikster.medicreminder.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tobikster.medicreminder.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RemindersListFragment remindersListFragment = RemindersListFragment.newInstance();
		getSupportFragmentManager().beginTransaction().replace(R.id.content, remindersListFragment).commit();
	}
}
