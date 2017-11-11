package com.tobikster.medicreminder.ui.reminders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tobikster.medicreminder.R;
import com.tobikster.medicreminder.data.Reminder;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ReminderViewHolder> {
	private Context context;
	private List<Reminder> reminders;

	RemindersAdapter(final Context context) {
		this.context = context;
	}

	public void setData(final List<Reminder> reminders) {
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

		void bind(final Reminder reminder) {
			this.textView.setText(reminder.getName());
			final DateTimeFormatter timeFormatter = DateTimeFormat.forStyle("-S");
			this.timeView.setText(timeFormatter.print(reminder.getTime()));
		}
	}
}
