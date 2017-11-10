package com.tobikster.medicreminder.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.tobikster.medicreminder.MedicReminderApplication;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

public class AppInjector {
	private AppInjector() {}

	public static void init(final MedicReminderApplication medicReminderApplication) {
		DaggerAppComponent.builder().application(medicReminderApplication).build().inject(medicReminderApplication);

		medicReminderApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
			@Override
			public void onActivityCreated(Activity activity, Bundle bundle) {
				handleActivity(activity);
			}

			@Override
			public void onActivityStarted(Activity activity) {

			}

			@Override
			public void onActivityResumed(Activity activity) {

			}

			@Override
			public void onActivityPaused(Activity activity) {

			}

			@Override
			public void onActivityStopped(Activity activity) {

			}

			@Override
			public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

			}

			@Override
			public void onActivityDestroyed(Activity activity) {

			}
		});
	}

	private static void handleActivity(final Activity activity) {
		if (activity instanceof HasSupportFragmentInjector) {
			AndroidInjection.inject(activity);
		}
		if (activity instanceof FragmentActivity) {
			((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
				@Override
				public void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle savedInstanceState) {
					if (fragment instanceof Injectable) {
						AndroidSupportInjection.inject(fragment);
					}
				}
			}, true);
		}
	}
}
