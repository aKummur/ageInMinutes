package com.akummur.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var tvSelectedDate: TextView? = null
    var tvSelectedDateInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
//            Toast.makeText(this, "Button Works", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickDatePicker(view: View ) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "$selectedDayOfMonth : ${selectedMonth+1} : $selectedYear ",
                Toast.LENGTH_SHORT).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time / 60000

            val diffInMinutes = (currentDateInMinutes - selectedDateInMinutes)
//            val diffInDays = diffInMinutes / 1440
            tvSelectedDateInMinutes?.setText(diffInMinutes.toString())
        }, year, month, day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}