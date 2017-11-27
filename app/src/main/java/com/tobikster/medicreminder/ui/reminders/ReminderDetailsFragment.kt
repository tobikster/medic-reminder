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
import com.tobikster.medicreminder.R
import com.tobikster.medicreminder.ui.reminders.model.Reminder
import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_reminder_details.*
import javax.inject.Inject

class ReminderDetailsFragment : Fragment() {
	@Inject lateinit var viewModelFactory: ViewModelProvider.Factory

	private lateinit var reminderDetailsModel: ReminderDetailsModel

	private var interactor: Interactor? = null

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

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =// Inflate the layout for this fragment
			inflater.inflate(R.layout.fragment_reminder_details, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		time_editor.setIs24HourView(DateFormat.is24HourFormat(context))
		save_button.setOnClickListener {
			reminderDetailsModel.addReminder(title_editor.text.toString(), time_editor.hour, time_editor.minute)
			interactor?.onReminderAdded()
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

	companion object {
		fun newInstance(): ReminderDetailsFragment = ReminderDetailsFragment()
	}
}
