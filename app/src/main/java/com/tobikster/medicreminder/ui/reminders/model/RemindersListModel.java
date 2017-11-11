package com.tobikster.medicreminder.ui.reminders.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.tobikster.medicreminder.domain.RemindersDataSource;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RemindersListModel extends AndroidViewModel {
	public final LiveData<List<Reminder>> remindersList;
	private final RemindersDataSource remindersDataSource;

	@Inject
	public RemindersListModel(final @NonNull Application application, final RemindersDataSource remindersDataSource) {
		super(application);
		this.remindersDataSource = remindersDataSource;
		remindersList = Transformations.map(this.remindersDataSource.getAllReminders(), reminders -> {
			List<Reminder> result = new ArrayList<>();
			for(com.tobikster.medicreminder.domain.model.Reminder reminder: reminders) {
				result.add(new Reminder(reminder.getName(), reminder.getTime()));
			}
			return result;
		});
	}

	public void addReminder(final String name, final LocalTime time) {
		if (name != null && name.length() > 0 && time != null) {
			this.remindersDataSource.addReminder(new com.tobikster.medicreminder.domain.model.Reminder(name, time));
		}
	}
}
