package com.tobikster.medicreminder.ui.reminders.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.tobikster.medicreminder.data.reminders.RemindersDataSource;

import java.time.LocalTime;

import javax.inject.Inject;

public class ReminderDetailsModel extends ViewModel {
	private RemindersDataSource remindersDataSource;

	public final LiveData<Reminder> reminderLiveData;

	@Inject
	public ReminderDetailsModel(final RemindersDataSource remindersDataSource) {
		this.remindersDataSource = remindersDataSource;
		reminderLiveData = new MutableLiveData<>();
		((MutableLiveData<Reminder>) reminderLiveData).setValue(null);
	}

	public boolean addReminder(String title, int hour, int minute) {
		boolean reminderAdded = false;
		if (title != null && title.length() > 0 && hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
			final com.tobikster.medicreminder.data.reminders.model.Reminder reminder =
					new com.tobikster.medicreminder.data.reminders.model.Reminder(title, LocalTime.of(hour, minute));
			reminderAdded = remindersDataSource.addReminder(reminder);
		}
		return reminderAdded;
	}
}
