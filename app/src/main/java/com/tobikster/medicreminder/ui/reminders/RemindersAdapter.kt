package com.tobikster.medicreminder.ui.reminders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tobikster.medicreminder.R
import com.tobikster.medicreminder.ui.reminders.model.Reminder
import kotlinx.android.synthetic.main.reminder_list_item.view.*
import org.threeten.bp.format.DateTimeFormatter

internal class RemindersAdapter(private val context: Context) : RecyclerView.Adapter<RemindersAdapter.ReminderViewHolder>() {

	private val reminders: MutableList<Reminder> = ArrayList()

	fun setData(reminders: List<Reminder>) {
		this.reminders.clear()
		this.reminders.addAll(reminders)
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.reminder_list_item, parent, false)
		return ReminderViewHolder(view)
	}

	override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
		holder.bind(reminders[position])
	}

	override fun getItemCount(): Int = reminders.size

	class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		fun bind(reminder: Reminder) {
			itemView.title.text = reminder.title
			itemView.time.text = DateTimeFormatter.ISO_LOCAL_TIME.format(reminder.time)
		}
	}

}
