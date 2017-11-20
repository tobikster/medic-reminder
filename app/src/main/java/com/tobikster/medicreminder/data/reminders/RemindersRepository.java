package com.tobikster.medicreminder.data.reminders;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tobikster.medicreminder.data.reminders.model.Reminder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RemindersRepository implements RemindersDataSource {
	private final MutableLiveData<List<Reminder>> reminders;

	private RemindersRepository() {
		reminders = new MutableLiveData<>();
		final List<Reminder> remindersList = new ArrayList<>();
		remindersList.add(new Reminder("Reminder 1", LocalTime.of(12, 0)));
		remindersList.add(new Reminder("Reminder 2", LocalTime.of(12, 30)));
		remindersList.add(new Reminder("Reminder 3", LocalTime.of(13, 0)));
		remindersList.add(new Reminder("Reminder 4", LocalTime.of(13, 30)));
		remindersList.add(new Reminder("Reminder 5", LocalTime.of(14, 0)));
		remindersList.add(new Reminder("Reminder 6", LocalTime.of(14, 30)));
		remindersList.add(new Reminder("Reminder 7", LocalTime.of(15, 0)));
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
	public boolean addReminder(final Reminder reminder) {
		boolean result = false;
		if (reminder.getName().length() > 0) {
			final List<Reminder> remindersList = this.reminders.getValue();
			if (remindersList != null && reminder.getName().length() > 0) {
				remindersList.add(reminder);
				this.reminders.setValue(remindersList);
				result = true;
			}
		}
		return result;
	}

	private static class InstanceHolder {
		private static final RemindersRepository INSTANCE = new RemindersRepository();

		private InstanceHolder() {}
	}
}
