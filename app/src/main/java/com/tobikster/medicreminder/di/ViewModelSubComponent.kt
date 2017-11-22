package com.tobikster.medicreminder.di

import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel
import com.tobikster.medicreminder.ui.reminders.model.RemindersListModel

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
	@Subcomponent.Builder
	interface Builder {
		fun build(): ViewModelSubComponent
	}

	fun remindersListModel(): RemindersListModel
	fun reminderDetailsModel(): ReminderDetailsModel
}
