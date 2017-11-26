package com.tobikster.medicreminder.data.reminders

import java.time.LocalTime

data class Reminder(
		val id: Long,
		val name: String,
		val time: LocalTime
)
