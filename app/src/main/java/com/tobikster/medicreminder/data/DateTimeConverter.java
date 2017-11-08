package com.tobikster.medicreminder.data;

import android.arch.persistence.room.TypeConverter;

import org.joda.time.LocalTime;

@SuppressWarnings("WeakerAccess")
class DateTimeConverter {
	private DateTimeConverter() {}

	@TypeConverter
	public static LocalTime fromMillis(final Long millis) {
		return LocalTime.fromMillisOfDay(millis);
	}

	@TypeConverter
	public static Long fromLocalTime(final LocalTime localTime) {
		return (long) localTime.getMillisOfDay();
	}
}
