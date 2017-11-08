package com.tobikster.medicreminder.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.LocalTime;

@Entity
public class Reminder {
	@PrimaryKey
	private int id;
	private String name;
	private LocalTime time;

	public Reminder(final int id, final String name, final LocalTime time) {
		this.id = id;
		this.name = name;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalTime getTime() {
		return time;
	}
}
