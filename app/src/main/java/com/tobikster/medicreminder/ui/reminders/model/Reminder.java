package com.tobikster.medicreminder.ui.reminders.model;

import java.time.LocalTime;

public class Reminder {
	private String title;
	private LocalTime time;

	Reminder(final String title, final LocalTime time) {

		this.title = title;
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public LocalTime getTime() {
		return time;
	}
}
