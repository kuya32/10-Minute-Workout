package com.macode.a10minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.macode.a10minuteworkout.databinding.SingleItemHistoryRowBinding

class HistoryAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SingleItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dbHandler = SqliteOpenHelper(context, null)
        val date: String = items[position]
        with(holder) {
            binding.historyDatePosition.text = (position + 1).toString()
            binding.historyDateItem.text = date

            binding.historyItemDeleteButton.setOnClickListener {
                val db = dbHandler.writableDatabase
                items.removeAt(position)
                db.delete(SqliteOpenHelper.TABLE_HISTORY, "${SqliteOpenHelper.COLUMN_ID}=?",
                    arrayOf((position + 1).toString())
                )
                db.close()
                notifyItemRemoved(position + 1)
                notifyItemRangeChanged((position + 1), items.size)
            }

            if (position % 2 == 0) {
                binding.singleItemHistoryContainer.setBackgroundColor(Color.parseColor("#EBEBEB"))
            } else {
                binding.singleItemHistoryContainer.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}