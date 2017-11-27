package com.tobikster.medicreminder.ui.reminders.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tobikster.medicreminder.data.reminders.RemindersDataSource
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.time.LocalTime
import javax.inject.Inject

class ReminderDetailsModel @Inject constructor(
		private val remindersDataSource: RemindersDataSource
) : ViewModel() {

	val reminderLiveData: LiveData<Reminder> = MutableLiveData()

	fun addReminder(title: String, hour: Int, minute: Int) {
		val reminder = com.tobikster.medicreminder.data.reminders.Reminder(0, title, LocalTime.of(hour, minute))
		remindersDataSource.addReminder(reminder)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(
						object : CompletableObserver {
							override fun onComplete() {
								Timber.d("Reminder successfully added!")
							}

							override fun onSubscribe(d: Disposable) {
							}

							override fun onError(e: Throwable) {
								Timber.e(e,"Error while adding reminder!")
							}
						})
	}
}
