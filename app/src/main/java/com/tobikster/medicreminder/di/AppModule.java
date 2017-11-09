package com.tobikster.medicreminder.di;

import android.app.Application;
import android.content.Context;

import com.tobikster.medicreminder.ui.RemindersComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {RemindersComponent.class})
public class AppModule {
	@Provides
	@Singleton
	Context provideContext(Application application) {
		return application;
	}
}
