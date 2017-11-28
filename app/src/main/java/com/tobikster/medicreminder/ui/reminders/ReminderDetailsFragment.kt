package com.tobikster.medicreminder.ui.reminders


import android.annotation.SuppressLint

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tobikster.medicreminder.R
import com.tobikster.medicreminder.ui.reminders.model.Reminder
import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel
import dagger.android.support.AndroidSupportInjection
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_reminder_details.*
import timber.log.Timber
import javax.inject.Inject

class ReminderDetailsFragment : Fragment() {
	@Inject lateinit var viewModelFactory: ViewModelProvider.Factory

	private lateinit var reminderDetailsModel: ReminderDetailsModel

	private var interactor: Interactor? = null

	companion object {
		fun newInstance(): ReminderDetailsFragment = ReminderDetailsFragment()
	}

	override fun onAttach(context: Context?) {
		super.onAttach(context)
		if (context is Interactor) {
			interactor = context
		}
	}

	@SuppressLint("CheckResult")
	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidSupportInjection.inject(this)
		super.onCreate(savedInstanceState)

		reminderDetailsModel = ViewModelProviders.of(this, viewModelFactory).get(ReminderDetailsModel::class.java)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
			inflater.inflate(R.layout.fragment_reminder_details, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		time_editor.setIs24HourView(DateFormat.is24HourFormat(context))
		save_button.setOnClickListener {
			reminderDetailsModel.addReminder(title_editor.text.toString(), time_editor.hour, time_editor.minute).subscribe(
					object : CompletableObserver {
						override fun onComplete() {
							interactor?.onReminderAdded()
						}

						override fun onSubscribe(d: Disposable) {
						}

						override fun onError(e: Throwable) {
							Toast.makeText(context, "Cannot add reminder", Toast.LENGTH_SHORT).show()
							Timber.e(e, "Reminder cannot be added!")
						}
					}
			)
		}
	}

	override fun onStart() {
		super.onStart()

		reminderDetailsModel.reminderLiveData.observe(this, Observer<Reminder> {
			if (it != null) {
				title_editor.setText(it.title)
				val reminderTime = it.time
				time_editor.hour = reminderTime.hour
				time_editor.minute = reminderTime.minute
			}
		})
	}
	interface Interactor {
		fun onReminderAdded()

	}
}
