package com.tobikster.medicreminder.ui.reminders;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tobikster.medicreminder.R;
import com.tobikster.medicreminder.ui.reminders.model.ReminderDetailsModel;

import java.time.LocalTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ReminderDetailsFragment extends Fragment {
	@Inject
	ViewModelProvider.Factory viewModelFactory;

	private ReminderDetailsModel reminderDetailsModel;

	@BindView(R.id.title)
	EditText titleEditor;

	@BindView(R.id.time)
	TimePicker timeEditor;

	@BindView(R.id.save_button)
	Button saveButton;

	private Interactor interactor;

	public ReminderDetailsFragment() {
		// Required empty public constructor
	}

	public static ReminderDetailsFragment newInstance() {
		return new ReminderDetailsFragment();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof Interactor) {
			interactor = (Interactor) context;
		}
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
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);

		timeEditor.setIs24HourView(DateFormat.is24HourFormat(getContext()));
		saveButton.setOnClickListener(clickedView -> {
			if (reminderDetailsModel.addReminder(titleEditor.getText().toString(),
			                                 timeEditor.getHour(),
			                                 timeEditor.getMinute())) {
				if (interactor != null) {
					interactor.onReminderAdded();
				}
			} else {
				Toast.makeText(getContext(), "Cannot add reminder", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();

		reminderDetailsModel.reminderLiveData.observe(this, reminder -> {
			if (reminder != null) {
				titleEditor.setText(reminder.getTitle());
				final LocalTime time = reminder.getTime();
				if (time != null) {
					timeEditor.setHour(time.getHour());
					timeEditor.setMinute(time.getMinute());
				}
			}
		});
	}

	public interface Interactor {
		void onReminderAdded();
	}
}
