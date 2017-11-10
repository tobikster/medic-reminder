package com.tobikster.medicreminder.di;

import android.app.Application;

import com.tobikster.medicreminder.MedicReminderApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
		AndroidInjectionModule.class,
        AppModule.class,
        RemindersListActivityModule.class
})
public interface AppComponent {
	@Component.Builder
	interface Builder {
		@BindsInstance
		Builder application(final Application application);
		AppComponent build();
	}
	void inject(final MedicReminderApplication medicReminderApplication);
}
