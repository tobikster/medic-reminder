package com.tobikster.medicreminder.di

import com.tobikster.medicreminder.ui.reminders.ReminderDetailsFragment
import com.tobikster.medicreminder.ui.reminders.RemindersActivity
import com.tobikster.medicreminder.ui.reminders.RemindersListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BuildersModule {
	@ContributesAndroidInjector
	fun contributeRemindersListActivity(): RemindersActivity

	@ContributesAndroidInjector
	fun contributeRemindersListFragment(): RemindersListFragment

	@ContributesAndroidInjector
	fun contributeReminderDetailFragment(): ReminderDetailsFragment
}
