package com.tobikster.medicreminder.ui.reminders.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.tobikster.medicreminder.data.reminders.RemindersDataSource;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RemindersListModel extends ViewModel {
	public final LiveData<List<Reminder>> remindersList;

	@Inject
	public RemindersListModel(final RemindersDataSource remindersDataSource) {
		remindersList = Transformations.map(remindersDataSource.getAllReminders(), reminders -> {
			List<Reminder> result = new ArrayList<>();
			for(com.tobikster.medicreminder.data.reminders.model.Reminder reminder: reminders) {
				result.add(new Reminder(reminder.getName(), reminder.getTime()));
			}
			return result;
		});
	}

	public class Reminder {
		private String title;
		private LocalTime time;

		Reminder(String title, LocalTime time) {
			this.title = title;
			this.time = time;
		}

		public String getTitle() {
			return title;
		}

		public LocalTime getTime() {
			return time;
		}
	}
}
