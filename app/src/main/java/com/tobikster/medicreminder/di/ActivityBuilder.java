package com.tobikster.medicreminder.di;

import android.app.Activity;

import com.tobikster.medicreminder.presentation.MainActivity;
import com.tobikster.medicreminder.presentation.RemindersComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityBuilder {
	@Binds
	@IntoMap
	@ActivityKey(MainActivity.class)
	abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(RemindersComponent.Builder builder);
}
