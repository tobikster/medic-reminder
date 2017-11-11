package com.tobikster.medicreminder.di;

import com.tobikster.medicreminder.ui.reminders.RemindersListActivity;
import com.tobikster.medicreminder.ui.reminders.RemindersListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface BuildersModule {
	@ContributesAndroidInjector
	RemindersListFragment contributeRemindersListFragment();


	@ContributesAndroidInjector
	RemindersListActivity contributeRemindersListActivity();
}
