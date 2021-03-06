package com.example.footballfixtures.utils

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun formatDate(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            .format(date)
    }

    fun toDate(date: String?): Date? {
        try {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(
                date ?: ""
            )
        } catch (e: Throwable) {
            Timber.e(e)
            return null
        }
    }

    fun formatTime(time: String?): String? {
        try {
            return SimpleDateFormat("HH:mm", Locale.getDefault())
                .format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(time))
        }catch (e: Throwable) {
            Timber.e(e)
            return null
        }
    }
}