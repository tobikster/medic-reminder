package com.tobikster.medicreminder.ui.reminders.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.tobikster.medicreminder.domain.RemindersDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RemindersListModel extends ViewModel {
	public final LiveData<List<Reminder>> remindersList;
	private final RemindersDataSource remindersDataSource;

	@Inject
	public RemindersListModel(final RemindersDataSource remindersDataSource) {
		this.remindersDataSource = remindersDataSource;
		remindersList = Transformations.map(this.remindersDataSource.getAllReminders(), reminders -> {
			List<Reminder> result = new ArrayList<>();
			for(com.tobikster.medicreminder.domain.model.Reminder reminder: reminders) {
				result.add(new Reminder(reminder.getName(), reminder.getTime()));
			}
			return result;
		});
	}
}
