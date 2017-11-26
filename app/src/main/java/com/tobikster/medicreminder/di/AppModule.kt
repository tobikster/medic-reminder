package com.tobikster.medicreminder.di

import com.tobikster.medicreminder.data.reminders.RemindersDataSource
import com.tobikster.medicreminder.data.reminders.RemindersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
internal class AppModule {
	@Singleton
	@Provides
	fun provideReminderDataSource(remindersRepository: RemindersRepository): RemindersDataSource = remindersRepository
}
