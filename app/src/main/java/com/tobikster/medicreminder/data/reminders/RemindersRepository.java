package com.tobikster.medicreminder.data.reminders;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tobikster.medicreminder.data.reminders.model.Reminder;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class RemindersRepository implements RemindersDataSource {
	private final MutableLiveData<List<Reminder>> reminders;

	private RemindersRepository() {
		reminders = new MutableLiveData<>();
		final List<Reminder> remindersList = new ArrayList<>();
		remindersList.add(new Reminder("Reminder 1", new LocalTime(12, 0)));
		remindersList.add(new Reminder("Reminder 2", new LocalTime(12, 30)));
		remindersList.add(new Reminder("Reminder 3", new LocalTime(13, 0)));
		remindersList.add(new Reminder("Reminder 4", new LocalTime(13, 30)));
		remindersList.add(new Reminder("Reminder 5", new LocalTime(14, 0)));
		remindersList.add(new Reminder("Reminder 6", new LocalTime(14, 30)));
		remindersList.add(new Reminder("Reminder 7", new LocalTime(15, 0)));
		reminders.setValue(remindersList);
	}

	public static RemindersRepository getInstance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public LiveData<List<Reminder>> getAllReminders() {
		return reminders;
	}

	@Override
	public void addReminder(final Reminder reminder) {
		final List<Reminder> remindersList = this.reminders.getValue();
		if (remindersList != null &&
		    reminder.getName() != null &&
		    reminder.getName().length() > 0 &&
		    reminder.getTime() != null) {
			remindersList.add(reminder);
			this.reminders.setValue(remindersList);
		}
	}

	private static class InstanceHolder {
		private static final RemindersRepository INSTANCE = new RemindersRepository();

		private InstanceHolder() {}
	}
}
