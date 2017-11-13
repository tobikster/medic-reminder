package com.tobikster.medicreminder.ui.reminders;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobikster.medicreminder.R;
import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class ReminderDetailsFragment extends Fragment {
	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private ReminderDetailsModel reminderDetailsModel;

	public ReminderDetailsFragment() {
		// Required empty public constructor
	}

	public static ReminderDetailsFragment newInstance() {
		return new ReminderDetailsFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		AndroidSupportInjection.inject(this);
		super.onCreate(savedInstanceState);

		reminderDetailsModel = ViewModelProviders.of(this, viewModelFactory).get(ReminderDetailsModel.class);
	}

	@Override
	public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_reminder_details, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
	}
}
