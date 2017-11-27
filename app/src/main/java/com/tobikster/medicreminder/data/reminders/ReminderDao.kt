package com.tobikster.medicreminder.data.reminders

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ReminderDao {
	@Query("SELECT * FROM Reminder")
	fun getAllReminders(): LiveData<List<Reminder>>

	@Query("SELECT * FROM Reminder WHERE id=:id")
	fun getReminder(id: Long): LiveData<Reminder>

	@Insert
	fun addReminder(reminder: Reminder): Long
}
