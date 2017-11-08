package com.tobikster.medicreminder.data;

import org.joda.time.LocalTime;

/**
 * Created by pti on 08.11.17.
 */

public class Reminder {
	private String name;
	private LocalTime time;

	public Reminder(String name, LocalTime time) {
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
