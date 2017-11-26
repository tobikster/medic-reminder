package com.tobikster.medicreminder.ui.reminders


import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tobikster.medicreminder.R
import com.tobikster.medicreminder.ui.reminders.model.Reminder
import com.tobikster.medicreminder.ui.reminders.model.RemindersListModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_reminders_list.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [RemindersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RemindersListFragment : Fragment() {
	companion object {

		/**
		 * Use this factory method to create a new instance of
		 * this fragment using the provided parameters.
		 *
		 * @return A new instance of fragment RemindersListFragment.
		 */
		fun newInstance(): RemindersListFragment = RemindersListFragment()
	}

	@Inject lateinit var viewModelFactory: ViewModelProvider.Factory

	private lateinit var remindersListModel: RemindersListModel

	private lateinit var remindersAdapter: RemindersAdapter

	private var interactor: Interactor? = null

	@SuppressLint("CheckResult")
	override fun onAttach(context: Context?) {
		AndroidSupportInjection.inject(this)
		super.onAttach(context)

		if (context is Interactor) {
			interactor = context
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
			inflater.inflate(R.layout.fragment_reminders_list, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		remindersAdapter = RemindersAdapter(this.context!!)
		reminders_list.layoutManager = LinearLayoutManager(this.context)
		reminders_list.adapter = remindersAdapter

		add_reminder_button.setOnClickListener {
			if (interactor != null) {
				interactor!!.onAddReminderButtonClicked()
			}
		}
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		remindersListModel = ViewModelProviders.of(this, viewModelFactory).get(RemindersListModel::class.java)
	}

	override fun onStart() {
		super.onStart()

		remindersListModel.remindersList.observe(this, Observer<List<Reminder>>{
			remindersAdapter.setData(it ?: emptyList())
		})
	}

	interface Interactor {
		fun onAddReminderButtonClicked()

	}
}
