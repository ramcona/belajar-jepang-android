package id.synertia.belajarbahasajepang.ui.sharedView

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DatePickerDialog(val context: Context,val callback: Callback) {
    private val myCalendar: Calendar = Calendar.getInstance()

    fun show() {
        val datePickerListener = DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->

            myCalendar.set(Calendar.YEAR, selectedYear)
            myCalendar.set(Calendar.MONTH, selectedMonth)
            myCalendar.set(Calendar.DAY_OF_MONTH, selectedDay)

            val formatTanggal = "yyyy-MM-dd"
            val dateFormat = SimpleDateFormat(formatTanggal, Locale.getDefault())
            val datePick = dateFormat.format(myCalendar.time)

            this.callback.onDatePick(datePick)
        }

        val datePickerDialog = DatePickerDialog(
            context,
            datePickerListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )


        datePickerDialog.show()
    }



    interface Callback {
        fun onDatePick(date: String)
    }
}