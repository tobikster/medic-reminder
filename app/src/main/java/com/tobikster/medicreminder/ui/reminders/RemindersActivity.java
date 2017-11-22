package com.tobikster.medicreminder.ui.reminders;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.tobikster.medicreminder.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RemindersActivity extends AppCompatActivity implements HasSupportFragmentInjector,
                                                                    RemindersListFragment.Interactor,
                                                                    ReminderDetailsFragment.Interactor {
	@Inject
	DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		AndroidInjection.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final RemindersListFragment remindersListFragment = RemindersListFragment.newInstance();
		getSupportFragmentManager().beginTransaction().replace(R.id.content, remindersListFragment).commit();
	}

	@Override
	public AndroidInjector<Fragment> supportFragmentInjector() {
		return fragmentDispatchingAndroidInjector;
	}

	@Override
	public void onAddReminderButtonClicked() {
		final ReminderDetailsFragment reminderDetailsFragment = ReminderDetailsFragment.Companion.newInstance();
		getSupportFragmentManager().beginTransaction()
		                           .addToBackStack(null)
		                           .replace(R.id.content, reminderDetailsFragment)
		                           .commit();
	}

	@Override
	public void onReminderAdded() {
		final RemindersListFragment remindersListFragment = RemindersListFragment.newInstance();
		getSupportFragmentManager().beginTransaction()
		                           .addToBackStack(null)
		                           .replace(R.id.content, remindersListFragment)
		                           .commit();
	}
}
