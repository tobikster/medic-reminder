package com.tobikster.medicreminder.di;

import com.tobikster.medicreminder.ui.RemindersListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
	@ContributesAndroidInjector
	abstract RemindersListFragment contributeRemindersListFragment();
}
