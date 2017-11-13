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
import android.widget.TextView;

import com.tobikster.medicreminder.R;
import com.tobikster.medicreminder.ui.reminders.model.RemindersListModel;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

	private RemindersAdapter remindersAdapter;

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

	static class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ReminderViewHolder> {

		private Context context;
		private List<RemindersListModel.Reminder> reminders;

		RemindersAdapter(final Context context) {
			this.context = context;
		}

		public void setData(final List<RemindersListModel.Reminder> reminders) {
			this.reminders = new ArrayList<>(reminders);
			notifyDataSetChanged();
		}

		@Override
		public ReminderViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
			final View view = LayoutInflater.from(context).inflate(R.layout.reminder_list_item, parent, false);
			return new ReminderViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ReminderViewHolder holder, final int position) {
			holder.bind(reminders.get(position));
		}

		@Override
		public int getItemCount() {
			return reminders.size();
		}

		static class ReminderViewHolder extends RecyclerView.ViewHolder {
			@BindView(R.id.title)
			TextView textView;
			@BindView(R.id.time)
			TextView timeView;

			ReminderViewHolder(View itemView) {
				super(itemView);
				ButterKnife.bind(this, itemView);
			}

			void bind(final RemindersListModel.Reminder reminder) {
				this.textView.setText(reminder.getTitle());
				this.timeView.setText(DateTimeFormatter.ISO_LOCAL_TIME.format(reminder.getTime()));
			}
		}
	}

	public interface Interactor {
		void onAddReminderButtonClicked();
	}
}
