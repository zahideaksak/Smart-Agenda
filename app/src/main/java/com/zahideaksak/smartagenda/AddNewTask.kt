package com.zahideaksak.smartagenda

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zahideaksak.smartagenda.databinding.AddNewTaskBinding
import com.zahideaksak.smartagenda.model.ToDoDao
import java.util.*


class AddNewTask : AppCompatActivity() {

    private lateinit var binding: AddNewTaskBinding
    private lateinit var toDoDao: ToDoDao

    private var datePickerDialog: DatePickerDialog? = null
    private var calendar: Calendar? = null
    private var year: Int = 0
    private var month: Int = 0
    private var dayOfMonth: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toDoDao = ToDoDao()

        binding.saveBtn.setOnClickListener {
            val note = binding.taskEdt.text.toString()
            val dueDate = binding.setDueTv.text.toString()
            if (note.isNotEmpty()) {
                toDoDao.addNote(dueDate, note)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }


        binding.setDueTv!!.setOnClickListener {
            calendar = Calendar.getInstance()
            year = calendar!!.get(Calendar.YEAR)
            month = calendar!!.get(Calendar.MONTH)
            dayOfMonth = calendar!!.get(Calendar.DAY_OF_MONTH)
            datePickerDialog = DatePickerDialog(this@AddNewTask, R.style.MyDialogTheme,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    binding.setDueTv.text = "$day/$month/$year"
                }, year, month, dayOfMonth)
            datePickerDialog!!.show()
        }
    }

}