package com.tobikster.medicreminder.domain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.tobikster.medicreminder.data.Reminder;

import org.joda.time.LocalTime;

import java.util.Arrays;
import java.util.List;

public class RemindersRepository implements RemindersDataSource {

	@Override
	public LiveData<List<Reminder>> getAllReminders() {
		MutableLiveData<List<Reminder>> result = new MutableLiveData<>();
		result.setValue(Arrays.asList(new Reminder("Reminder 1", new LocalTime(12, 0)),
		                              new Reminder("Reminder 2", new LocalTime(12, 30)),
		                              new Reminder("Reminder 3", new LocalTime(13, 0)),
		                              new Reminder("Reminder 4", new LocalTime(13, 30)),
		                              new Reminder("Reminder 5", new LocalTime(14, 0)),
		                              new Reminder("Reminder 6", new LocalTime(14, 30)),
		                              new Reminder("Reminder 7", new LocalTime(15, 0))));
		return result;
	}
}
