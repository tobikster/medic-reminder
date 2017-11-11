package com.tobikster.medicreminder.ui.reminders;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tobikster.medicreminder.R;
import com.tobikster.medicreminder.ui.reminders.model.RemindersListModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemindersListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemindersListFragment extends Fragment {
	@Inject
	ViewModelProvider.Factory viewModelFactory;
	private RemindersListModel remindersListModel;

	private Unbinder unbinder;

	@BindView(R.id.reminders)
	RecyclerView remindersList;
	@BindView(R.id.add_remind)
	FloatingActionButton addReminderFab;

	RemindersAdapter remindersAdapter;

	private Interactor interactor;

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

		if (context instanceof Interactor) {
			interactor = (Interactor) context;
		}
	}

	@Override
	public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_reminders_list, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		unbinder = ButterKnife.bind(this, view);

		remindersAdapter = new RemindersAdapter(this.getContext());
		remindersList.setLayoutManager(new LinearLayoutManager(this.getContext()));
		remindersList.setAdapter(remindersAdapter);

		addReminderFab.setOnClickListener(clickedView -> {
			if (interactor != null) {
				interactor.onAddReminderButtonClicked();
			}
		});
	}

	@Override
	public void onActivityCreated(final @Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		remindersListModel = ViewModelProviders.of(this, viewModelFactory).get(RemindersListModel.class);
	}

	@Override
	public void onStart() {
		super.onStart();

		remindersListModel.remindersList.observe(this, reminders -> remindersAdapter.setData(reminders));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}

	public interface Interactor {
		void onAddReminderButtonClicked();
	}
}
