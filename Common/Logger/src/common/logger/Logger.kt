package common.logger

import java.text.SimpleDateFormat
import java.util.*

/**
 * Logs information on the console.
 *
 * @author Alexander Komischke
 */
object Logger {
    private const val shouldLog = true
    private const val separator = "\t : \t"
    private fun format(message: String): String {
        if (!shouldLog) {
            return ""
        }
        val calendar: Calendar = GregorianCalendar()
        val simpleDateFormat = SimpleDateFormat("yyy.MM.dd HH:mm:ss")
        return simpleDateFormat.format(calendar.time) + separator + message
    }

    fun log(o: Any) {
        log(o.toString())
    }

    fun log(o: Any?, argument: Any?) {
        log(o.toString() + separator + argument.toString())
    }

    fun log(message: String) {
        if (shouldLog) {
            println(format(message))
        }
    }

}
