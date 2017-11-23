package com.tobikster.medicreminder.ui.reminders.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.ArrayMap
import com.tobikster.medicreminder.di.ViewModelSubComponent
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KFunction

@Singleton
class ReminderViewModelFactory @Inject constructor(
		viewModelSubComponent: ViewModelSubComponent
) : ViewModelProvider.Factory {
	private val creators: ArrayMap<Class<*>, KFunction<ViewModel>> = ArrayMap()

	init {
		creators.put(RemindersListModel::class.java, viewModelSubComponent::remindersListModel)
		creators.put(ReminderDetailsModel::class.java, viewModelSubComponent::reminderDetailsModel)
	}

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		var creator = creators[modelClass]

		if (creator == null) {
			for ((key, value) in creators) {
				if (modelClass.isAssignableFrom(key)) {
					creator = value
					break
				}
			}
		}
		if (creator == null) {
			throw IllegalArgumentException(String.format("Unknown model class %s", modelClass))
		}
		try {
			@Suppress("UNCHECKED_CAST")
			return creator.call() as T
		} catch (e: Exception) {
			throw RuntimeException(e)
		}

	}
}
