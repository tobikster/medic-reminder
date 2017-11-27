package com.tobikster.medicreminder.data.reminders

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = arrayOf(Reminder::class), version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class Database : RoomDatabase() {
	abstract fun reminderDao(): ReminderDao
}
