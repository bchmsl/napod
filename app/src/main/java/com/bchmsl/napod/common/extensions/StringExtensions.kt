package com.bchmsl.napod.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.addDays(daysToAdd: Int): String {
    val date = this.split("-")
    val year = date[0].toInt()
    val month = date[1].toInt()
    val day = date[2].toInt()
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day)
    calendar.add(Calendar.DAY_OF_MONTH, daysToAdd)
    val nextDay = calendar.time
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(nextDay)
}

fun String.beautifyDate(): String {
    val date = this.split("-")
    val year = date[0].toInt()
    val month = date[1].toInt()
    val day = date[2].toInt()
    val calendar = Calendar.getInstance()
    calendar.set(year, month-1, day)
    val nextDay = calendar.time
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return dateFormat.format(nextDay)
}