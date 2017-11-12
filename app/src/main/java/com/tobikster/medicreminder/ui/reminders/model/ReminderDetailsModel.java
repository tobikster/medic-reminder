package com.tobikster.medicreminder.ui.reminders.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.tobikster.medicreminder.domain.RemindersDataSource;

import javax.inject.Inject;

public class ReminderDetailsModel extends ViewModel {
	private RemindersDataSource remindersDataSource;

	public final LiveData<Reminder> reminder;

	@Inject
	public ReminderDetailsModel(final RemindersDataSource remindersDataSource) {
		this.remindersDataSource = remindersDataSource;
		reminder = new MutableLiveData<>();
		((MutableLiveData<Reminder>) reminder).setValue(new Reminder("", null));
	}
}
