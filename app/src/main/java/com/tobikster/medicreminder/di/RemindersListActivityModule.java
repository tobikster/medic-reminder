package com.tobikster.medicreminder.di;

import com.tobikster.medicreminder.ui.RemindersListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RemindersListActivityModule {
	@ContributesAndroidInjector(modules = FragmentBuildersModule.class)
	abstract RemindersListActivity contributeRemindersListActivity();
}
