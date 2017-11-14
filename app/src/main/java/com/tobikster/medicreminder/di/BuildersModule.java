package com.tobikster.medicreminder.di;

import com.tobikster.medicreminder.ui.reminders.ReminderDetailsFragment;
import com.tobikster.medicreminder.ui.reminders.RemindersActivity;
import com.tobikster.medicreminder.ui.reminders.RemindersListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface BuildersModule {
	@ContributesAndroidInjector
	RemindersActivity contributeRemindersListActivity();

	@ContributesAndroidInjector
	RemindersListFragment contributeRemindersListFragment();

	@ContributesAndroidInjector
	ReminderDetailsFragment contributeReminderDetailFragment();
}
