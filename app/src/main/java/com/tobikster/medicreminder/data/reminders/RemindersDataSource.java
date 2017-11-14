package com.tobikster.medicreminder.data.reminders;

import android.arch.lifecycle.LiveData;

import com.tobikster.medicreminder.data.reminders.model.Reminder;

import java.util.List;

public interface RemindersDataSource {
	LiveData<List<Reminder>> getAllReminders();
	boolean addReminder(final Reminder reminder);
}
