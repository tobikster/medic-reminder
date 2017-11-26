package com.tobikster.medicreminder.data.reminders

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import java.time.LocalTime
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemindersRepository @Inject constructor() : RemindersDataSource {

	private val reminders: MutableLiveData<List<Reminder>> = MutableLiveData()

	init {
		val remindersList = ArrayList<Reminder>()
		remindersList.add(Reminder(0, "Reminder 1", LocalTime.of(12, 0)))
		remindersList.add(Reminder(0, "Reminder 2", LocalTime.of(12, 30)))
		remindersList.add(Reminder(0, "Reminder 3", LocalTime.of(13, 0)))
		remindersList.add(Reminder(0, "Reminder 4", LocalTime.of(13, 30)))
		remindersList.add(Reminder(0, "Reminder 5", LocalTime.of(14, 0)))
		remindersList.add(Reminder(0, "Reminder 6", LocalTime.of(14, 30)))
		remindersList.add(Reminder(0, "Reminder 7", LocalTime.of(15, 0)))
		reminders.value = remindersList
	}

	override fun getAllReminders(): LiveData<List<Reminder>> {
		return reminders
	}

	override fun addReminder(reminder: Reminder): Boolean {
		var result = false
		if (reminder.name.isNotEmpty()) {
			val remindersList: MutableList<Reminder>? = this.reminders.value as MutableList<Reminder>?
			if (remindersList != null) {
				remindersList.add(reminder)
				this.reminders.value = remindersList
				result = true
			}
		}
		return result
	}
}
