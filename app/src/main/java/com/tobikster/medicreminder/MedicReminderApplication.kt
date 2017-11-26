package com.tobikster.medicreminder

import android.app.Activity
import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.tobikster.medicreminder.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MedicReminderApplication : Application(), HasActivityInjector {
	@Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun onCreate() {
		super.onCreate()

		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return
		}

		DaggerAppComponent.builder().application(this).build().inject(this)

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}

		LeakCanary.install(this)
	}


	override fun activityInjector(): AndroidInjector<Activity>? {
		return activityDispatchingAndroidInjector
	}
}
