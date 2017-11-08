package com.tobikster.medicreminder;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

public class MedicReminderApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);

		JodaTimeAndroid.init(this);

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
	}
}
