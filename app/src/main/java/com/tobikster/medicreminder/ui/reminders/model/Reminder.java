package com.tobikster.medicreminder.ui.reminders.model;


import org.joda.time.LocalTime;

public class Reminder {
	private String name;
	private LocalTime time;

	Reminder(String name, LocalTime time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public LocalTime getTime() {
		return time;
	}
}
