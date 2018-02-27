package com.tobikster.medicreminder.ui.reminders

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.tobikster.medicreminder.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class RemindersActivity : AppCompatActivity(), HasSupportFragmentInjector, RemindersListFragment.Interactor, ReminderDetailsFragment.Interactor {
	@Inject
	lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

	@SuppressLint("CheckResult")
	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val remindersListFragment = RemindersListFragment.newInstance()
		supportFragmentManager.beginTransaction().replace(R.id.content, remindersListFragment).commit()
	}

	override fun supportFragmentInjector(): AndroidInjector<Fragment>? = fragmentDispatchingAndroidInjector

	override fun onAddReminderButtonClicked() {
		val reminderDetailsFragment = ReminderDetailsFragment.Companion.newInstance()
		supportFragmentManager.beginTransaction()
				.addToBackStack(null)
				.replace(R.id.content, reminderDetailsFragment)
				.commit()
	}

	override fun onReminderAdded() {
		val remindersListFragment = RemindersListFragment.Companion.newInstance()
		supportFragmentManager.beginTransaction()
				.addToBackStack(null)
				.replace(R.id.content, remindersListFragment)
				.commit()
	}
}
