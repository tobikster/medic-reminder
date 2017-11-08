package com.tobikster.medicreminder.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Reminder.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	private static volatile AppDatabase instance;

	public static AppDatabase getInstance(final Context context) {
		if (instance == null) {
			synchronized (AppDatabase.class) {
				if (instance == null) {
					instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "medicreminder.db").build();
				}
			}
		}
		return instance;
	}

	public abstract ReminderDao getReminderDao();

}
