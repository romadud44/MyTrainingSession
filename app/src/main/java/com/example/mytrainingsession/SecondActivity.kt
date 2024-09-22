package com.example.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytrainingsession.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var training: Exercise? = null
    private var listAdapter: ListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbarSecond.title = "Выбор упражнения"
        setSupportActionBar(binding.toolbarSecond)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listAdapter = ListAdapter(this, ExerciseDataBase.exercises)
        binding.trainingLV.adapter = listAdapter
        listAdapter?.notifyDataSetChanged()

        binding.trainingLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                training = listAdapter?.getItem(position)

                val intent = Intent(this, TrainingActivity::class.java)
                intent.putExtra("training", training)
                startActivity(intent)
            }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_second, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuSecond -> {
                finishAndRemoveTask()
                finishAffinity()
                finish()
                Toast.makeText(this, "Программа завершена", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}