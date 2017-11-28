package com.tobikster.medicreminder.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.tobikster.medicreminder.data.reminders.Database
import com.tobikster.medicreminder.data.reminders.ReminderDao
import com.tobikster.medicreminder.data.reminders.RemindersDataSource
import com.tobikster.medicreminder.data.reminders.RemindersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
internal class AppModule {
	@Singleton
	@Provides
	fun provideContext(app: Application): Context = app.applicationContext

	@Singleton
	@Provides
	fun provideDatabase(context: Context): Database =
			Room.databaseBuilder(context, Database::class.java, "medicreminder.db").build()

	@Singleton
	@Provides
	fun provideReminderDao(database: Database): ReminderDao = database.reminderDao()

	@Singleton
	@Provides
	fun provideReminderDataSource(remindersRepository: RemindersRepository): RemindersDataSource = remindersRepository
}
