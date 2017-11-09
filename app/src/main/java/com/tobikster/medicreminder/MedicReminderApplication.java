package com.tobikster.medicreminder;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tobikster.medicreminder.di.DaggerAppComponent;

import net.danlew.android.joda.JodaTimeAndroid;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class MedicReminderApplication extends Application implements HasActivityInjector {
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

		DaggerAppComponent.builder().application(this).build().inject(this);

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}

		LeakCanary.install(this);

		JodaTimeAndroid.init(this);
	}

	@Override
	public AndroidInjector<Activity> activityInjector() {
		return activityDispatchingAndroidInjector;
	}
}
