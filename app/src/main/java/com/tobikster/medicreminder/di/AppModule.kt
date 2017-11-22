package com.tobikster.medicreminder.di

import android.arch.lifecycle.ViewModelProvider
import com.tobikster.medicreminder.data.reminders.RemindersDataSource
import com.tobikster.medicreminder.data.reminders.RemindersRepository
import com.tobikster.medicreminder.ui.reminders.model.ReminderViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
internal class AppModule {
	@Singleton
	@Provides
	fun provideReminderDataSource(remindersRepository: RemindersRepository): RemindersDataSource = remindersRepository

	@Singleton
	@Provides
	fun provideViewModelFactory(viewModelComponentBuilder: ViewModelSubComponent.Builder): ViewModelProvider.Factory = ReminderViewModelFactory(viewModelComponentBuilder.build())
}
