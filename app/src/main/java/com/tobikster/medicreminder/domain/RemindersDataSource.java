package com.tobikster.medicreminder.domain;

import android.arch.lifecycle.LiveData;

import com.tobikster.medicreminder.data.Reminder;

import java.util.List;

public interface RemindersDataSource {
	LiveData<List<Reminder>> getAllReminders();
}
