package id.anantyan.todolistviapulsa.common

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/**
 * Created by Arya Rezza Anantya on 09/06/24.
 */
fun formatDateTime(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = inputFormat.parse(inputDate)
    val outputFormat = SimpleDateFormat("dd MMM, yyyy - HH:mm", Locale.getDefault())
    return outputFormat.format(date!!)
}