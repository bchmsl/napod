package com.bchmsl.napod.presentation.ui.curiosity

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.bchmsl.napod.R
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    var dateSetCallback: ((Int, Int, Int) -> Unit)? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val day = CuriosityFragment.date.split("-")[2].toInt()
        val month = CuriosityFragment.date.split("-")[1].toInt()
        val year = CuriosityFragment.date.split("-")[0].toInt()

        val dialog = DatePickerDialog(
            requireContext(),
            R.style.MySpinnerDatePickerStyle,
            this,
            year,
            month,
            day
        )
        dialog.datePicker.minDate = 1344196800000
        dialog.datePicker.maxDate = System.currentTimeMillis() - 1000
        return dialog
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateSetCallback?.invoke(dayOfMonth, month + 1, year)
    }
}