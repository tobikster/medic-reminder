package com.tobikster.medicreminder.data.reminders

import android.arch.lifecycle.LiveData
import io.reactivex.Completable

interface RemindersDataSource {
	fun getAllReminders(): LiveData<List<Reminder>>
	fun addReminder(reminder: Reminder): Completable
}
