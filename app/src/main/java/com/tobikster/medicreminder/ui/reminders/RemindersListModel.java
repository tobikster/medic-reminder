package com.tobikster.medicreminder.ui.reminders;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.tobikster.medicreminder.data.Reminder;
import com.tobikster.medicreminder.domain.RemindersDataSource;

import java.util.List;

import javax.inject.Inject;

public class RemindersListModel extends AndroidViewModel {
	private LiveData<List<Reminder>> reminders;
	private RemindersDataSource remindersDataSource;

	@Inject
	public RemindersListModel(final @NonNull Application application, final RemindersDataSource remindersDataSource) {
		super(application);
		this.remindersDataSource = remindersDataSource;
	}

	public LiveData<List<Reminder>> getReminders() {
		if (reminders == null) {
			reminders = remindersDataSource.getAllReminders();
		}
		return reminders;
	}
}
