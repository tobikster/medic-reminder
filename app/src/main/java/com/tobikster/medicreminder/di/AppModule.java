package com.tobikster.medicreminder.di;

import android.arch.lifecycle.ViewModelProvider;

import com.tobikster.medicreminder.domain.RemindersDataSource;
import com.tobikster.medicreminder.domain.RemindersRepository;
import com.tobikster.medicreminder.ui.reminders.model.ReminderViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
	@Singleton
	@Provides
	RemindersDataSource provideReminderDataSource() {
		return RemindersRepository.getInstance();
	}

	@Singleton
	@Provides
	ViewModelProvider.Factory provideViewModelFactory(final ViewModelSubComponent.Builder viewModelComponentBuilder) {
		return new ReminderViewModelFactory(viewModelComponentBuilder.build());
	}
}
