package com.tobikster.medicreminder.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel
import com.tobikster.medicreminder.ui.reminders.model.RemindersListModel
import com.tobikster.medicreminder.ui.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
	@Binds
	abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

	@Binds
	@IntoMap
	@ViewModelKey(RemindersListModel::class)
	abstract fun bindRemindersListViewModel(remindersListModel: RemindersListModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(ReminderDetailsModel::class)
	abstract fun bindReminderDetailsViewModel(reminderDetailsModel: ReminderDetailsModel): ViewModel
}
