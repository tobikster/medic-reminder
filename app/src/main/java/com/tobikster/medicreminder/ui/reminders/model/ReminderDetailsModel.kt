package com.tobikster.medicreminder.ui.reminders.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.tobikster.medicreminder.data.reminders.RemindersDataSource

import java.time.LocalTime

import javax.inject.Inject

class ReminderDetailsModel @Inject constructor(
		private val remindersDataSource: RemindersDataSource
) : ViewModel() {

	val reminderLiveData: LiveData<Reminder> = MutableLiveData()

	init {
		(reminderLiveData as MutableLiveData<Reminder>).value = null
	}

	fun addReminder(title: String, hour: Int, minute: Int): Boolean {
		var reminderAdded = false
		if (title.isNotEmpty() && hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
			val reminder = com.tobikster.medicreminder.data.reminders.Reminder(0, title, LocalTime.of(hour, minute))
			reminderAdded = remindersDataSource.addReminder(reminder) > 0
		}
		return reminderAdded
	}
}
