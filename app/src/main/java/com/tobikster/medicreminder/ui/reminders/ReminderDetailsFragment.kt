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
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import butterknife.ButterKnife
import com.tobikster.medicreminder.R
import com.tobikster.medicreminder.ui.reminders.model.Reminder
import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel
import dagger.android.support.AndroidSupportInjection
import kotterknife.bindView
import javax.inject.Inject

class ReminderDetailsFragment : Fragment() {
	@Inject lateinit var viewModelFactory: ViewModelProvider.Factory

	private lateinit var reminderDetailsModel: ReminderDetailsModel

	private val titleEditor: EditText by bindView(R.id.title)
	private val timeEditor: TimePicker by bindView(R.id.time)
	private val saveButton: Button by bindView(R.id.save_button)

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

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_reminder_details, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		ButterKnife.bind(this, view)

		timeEditor.setIs24HourView(DateFormat.is24HourFormat(context))
		saveButton.setOnClickListener {
			if (reminderDetailsModel.addReminder(titleEditor.text.toString(),
					timeEditor.hour,
					timeEditor.minute)) {
				if (interactor != null) {
					interactor!!.onReminderAdded()
				}
			} else {
				Toast.makeText(context, "Cannot add reminder", Toast.LENGTH_SHORT).show()
			}
		}
	}

	override fun onStart() {
		super.onStart()

		reminderDetailsModel.reminderLiveData.observe(this, Observer<Reminder> {
			if (it != null) {
				titleEditor.setText(it.title)
				val time = it.time
				timeEditor.hour = time.hour
				timeEditor.minute = time.minute
			}
		})
	}

	interface Interactor {
		fun onReminderAdded()
	}

	companion object {
		fun newInstance(): ReminderDetailsFragment {
			return ReminderDetailsFragment()
		}
	}
}
