package com.tobikster.medicreminder.di;

import android.app.Application;

import com.tobikster.medicreminder.MedicReminderApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {
	@Component.Builder
	interface Builder {
		@BindsInstance Builder application(Application application);
		AppComponent build();
	}

	void inject(MedicReminderApplication application);
}
