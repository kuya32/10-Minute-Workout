package com.macode.a10minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.macode.a10minuteworkout.databinding.SingleItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context) :
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SingleItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        with(holder) {
            binding.exerciseStatusItem.text = model.getId().toString()

            if (model.getIsSelected()) {
                binding.exerciseStatusItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_border_status)
                binding.exerciseStatusItem.setTextColor(Color.BLACK)
            } else if (model.getIsComplete()) {
                binding.exerciseStatusItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
                binding.exerciseStatusItem.setTextColor(Color.parseColor("#FFD700"))
            } else {
                binding.exerciseStatusItem.background = ContextCompat.getDrawable(context, R.drawable.exercise_status_item_gray_background)
                binding.exerciseStatusItem.setTextColor(Color.BLACK)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}