package com.tobikster.medicreminder.di;

import com.tobikster.medicreminder.ui.model.RemindersListModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
	@Subcomponent.Builder
	interface Builder {
		ViewModelSubComponent build();
	}

	RemindersListModel remindersListModel();
}
