package com.tobikster.medicreminder.ui.reminders.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.tobikster.medicreminder.data.reminders.RemindersDataSource
import javax.inject.Inject


class RemindersListModel @Inject constructor(
		remindersDataSource: RemindersDataSource
) : ViewModel() {
	val remindersList: LiveData<List<Reminder>> =
			Transformations.map<List<com.tobikster.medicreminder.data.reminders.model.Reminder>, List<Reminder>>(remindersDataSource.getAllReminders(), {
				it.map { Reminder(it.name, it.time) }
			})

}
