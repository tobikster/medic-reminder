package com.tobikster.medicreminder.data.reminders

import android.arch.lifecycle.LiveData

import com.tobikster.medicreminder.data.reminders.model.Reminder

interface RemindersDataSource {
	fun getAllReminders(): LiveData<List<Reminder>>
	fun addReminder(reminder: Reminder): Boolean
}
