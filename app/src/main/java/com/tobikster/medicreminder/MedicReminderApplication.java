package com.tobikster.medicreminder;

import android.app.Activity;

import com.squareup.leakcanary.LeakCanary;
import com.tobikster.medicreminder.ui.RemindersListActivity;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class MedicReminderApplication extends DaggerApplication {
	@Inject
	DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

	@Override
	public void onCreate() {
		super.onCreate();

		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}

		LeakCanary.install(this);

		JodaTimeAndroid.init(this);
	}

	@Override
	protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
		return DaggerMedicReminderApplication_Component.builder().create(this);
	}

	@dagger.Component(modules = {AndroidSupportInjectionModule.class, RemindersListActivity.Module.class})
	interface Component extends AndroidInjector<MedicReminderApplication> {
		@dagger.Component.Builder
		abstract class Builder extends AndroidInjector.Builder<MedicReminderApplication> {}
	}
}
