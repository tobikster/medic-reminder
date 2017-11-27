package com.tobikster.medicreminder.data.reminders

import android.arch.lifecycle.LiveData
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemindersRepository @Inject constructor(
		private val reminderDao: ReminderDao
) : RemindersDataSource {
	override fun getAllReminders(): LiveData<List<Reminder>> = reminderDao.getAllReminders()

	override fun addReminder(reminder: Reminder): Completable = Completable.fromAction {
		if (reminder.name.isEmpty()) {
			throw IllegalArgumentException("Reminder name cannot be empty!")
		} else {
			reminderDao.addReminder(reminder)
		}
	}
}
