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

	public class Reminder {
		private String title;
		private LocalTime time;

		public String getTitle() {
			return title;
		}

		public LocalTime getTime() {
			return time;
		}

		Reminder(String title, LocalTime time) {

			this.title = title;
			this.time = time;
		}
	}
}
