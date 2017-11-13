package com.tobikster.medicreminder.data.reminders.model;

import java.time.LocalTime;

public class Reminder {
	private String name;
	private LocalTime time;

	public Reminder(final String title, final LocalTime time) {
		this.name = title;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public LocalTime getTime() {
		return time;
	}
}
