package com.tobikster.medicreminder.di;

import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel;
import com.tobikster.medicreminder.ui.reminders.model.RemindersListModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
	@Subcomponent.Builder
	interface Builder {
		ViewModelSubComponent build();
	}

	RemindersListModel remindersListModel();
	ReminderDetailsModel reminderDetailsModel();
}
