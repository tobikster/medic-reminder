package com.tobikster.medicreminder;

import android.app.Application;

import timber.log.Timber;

public class MedicReminderApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
	}
}
