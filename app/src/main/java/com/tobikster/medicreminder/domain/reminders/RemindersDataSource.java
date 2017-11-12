package com.tobikster.medicreminder.domain.reminders;

import android.arch.lifecycle.LiveData;

import com.tobikster.medicreminder.domain.reminders.model.Reminder;

import java.util.List;

public interface RemindersDataSource {
	LiveData<List<Reminder>> getAllReminders();
	void addReminder(final Reminder reminder);
}
