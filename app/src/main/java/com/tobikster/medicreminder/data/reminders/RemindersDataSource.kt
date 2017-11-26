package com.tobikster.medicreminder.data.reminders

import android.arch.lifecycle.LiveData

interface RemindersDataSource {
	fun getAllReminders(): LiveData<List<Reminder>>
	fun addReminder(reminder: Reminder): Boolean
}
