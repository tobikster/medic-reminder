package com.tobikster.medicreminder.ui.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.tobikster.medicreminder.data.Reminder;
import com.tobikster.medicreminder.domain.RemindersDataSource;
import com.tobikster.medicreminder.domain.RemindersRepository;

import java.util.List;

public class RemindersListModel extends ViewModel {
	private LiveData<List<Reminder>> reminders;
	private RemindersDataSource remindersRepository;

	public RemindersListModel() {
		this.remindersRepository = new RemindersRepository();
	}

	public LiveData<List<Reminder>> getReminders() {
		if (reminders == null) {
			reminders = remindersRepository.getAllReminders();
		}
		return reminders;
	}
}
