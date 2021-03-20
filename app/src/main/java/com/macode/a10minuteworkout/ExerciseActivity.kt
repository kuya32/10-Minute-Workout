package com.macode.a10minuteworkout

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.macode.a10minuteworkout.databinding.ActivityExerciseBinding
import com.macode.a10minuteworkout.databinding.MainToolbarBinding
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() , TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = findViewById<Toolbar>(R.id.exerciseToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        tts = TextToSpeech(this, this)

        exerciseList = Exercises.defaultExerciseList()
        setUpRestView()

    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player != null) {
            player!!.stop()
        }

        super.onDestroy()
    }

    private fun setRestProgressBar() {
        binding.countdownProgressBar.progress = restProgress
        restTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.countdownProgressBar.progress = 10 - restProgress
                binding.countdownTimer.text = (10 - restProgress).toString()
                try {
                    player = MediaPlayer.create(applicationContext, R.raw.beep)
                    player!!.isLooping = false
                    if (restProgress in 7..10) {
                        player!!.start()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFinish() {
                currentExercisePosition++
                setUpExerciseView()
            }
        }.start()
    }

    private fun setUpRestView() {
        binding.linearExercise.visibility = View.GONE
        binding.linearCountdown.visibility = View.VISIBLE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (currentExercisePosition > -1) {
            binding.introRestText.text = "Rest"
        }

        binding.upcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()
        setRestProgressBar()
    }
    private fun setExerciseProgressBar() {
        binding.exerciseProgressBar.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding.exerciseProgressBar.progress = 30 - exerciseProgress
                binding.exerciseTimer.text = (30 - exerciseProgress).toString()
                try {
                    player = MediaPlayer.create(applicationContext, R.raw.beep)
                    player!!.isLooping = false
                    if (exerciseProgress in 27..30) {
                        player!!.start()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    setUpRestView()
                } else {
                    Toast.makeText(this@ExerciseActivity, "Well done!", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    private fun setUpExerciseView() {
        binding.linearCountdown.visibility = View.GONE
        binding.linearExercise.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        setExerciseProgressBar()

        binding.exerciseImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding.exerciseName.text = exerciseList!![currentExercisePosition].getName()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initialization failed!")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

}