package com.tobikster.medicreminder.ui.reminders.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tobikster.medicreminder.data.reminders.RemindersDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalTime
import javax.inject.Inject

class ReminderDetailsModel @Inject constructor(
		private val remindersDataSource: RemindersDataSource
) : ViewModel() {

	val reminderLiveData: LiveData<Reminder> = MutableLiveData()

	fun addReminder(title: String, hour: Int, minute: Int): Completable {
		val reminder = com.tobikster.medicreminder.data.reminders.Reminder(0, title, LocalTime.of(hour, minute))
		return remindersDataSource.addReminder(reminder)
		                          .observeOn(AndroidSchedulers.mainThread())
		                          .subscribeOn(Schedulers.io())
	}
}
