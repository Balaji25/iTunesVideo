package com.bg.itunesvideo.data.utils

import android.os.Build
import android.text.Html
import android.text.Spanned
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object VideoUtils {

    /**
     * This getSongDuration method used to convert video duration millis to min/ sec
     * *
     *
     * */
    fun getSongDuration(milliseconds: Int): String {
        var duration = "";
        try {
            val minutes = milliseconds.toLong() / 1000 / 60
            val seconds = milliseconds.toLong() / 1000 % 60
            duration = "$minutes:$seconds"
        } catch (e: Exception) {

        }

        return duration;

    }

    /**
     * This getDayName method used to format date
     * *
     * */
    fun getDayName(dateStr: String): String {
        //"2021-01-07T00:00:00-07:00"
        var day = ""
        try {
            val inFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.US)
            var dateArr = dateStr.split("-")
            val date = inFormat.parse(dateArr[0] + "-" + dateArr[1] + "-" + dateArr[2])
            val outFormat = SimpleDateFormat("EEEE MMM yyyy hh:mm", Locale.US)
            day = outFormat.format(date!!)
        } catch (e: Exception) {
            e.stackTrace
        }

        return day;
    }




}