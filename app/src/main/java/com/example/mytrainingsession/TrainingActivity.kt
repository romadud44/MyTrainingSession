package com.example.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytrainingsession.databinding.ActivityTrainingBinding

@Suppress("DEPRECATION")
class TrainingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrainingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbarTraining.title = "Тренировка"
        setSupportActionBar(binding.toolbarTraining)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val training: Exercise = intent.extras?.getSerializable("training") as Exercise
        binding.toolbarTraining.title = training.name
        binding.descriptionTV.text = training.description

        binding.startTrainingBTN.setOnClickListener {
            binding.trainingGifIV.setImageResource(training.gifImage)
            binding.startTrainingBTN.isEnabled = false
            binding.startTrainingBTN.text = "Процесс тренировки"
            binding.timerTV.text = formatTime(training.durationInSeconds)
            var timer = object : CountDownTimer(training.durationInSeconds * 1000L, 1000) {
                override fun onTick(p0: Long) {
                    binding.timerTV.text = formatTime((p0 / 1000).toInt())
                }

                override fun onFinish() {
                    binding.timerTV.text = "Упражнение завершено"
                    binding.trainingGifIV.visibility = View.VISIBLE
                    binding.startTrainingBTN.isEnabled = true
                    binding.startTrainingBTN.text = "Начать заново"
                    binding.trainingGifIV.setImageResource(0)
                }
            }.start()
        }

        binding.backTrainingBTN.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_training, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuTraining -> {
                finishAndRemoveTask()
                finishAffinity()
                finish()
                Toast.makeText(this, "Программа завершена", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}