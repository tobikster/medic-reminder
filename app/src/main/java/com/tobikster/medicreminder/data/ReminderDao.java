package com.tobikster.medicreminder.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ReminderDao {
	@Query("SELECT * FROM reminder")
	Flowable<List<Reminder>> getAll();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertReminder(final Reminder reminder);

	@Query("DELETE FROM reminder")
	void removeAllReminders();

}
