package com.tobikster.medicreminder.data.reminders

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.LocalTime

@Entity
data class Reminder(
		@PrimaryKey(autoGenerate = true)
		val id: Long,
		val name: String,
		val time: LocalTime
)