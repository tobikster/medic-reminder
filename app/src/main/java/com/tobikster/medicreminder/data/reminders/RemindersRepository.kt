package com.tobikster.medicreminder.data.reminders

import android.arch.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemindersRepository @Inject constructor(
		private val reminderDao: ReminderDao
) : RemindersDataSource {
	override fun getAllReminders(): LiveData<List<Reminder>> = reminderDao.getAllReminders()

	override fun addReminder(reminder: Reminder): Long {
		val reminderDao = reminderDao
		var insertedId = 0L
		if (reminder.name.isNotEmpty()) {
			insertedId = reminderDao.addReminder(reminder)
		}
		return insertedId
	}
}
