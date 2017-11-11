package com.tobikster.medicreminder.ui.reminders.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.tobikster.medicreminder.domain.RemindersDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RemindersListModel extends AndroidViewModel {
	public final LiveData<List<Reminder>> remindersList;

	@Inject
	public RemindersListModel(final @NonNull Application application, final RemindersDataSource remindersDataSource) {
		super(application);
		remindersList = Transformations.map(remindersDataSource.getAllReminders(), reminders -> {
			List<Reminder> result = new ArrayList<>();
			for(com.tobikster.medicreminder.data.Reminder reminder: reminders) {
				result.add(new Reminder(reminder.getName(), reminder.getTime()));
			}
			return result;
		});
	}
}
