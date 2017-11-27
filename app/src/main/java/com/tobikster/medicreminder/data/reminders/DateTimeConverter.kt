package com.tobikster.medicreminder.data.reminders

import android.arch.persistence.room.TypeConverter
import java.time.LocalTime

class DateTimeConverter {
	companion object {
		@JvmStatic
		@TypeConverter
		fun secondToLocalTime(second: Long): LocalTime = LocalTime.ofSecondOfDay(second)

		@JvmStatic
		@TypeConverter
		fun localTimeToSecond(time: LocalTime): Long = time.toSecondOfDay().toLong()
	}
}
