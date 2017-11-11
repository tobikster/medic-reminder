package com.tobikster.medicreminder.ui.reminders;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.ArrayMap;

import com.tobikster.medicreminder.di.ViewModelSubComponent;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReminderViewModelFactory implements ViewModelProvider.Factory {
	private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

	@Inject
	public ReminderViewModelFactory(final ViewModelSubComponent viewModelSubComponent) {
		creators = new ArrayMap<>();

		creators.put(RemindersListModel.class, viewModelSubComponent::remindersListModel);
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(final @NonNull Class<T> modelClass) {
		Callable<? extends ViewModel> creator = creators.get(modelClass);

		if (creator == null) {
			for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
				if (modelClass.isAssignableFrom(entry.getKey())) {
					creator = entry.getValue();
					break;
				}
			}
		}
		if (creator == null) {
			throw new IllegalArgumentException(String.format("Unknown model class %s", modelClass));
		}
		try {
			//noinspection unchecked
			return (T) creator.call();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
