package com.example.dataprocessor

import java.time.LocalTime
import java.util.Calendar
import java.util.TimeZone

class DataProcessor {
    val raspored: Array<Array<String>> = arrayOf( // [dan][cas]
        arrayOf("g", "g", "EP", "Matematika", "Matematika", "Srpski", "Pred A"),
        arrayOf("g", "g", "g", "g", "g", "g", "g"),
        arrayOf("EP", "RS", "g", "g", "Fizicko", "Engleski"),
        arrayOf("Matematika", "g", "g", "g", "g", "g", "g"),
        arrayOf("Srpski", "RMI", "Engleski", "Pred A / Verska", "Fizicko", "Pred B / Grad", "Pred B")
    )

    val grupe: Array<Array<Array<String>>> = arrayOf( // [dan][grupa][cas]
        arrayOf(
            arrayOf("PiT", "PiT", "", "", "", "", ""),
            arrayOf("RMI", "RMI", "", "", "", "", ""),
            arrayOf("PIT", "PIT", "", "", "", "", "")
        ),
        arrayOf(
            arrayOf("Programiranje", "Programiranje", "Programiranje", "WP", "WP", "WP", ""),
            arrayOf("WP", "WP", "WP", "PIT", "PIT", "ZIS", "ZIS"),
            arrayOf("PIT", "PIT", "ZIS", "ZIS", "Programiranje", "Programiranje", "Programiranje")
        ),
        arrayOf(
            arrayOf("", "", "PIT", "PIT", "", "", ""),
            arrayOf("", "", "PIT", "PIT", "", "", ""),
            arrayOf("", "", "RMI", "RMI", "", "", "")
        ),
        arrayOf(
            arrayOf("", "RMI", "RMI", "PIT", "PIT", "ZIS", "ZIS"),
            arrayOf("", "Programiranje", "Programiranje", "Programiranje", "PIT", "PIT", ""),
            arrayOf("", "PIT", "PIT", "WP", "WP", "WP", "")),
        arrayOf(
            arrayOf("", "", "", "", "", "Gradjansko", ""),
            arrayOf("", "", "", "Verska", "", "Preduzetnistvo", "Preduzetnistvo"),
            arrayOf("", "", "", "", "", "", "")
        )
    )

    val startTimeTable: Array<String> = arrayOf("08:00", "08:55", "10:00", "10:55", "11:50", "12:45", "13:35")
    val endTimeTable: Array<String> = arrayOf("8:45", "9:40", "10:45", "11:40", "12:35", "13:30", "14:20")

    private val timeZone = TimeZone.getDefault()
    private val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone.id))
    private var currentHour: Int = calendar.get(Calendar.HOUR_OF_DAY)
    private var currentMinute: Int = calendar.get(Calendar.MINUTE)
    private var day = 0

    var grupa = 0

    fun getDay(value: Int = -1): Int {
        return if (value == -1) {
            day = calendar.get(Calendar.DAY_OF_WEEK)
            day -= 2 // probably had to do this because sundays is 0 and index being at 0
            if (day > 4 || day < 0) day = 0
            day
        } else { day }
    }

    fun setDay(value: Int = -1) {
        day = if (value == -1) calendar.get(Calendar.DAY_OF_WEEK) else value
    }

    private fun getCurrentTime(type: Int = 0, hour: String = "00", minute: String = "00"): Int {
        val currentTime: LocalTime = if (type == 1) LocalTime.parse("$hour:$minute") else LocalTime.now()

        if (type == 1) {
            currentHour = hour.toInt()
            currentMinute = minute.toInt()
        }

        if (currentTime.isBefore(LocalTime.parse("06:00"))) return -1

        startTimeTable.forEach {
            if (currentTime.isBefore(LocalTime.parse(it))) return startTimeTable.indexOf(it)
        }
        return -1
    }

    fun getClass(type: Int = 0): String {
        var currentClass: String
        val classIndex: Int = getCurrentTime()
        val day = getDay()

        if (classIndex == -1 || (day == -1 || day == 6)) return "Nema Skole"

        currentClass = if (raspored[day][classIndex] == "g") grupe[day][grupa][classIndex]
        else raspored[day][classIndex]

        if (currentClass == "") currentClass = "Empty" // should never be called, still a fallback

        if (type == 1)
            return "day: $day  time: $currentHour:$currentMinute  startTime: ${startTimeTable[classIndex]}  classIndex: $classIndex  class: $currentClass".replace("  ", "\n")
        return currentClass
    }
}