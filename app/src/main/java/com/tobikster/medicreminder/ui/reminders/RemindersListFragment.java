package com.tobikster.medicreminder.ui.reminders;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobikster.medicreminder.R;
import com.tobikster.medicreminder.data.Reminder;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemindersListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemindersListFragment extends Fragment {
	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private RemindersListModel remindersListModel;

	public RemindersListFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @return A new instance of fragment RemindersListFragment.
	 */
	public static RemindersListFragment newInstance() {
		return new RemindersListFragment();
	}

	@Override
	public void onAttach(final Context context) {
		AndroidSupportInjection.inject(this);
		super.onAttach(context);
	}

	@Override
	public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_reminders_list, container, false);
	}

	@Override
	public void onActivityCreated(final @Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		remindersListModel = ViewModelProviders.of(this, viewModelFactory).get(RemindersListModel.class);
	}

	@Override
	public void onStart() {
		super.onStart();

		remindersListModel.getReminders().observe(this, reminders -> {
			if (reminders != null) {
				for (Reminder reminder : reminders) {
					Timber.d("Reminder %s at %s", reminder.getName(), reminder.getTime());
				}
			}
		});
	}
}
