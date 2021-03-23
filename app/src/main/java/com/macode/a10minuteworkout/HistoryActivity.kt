package com.macode.a10minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.macode.a10minuteworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = findViewById<Toolbar>(R.id.historyToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Workout History"

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates() {
        val dbHandler = SqliteOpenHelper(this, null)
        val allCompletedDatesList = dbHandler.getAllCompleteDatesList()

        if (allCompletedDatesList.size > 0) {
            binding.completedWorkoutsText.visibility = View.VISIBLE
            binding.historyRecyclerView.visibility = View.VISIBLE
            binding.dataNotAvailableText.visibility = View.GONE

            binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, allCompletedDatesList)
            binding.historyRecyclerView.adapter = historyAdapter
        } else {
            binding.completedWorkoutsText.visibility = View.GONE
            binding.historyRecyclerView.visibility = View.GONE
            binding.dataNotAvailableText.visibility = View.VISIBLE
        }
    }
}